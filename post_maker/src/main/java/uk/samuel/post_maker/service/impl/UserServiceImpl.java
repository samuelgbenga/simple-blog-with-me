package uk.samuel.post_maker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.samuel.post_maker.entity.Post;
import uk.samuel.post_maker.entity.PushNotification;
import uk.samuel.post_maker.entity.UserEntity;
import uk.samuel.post_maker.exception.GeneralExceptionHandler;
import uk.samuel.post_maker.payload.request.FireBaseNotificationRequestDto;
import uk.samuel.post_maker.payload.request.PostRequestDto;
import uk.samuel.post_maker.payload.request.UserLoginRequestDto;
import uk.samuel.post_maker.payload.request.UserSignupRequestDto;
import uk.samuel.post_maker.payload.response.GeneralResponseDto;
import uk.samuel.post_maker.payload.response.PostResponseDto;
import uk.samuel.post_maker.payload.response.PushNotificationResponseDto;
import uk.samuel.post_maker.repository.PostRepository;
import uk.samuel.post_maker.repository.UserRepository;
import uk.samuel.post_maker.service.PushNotificationService;
import uk.samuel.post_maker.service.UserService;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final PushNotificationService pushNotificationService;

    private final FCMService fcmService;


    @Override
    public GeneralResponseDto signup(UserSignupRequestDto requestDto) {

        // check for username
        if(userRepository.existsByEmail(requestDto.getEmail())){
            return GeneralResponseDto.builder()
                    .responseCode("010")
                    .responseMessage("Email already exist")
                    .build();
        }
        else if(userRepository.existsByUsername(requestDto.getUsername())){
            return GeneralResponseDto.builder()
                    .responseCode("020")
                    .responseMessage("Username already exist")
                    .build();
        }


        UserEntity newUser = UserEntity.builder()
                .fullName(requestDto.getFullName())
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .phoneNumber(requestDto.getPhoneNumber())
                .password(requestDto.getPassword())
                .build();

        userRepository.save(newUser);

        return GeneralResponseDto.builder()
                .responseCode("001")
                .responseMessage("User Created successfully")
                .build();
    }

    @Override
    public GeneralResponseDto login(UserLoginRequestDto requestDto) {


        UserEntity user = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(()->
                new GeneralExceptionHandler("Wrong credentials") );


        if(!user.getPassword().equals(requestDto.getPassword())){
            throw new GeneralExceptionHandler("Wrong credentials");
        }


        return GeneralResponseDto.builder()
                .responseCode("002")
                .responseMessage("User Login successfully")
                .build();
    }

    @Override
    public GeneralResponseDto createPost(String username, PostRequestDto requestDto) {

        UserEntity user = userRepository.findByUsername(username).orElseThrow(()->
                new GeneralExceptionHandler("User Does not exist"));

        if(postRepository.existsByPostTitle(requestDto.getPostTitle())){
            return GeneralResponseDto.builder()
                    .responseCode("030")
                    .responseMessage("Post Title already exist!")
                    .build();
        }

        Post post = Post.builder()
                .postTitle(requestDto.getPostTitle())
                .post(requestDto.getPost())
                .user(user)
                .build();


        FireBaseNotificationRequestDto fireBaseNotificationRequestDto = FireBaseNotificationRequestDto.builder()
                .title(post.getUser().getUsername()+" created A new Post")
                .body(post.getPostTitle())
                .build();

        // get the list of all available browser token
        List<PushNotificationResponseDto> pushNotificationsTokens = pushNotificationService.getAllToken();

        for(PushNotificationResponseDto responseDto: pushNotificationsTokens){
            fireBaseNotificationRequestDto.setToken(responseDto.getToken());

            createPostPushNotification(fireBaseNotificationRequestDto);
        }


        postRepository.save(post);

        return GeneralResponseDto.builder()
                .responseCode("003")
                .responseMessage("Post Created successfully")
                .build();
    }

    @Override
    public List<PostResponseDto> getAllPost(String username) {

        UserEntity user = userRepository.findByUsername(username).orElseThrow(()->
                new GeneralExceptionHandler("User Does not exist"));

        List<PostResponseDto> userPosts = user.getPosts().stream().map(post ->
         new PostResponseDto(
                 post.getPostTitle(),
                 post.getPost()
         )).collect(Collectors.toList());

        return userPosts;
    }

    private void createPostPushNotification(FireBaseNotificationRequestDto request){

        try {
            fcmService.sendMessageToToken(request);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
