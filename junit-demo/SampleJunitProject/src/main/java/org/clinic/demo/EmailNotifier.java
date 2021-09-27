package org.clinic.demo;

public interface EmailNotifier {
    void sendNotification(String subject, String body, String emailId);
}
