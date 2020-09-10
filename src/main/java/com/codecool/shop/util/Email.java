package com.codecool.shop.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Objects;
import java.util.Properties;


public class Email {

    public static final String EMAIL_PROPERTIES = "email.properties";

    public static void sendEmail(String toEmailAddress, String body, String title) throws IOException, MessagingException {
        Properties prop = new Properties();
        ClassLoader classLoader = Email.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(EMAIL_PROPERTIES)).getFile());
        prop.load(new FileInputStream(file.getAbsolutePath()));
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(prop.getProperty("email.username"), prop.getProperty("email.password"));
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(prop.getProperty("email.username")));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));
        message.setSubject(title);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
    }

}
