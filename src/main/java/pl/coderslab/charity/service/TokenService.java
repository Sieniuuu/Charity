package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.email.MailService;
import pl.coderslab.charity.model.Token;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.TokenRepository;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final MailService mailService;

    public TokenService(TokenRepository tokenRepository, MailService mailService) {
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    public void sendToken(User user) {
        String value = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(value);
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        String url = " http://localhost:8080/register/token?value=" + value;
        String msg = "Link aktywacyjny do naszego portalu! Kliknij w niego ";

        try {
            mailService.sendMail(user.getEmail(), "Link aktywacyjny do portalu CharityDonation",
                    msg + url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
