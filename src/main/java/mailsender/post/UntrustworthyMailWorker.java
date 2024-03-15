package mailsender.post;

import mailsender.model.send.Sendable;
import mailsender.service.MailService;
import mailsender.service.RealMailSender;

import java.util.List;

public class UntrustworthyMailWorker implements MailService {
    private MailService[] mailServices;
    private RealMailSender realMailSender;

    public UntrustworthyMailWorker(MailService[] mailServices) {
        this.mailServices = mailServices;
        realMailSender = new RealMailSender();
    }

    public RealMailSender getRealMailSender() {
        return realMailSender;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        Sendable process = mail;

        for (int i = 0; i < mailServices.length; i++) {
            process = mailServices[i].processMail(process);
        }

        return realMailSender.processMail(mail);
    }
}
