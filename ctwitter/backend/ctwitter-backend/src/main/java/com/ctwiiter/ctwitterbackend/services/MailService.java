package com.ctwiiter.ctwitterbackend.services;

import com.ctwiiter.ctwitterbackend.exceptions.EmailFailedToSendException;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.io.ByteArrayOutputStream;

@Service
public class MailService {

    private final Gmail gmail;
@Autowired
    public MailService(Gmail gmail){
        this.gmail = gmail;
    }

    public void sendEmail(String to, String subject, String body) throws Exception{
        Properties properties = new Properties();
        Session session = Session.getInstance(properties, null);
        MimeMessage email = new MimeMessage(session);

        try{
            email.setFrom(new javax.mail.internet.InternetAddress("amanueltsehay11@gmail.com"));
            email.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(to));
            email.setSubject(subject);
            email.setText(body);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();

            String encodedEmail = com.google.api.client.util.Base64.encodeBase64URLSafeString(rawMessageBytes);

            Message message = new Message();
            message.setRaw(encodedEmail);
            message = gmail.users().messages().send("me", message).execute();

        }
        catch(Exception e){
           throw new EmailFailedToSendException("Email hast been sent ");
            }
        }

    }
