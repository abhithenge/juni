package org.clinic.demo;

import java.util.ArrayList;

public class EmailNotifierTest implements EmailNotifier{

    ArrayList<Message> receivedMessage = new ArrayList<>();

    @Override
    public void sendNotification(String subject, String body, String emailId) {
        receivedMessage.add(new Message(subject,body,emailId));
    }

    class Message{
        String subject;
        String body;
        String toEmailId;

        public Message(String subject, String body, String emailId) {
            this.subject = subject;
            this.body = body;
            this.toEmailId = emailId;
        }
    }
}
