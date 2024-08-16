package uk.samuel.post_maker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.samuel.post_maker.entity.PushNotification;
import uk.samuel.post_maker.payload.request.PushNotificationRequestDto;
import uk.samuel.post_maker.payload.response.GeneralResponseDto;
import uk.samuel.post_maker.payload.response.PushNotificationResponseDto;
import uk.samuel.post_maker.repository.PushNotificationRepository;
import uk.samuel.post_maker.service.PushNotificationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PushNotificationServiceImpl implements PushNotificationService {

    private final PushNotificationRepository pushNotificationRepository;

    @Override
    public GeneralResponseDto saveToken(PushNotificationRequestDto requestDto) {

        boolean isExist = pushNotificationRepository.existsByToken(requestDto.getToken());

        if(isExist){
            return GeneralResponseDto.builder()
                    .responseCode("040")
                    .responseMessage("Token already exist")
                    .build();
        }

        pushNotificationRepository.save(PushNotification.builder()
                        .token(requestDto.getToken())
                .build());

        return GeneralResponseDto.builder()
                .responseCode("004")
                .responseMessage("Push Notification token saved")
                .build();
    }

    @Override
    public List<PushNotificationResponseDto> getAllToken() {

        List<PushNotification> lists = pushNotificationRepository.findAll();

        return lists.stream().map(notification -> new PushNotificationResponseDto(notification.getToken()))
                .collect(Collectors.toList());
    }
}
