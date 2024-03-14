package mailsender.service;
import mailsender.model.send.Sendable;

public interface MailService {
    Sendable processMail(Sendable mail);
}
