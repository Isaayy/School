package pl.edu.pwsztar;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailApi {

    private String username;
    private String password = "aa";
    private String destinationAddress;
    private String subject;
    private String msg;

    public void setUsername(String str) {
        this.username = str;
    }

    public void setDestinationAddress(String str) {
        this.destinationAddress = str;
    }

    public void setSubject(String str) {
        this.subject = str;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void sendMail() {
        Properties prop = new Properties();

        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinationAddress)
            );
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
