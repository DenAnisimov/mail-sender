package mailsender.service;

import mailsender.model.send.Sendable;

public class RealMailSender implements MailService {
    @Override
    public Sendable processMail(Sendable mail) {
        // Здесь описан код настоящей системы отправки почты.
        return mail;
    }
}
