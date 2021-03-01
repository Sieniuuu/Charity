package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.email.MailService;
import pl.coderslab.charity.model.Token;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.TokenRepository;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final MailService mailService;

    public TokenService(TokenRepository tokenRepository, MailService mailService) {
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    public Optional<Token> findByValue(String value) {
        return tokenRepository.findByValue(value);
    }

    public void sendActiveToken(User user) {
        String subject = "Link aktywacyjny do portalu CharityDonation";
        String urlContent = " http://localhost:8080/register/token?value=";
        String msgContent = "Link aktywacyjny do naszego portalu! Kliknij w niego ";

        sendToken(user, urlContent, msgContent, subject);
    }

    public void sendPasswordResetToken(User user) {
        String subject = "Link do formularza restartującego hasło CharityDonation";
        String urlContent = " http://localhost:8080/login/changePassword?value=";
        String msgContent = "Link do formularza resetującego konto! Kliknij w niego ";

        sendToken(user, urlContent, msgContent, subject);
    }


    public void sendToken(User user, String urlContent, String msgContent, String subject) {
        String value = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(value);
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        String url = urlContent + value;
        String msg = msgContent;

        try {
            mailService.sendMail(user.getEmail(), subject,msg + url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }





}
