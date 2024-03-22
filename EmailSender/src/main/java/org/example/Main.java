package org.example;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {
    public static void sendEmail(String recipientEmail, String confirmationLink) {

        String fromEmail = "";
        String emailPassword = "";


        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, emailPassword);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Potwierdzenie założenia konta");
            message.setText("Witaj,\n\nDziękujemy za założenie konta. Kliknij poniższy link, aby potwierdzić swoje konto:\n\n" + confirmationLink);


            Transport.send(message);
            System.out.println("Wiadomość wysłana pomyślnie.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String recipientEmail = "";
        String confirmationLink = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";


        sendEmail(recipientEmail, confirmationLink);
    }
}