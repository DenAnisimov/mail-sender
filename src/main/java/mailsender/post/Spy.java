package mailsender.post;

import mailsender.model.mail.MailMessage;
import mailsender.model.send.Sendable;
import mailsender.service.MailService;

import java.util.logging.Logger;

public class Spy implements MailService {
    private final String AUSTIN_POWERS = "Austin Powers";
    private Logger logger;

    public Spy(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (!(mail instanceof MailMessage mailMessage)) {
            return mail;
        }
        String from = mailMessage.getFrom();
        String to = mailMessage.getTo();
        String message = mailMessage.getMessage();

        if (from.equalsIgnoreCase(AUSTIN_POWERS) || to.equalsIgnoreCase(AUSTIN_POWERS)) {
            logger.warning("Detected target mail correspondence: from " + mail.getFrom() +
                    " to " + to + " \"" + message +"\"");
        } else {
            logger.info("Usual correspondence: from " + from + " to " + to + "");
        }
        return mail;
    }
}
