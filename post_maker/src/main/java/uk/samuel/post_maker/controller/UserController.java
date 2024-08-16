package uk.samuel.post_maker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uk.samuel.post_maker.payload.request.PostRequestDto;
import uk.samuel.post_maker.payload.request.UserLoginRequestDto;
import uk.samuel.post_maker.payload.request.UserSignupRequestDto;
import uk.samuel.post_maker.payload.response.GeneralResponseDto;
import uk.samuel.post_maker.payload.response.PostResponseDto;
import uk.samuel.post_maker.service.UserService;
import uk.samuel.post_maker.service.impl.FCMService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from this origin
public class UserController {
    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<GeneralResponseDto> signup(@Validated @RequestBody UserSignupRequestDto requestDto){

        GeneralResponseDto generalResponseDto = userService.signup(requestDto);

        return ResponseEntity.ok(generalResponseDto);
    }

    // login user
    @PostMapping("/login")
    public ResponseEntity<GeneralResponseDto> login(@RequestBody UserLoginRequestDto requestDto){

        GeneralResponseDto generalResponseDto = userService.login(requestDto);

        return ResponseEntity.ok(generalResponseDto);
    }

    // create new post
    @PostMapping("/{username}/newPost")
    public ResponseEntity<GeneralResponseDto> createPost(@PathVariable String username, @RequestBody PostRequestDto requestDto){

        GeneralResponseDto generalResponseDto = userService.createPost(username, requestDto);

        return ResponseEntity.ok(generalResponseDto);
    }

    // get all post
    @GetMapping("/{username}/get-all-post")
    public ResponseEntity<List<PostResponseDto>> getUsersPost(@PathVariable String username){

        List<PostResponseDto> response = userService.getAllPost(username);

         return ResponseEntity.ok(response);
    }
}
