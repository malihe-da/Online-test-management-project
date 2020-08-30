package ir.maktab.finalproject.serevice;

import ir.maktab.finalproject.model.dao.ConfirmationTokenDao;
import ir.maktab.finalproject.model.entity.ConfirmationToken;
import ir.maktab.finalproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@PropertySource("classpath:application.properties")
@Service
public class EmailSenderService {
    private JavaMailSender javaMailSender;
    ConfirmationTokenDao confirmationTokenDao;
    private Environment env;

    public void sendEmailToAddress(User user) {
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenDao.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmailAddress());
        mailMessage.setSubject("Please Complete Maktab Registration");
        mailMessage.setFrom(env.getProperty("from.address"));
        mailMessage.setText("To confirm your account, please click here : "
                + env.getProperty("link") + confirmationToken.getConfirmationToken());

        sendEmail(mailMessage);
    }
    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    @Autowired
    public void setConfirmationTokenDao(ConfirmationTokenDao confirmationTokenDao) {
        this.confirmationTokenDao = confirmationTokenDao;
    }
}
