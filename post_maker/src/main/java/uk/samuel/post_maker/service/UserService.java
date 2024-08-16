package uk.samuel.post_maker.service;

import uk.samuel.post_maker.payload.request.PostRequestDto;
import uk.samuel.post_maker.payload.request.UserLoginRequestDto;
import uk.samuel.post_maker.payload.request.UserSignupRequestDto;
import uk.samuel.post_maker.payload.response.GeneralResponseDto;
import uk.samuel.post_maker.payload.response.PostResponseDto;

import java.util.List;

public interface UserService {

    GeneralResponseDto signup(UserSignupRequestDto requestDto);

    GeneralResponseDto login(UserLoginRequestDto requestDto);

    GeneralResponseDto createPost(String username, PostRequestDto responseDto);

    List<PostResponseDto> getAllPost(String username);
}

// FileInputStream serviceAccount =
// new FileInputStream("path/to/serviceAccountKey.json");

// FirebaseOptions options = new FirebaseOptions.Builder()
//   .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//   .build();

// FirebaseApp.initializeApp(options);