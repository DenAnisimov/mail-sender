package mailsender.service;

import mailsender.model.send.Sendable;

public class RealMailService implements MailService {
    @Override
    public Sendable processMail(Sendable mail) {
        // Здесь описан код настоящей системы отправки почты.
        return mail;
    }
}
