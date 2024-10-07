package org.buddy.backend.aws;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

@Service
public class SqsService {

    private final SqsClient sqsClient;

    public SqsService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void updateRecommendedBuddies(String firebaseUID) {
        String queueName = System.getenv("SQS_NAME_BUDDY_RECOMMENDATIONS");
        try {
            String queueURL = this.getQueueUrl(queueName);

            System.out.println("Queue URL: " + queueURL);
    
            this.sendMessageToQueue(queueURL, firebaseUID);
        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    private void sendMessageToQueue(String queueUrl, String messageBody) {
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .build();

        SendMessageResponse sendMessageResponse = sqsClient.sendMessage(sendMessageRequest);

        System.out.println("Mensaje enviado. ID del mensaje: " + sendMessageResponse.messageId());
    }

    private String getQueueUrl(String queueName) {
        GetQueueUrlRequest getQueueRequest = GetQueueUrlRequest.builder()
                .queueName(queueName)
                .build();
    
        return sqsClient.getQueueUrl(getQueueRequest).queueUrl();
    }
}

