package com.service.impl;

import com.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Transactional
    @Override
    public void sendConfirmationCode(String email, String code) {
        try {
            final String username = "test123test178@gmail.com";
            final String password = "Svistylin123";
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS
            Session session = Session.getInstance(prop,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("test123test178@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Testing Gmail TLS");
            Transport.send(message);
            message.setText("Dear User, your order confirmation code is:  " + code);
        } catch (MessagingException e) {
            logger.error("Error happened while sending a code");
        }
    }
}
