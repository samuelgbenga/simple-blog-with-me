package uk.samuel.post_maker.service;

import uk.samuel.post_maker.payload.request.PushNotificationRequestDto;
import uk.samuel.post_maker.payload.response.PushNotificationResponseDto;
import uk.samuel.post_maker.payload.response.GeneralResponseDto;

import java.util.List;

public interface PushNotificationService {

    GeneralResponseDto saveToken(PushNotificationRequestDto requestDto);

    List<PushNotificationResponseDto> getAllToken();
}
