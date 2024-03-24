package mailsender.post;

import mailsender.model.mail.MailPackage;
import mailsender.model.pack.Package;
import mailsender.model.send.Sendable;
import mailsender.post.exception.IllegalPackageException;
import mailsender.post.exception.StolenPackageException;
import mailsender.service.MailService;

import static mailsender.post.InvalidPackageContent.*;

public class Inspector implements MailService {

    public Inspector() {

    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (!(mail instanceof MailPackage mailPackage)) {
            return mail;
        }

        Package aPackage = mailPackage.getContent();
        String content = aPackage.getContent().toUpperCase();

        if (content.contains(WEAPONS) || content.contains(BANNED_SUBSTANCE)) {
            throw new IllegalPackageException();
        }

        if (content.contains(STONES)) {
            throw new StolenPackageException();
        }

        return mailPackage;
    }
}
