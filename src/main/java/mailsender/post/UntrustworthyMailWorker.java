package mailsender.post;

import mailsender.model.send.Sendable;
import mailsender.service.MailService;
import mailsender.service.RealMailService;

public class UntrustworthyMailWorker implements MailService {
    private MailService[] mailServices;
    private RealMailService realMailService;

    public UntrustworthyMailWorker(MailService[] mailServices) {
        this.mailServices = mailServices;
        realMailService = new RealMailService();
    }

    public RealMailService getRealMailService() {
        return realMailService;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        Sendable process = mail;

        for (int i = 0; i < mailServices.length; i++) {
            process = mailServices[i].processMail(process);
        }

        return realMailService.processMail(mail);
    }
}
