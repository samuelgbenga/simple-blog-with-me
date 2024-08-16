package uk.samuel.post_maker.service.impl;


import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.HttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.samuel.post_maker.payload.request.FireBaseNotificationRequestDto;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
public class FCMService {

    private Logger logger = LoggerFactory.getLogger(FCMService.class);

    public void sendMessageToToken(FireBaseNotificationRequestDto request)
            throws InterruptedException, ExecutionException{

            Message message = getPreconfiguredMessageToToken(request);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonOutput = gson.toJson(message);
            String response = sendAndGetResponse(message);
            logger.info("Sent message to token. Device token: {}, {} msg {}", request.getToken(), response, jsonOutput);

    }

    private String sendAndGetResponse(Message message) throws ExecutionException, InterruptedException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private Message getPreconfiguredMessageToToken(FireBaseNotificationRequestDto request) {
        return getPreconfiguredMessageBuilder(request)
                .setToken(request.getToken())
                .build();
    }

    // handle android notification
    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis())
                .setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setTag(topic)
                        .build())
                .build();
    }

    // handle ios notification
    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder()
                        .setCategory(topic)
                        .setThreadId(topic)
                        .build())
                .build();
    }

    // for desktop web support notification
    private WebpushConfig getWebpushConfig(String title, String body) {
        return WebpushConfig.builder()
                .putHeader("ttl", "300")
                .setNotification(WebpushNotification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .setIcon("https://avatar.iran.liara.run/public/boy?username=Ash")
                        .build())
                .build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(FireBaseNotificationRequestDto request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        WebpushConfig webpushConfig = getWebpushConfig(request.getTitle(), request.getBody());

        Notification notification = Notification.builder()
                .setTitle(request.getTitle())
                .setBody(request.getBody())
                .build();

        return Message.builder()
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .setWebpushConfig(webpushConfig) // Add WebpushConfig for desktop notifications
                .setNotification(notification);
    }

}
