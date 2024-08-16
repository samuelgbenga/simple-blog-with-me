package uk.samuel.post_maker.controller;


import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.samuel.post_maker.payload.request.FireBaseNotificationRequestDto;
import uk.samuel.post_maker.payload.request.PushNotificationRequestDto;
import uk.samuel.post_maker.payload.response.FireBaseNotificationResponseDto;
import uk.samuel.post_maker.payload.response.GeneralResponseDto;
import uk.samuel.post_maker.service.PushNotificationService;
import uk.samuel.post_maker.service.impl.FCMService;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/push-notification")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from this origin
public class PushNotificationController {

    private final PushNotificationService pushNotificationService;

    private final FCMService fcmService;

//    @PostMapping("/notification")
//    public ResponseEntity sendNotification(@RequestBody FireBaseNotificationRequestDto request) throws ExecutionException, InterruptedException, HttpResponseException {
//
//        try {
//            fcmService.sendMessageToToken(request);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//
//        return new ResponseEntity<>(new FireBaseNotificationResponseDto(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
//    }

    @PostMapping("/save")
    public ResponseEntity<GeneralResponseDto> save(@RequestBody PushNotificationRequestDto requestDto){

       return ResponseEntity.ok(pushNotificationService.saveToken(requestDto));
    }
}
