package by.epam.project.command;
import com.sun.mail.smtp.SMTPTransport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Класс для отправки на емеил пользователя письма об успешной регисрации
 *
 * @author Шпакова А.
 */

public class MailSenderCommand extends Thread {
    private static final Logger logger = LogManager.getLogger();

    private String login;
    private String password;
    private String recipientMail;
    private String message;
    private String title;

    public MailSenderCommand(String login, String password, String recipientMail, String message, String title) {
        this.login = login;
        this.password = password;
        this.recipientMail = recipientMail;
        this.message = message;
        this.title=title;
    }

    @Override
    public void run() {
        Properties sessionConfig = new Properties();
        // loading mail server parameters into mail session properties
        sessionConfig.setProperty("mail.smtps.host", "smtp.gmail.com");
        sessionConfig.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sessionConfig.setProperty("mail.smtp.socketFactory.fallback", "false");
        sessionConfig.setProperty("mail.smtp.socketFactory.port", "465");
        sessionConfig.setProperty("mail.smtp.port", "465");
        sessionConfig.setProperty("mail.smtps.auth", "true");
        sessionConfig.setProperty("mail.transport.protocol", "smtp");
        sessionConfig.setProperty("mail.smtp.quitwait", "false");

        Session session = Session.getInstance(sessionConfig); // default session
        MimeMessage mimeMessage = new MimeMessage(session);   // email message
        try {
            mimeMessage.setFrom(new InternetAddress(login));  // setting header fields
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientMail, false));
            mimeMessage.setSubject(title);
            mimeMessage.setText(message, "UTF-8");
            mimeMessage.setSentDate(new Date());
            SMTPTransport transport = (SMTPTransport) session.getTransport("smtps");
                transport.connect("smtp.gmail.com", login, password);
                transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
                transport.close();

        } catch (MessagingException e) {
            logger.error("Error with MimeMessage object", e);
        }
        logger.debug("Mail completed successfully");
    }
}

