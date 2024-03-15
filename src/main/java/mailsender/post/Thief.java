package mailsender.post;

import mailsender.model.mail.MailPackage;
import mailsender.model.pack.Package;
import mailsender.model.send.Sendable;
import mailsender.service.MailService;

public class Thief implements MailService {
    private int minValue;
    private int totalValue;

    public Thief(int minCost) {
        this.minValue = minCost;
        this.totalValue = 0;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (!(mail instanceof MailPackage)) {
            return mail;
        }

        MailPackage mailPackage = (MailPackage) mail;

        if (mailPackage.getContent().getPrice() >= minValue) {
            Package aPackage = mailPackage.getContent();
            String packageContent = mailPackage.getContent().getContent();
            totalValue += aPackage.getPrice();

            Package newPackage = new Package("stones instead of " + packageContent, 0);
            MailPackage newMailPackage = new MailPackage(mailPackage.getFrom(), mail.getTo(), newPackage);

            return newMailPackage;
        }

        return mailPackage;
    }

    public int getStolenValue() {
        return totalValue;
    }
}
