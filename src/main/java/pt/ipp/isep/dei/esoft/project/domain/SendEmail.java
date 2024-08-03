package pt.ipp.isep.dei.esoft.project.domain;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Send email.
 */
public class SendEmail {

    /**
     * Email send boolean.
     *
     * @param toEmail             the to email
     * @param messageVisitRequest the message visit request
     * @return the boolean
     */
    public static boolean emailSend(String toEmail, String messageVisitRequest) {


        String to = toEmail;
        String from = "g45noreplytestmail@gmail.com";
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream("src/main/resources/config.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("g45noreplytestmail@gmail.com", "bvfuaavmcrikhuoz");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Email for visitRequest");
            message.setText(messageVisitRequest);
            Transport.send(message);
            return true;
            //System.out.println("success");
        } catch (MessagingException messageException) {
            messageException.printStackTrace();
            return false;
        }


    }

}

