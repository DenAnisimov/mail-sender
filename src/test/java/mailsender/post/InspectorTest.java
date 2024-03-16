package mailsender.post;

import mailsender.model.mail.MailMessage;
import mailsender.model.mail.MailPackage;
import mailsender.model.pack.Package;
import mailsender.post.exception.IllegalPackageException;
import mailsender.post.exception.StolenPackageException;
import mailsender.service.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class InspectorTest {
    private MailService inspector;

    @BeforeEach
    public void setUp() {
        inspector = new Inspector();
    }

    @Test
    @DisplayName("Test none exception throw with package")
    public void testNoneExceptionWithPackage() {
        MailPackage regularPackage = new MailPackage("Romeo", "Juliet", new Package("Flowers", 25));
        assertDoesNotThrow(() -> inspector.processMail(regularPackage));
    }

    @Test
    @DisplayName("Test none exception throw with message")
    public void testNoneExceptionWithMessage() {
        MailMessage message = new MailMessage("Romeo", "Juliet", "I love you!");
        assertDoesNotThrow(() -> inspector.processMail(message));
    }

    @Test
    @DisplayName("Test banned substance throw IllegalPackageException")
    public void testBannedSubstanceThrowIllegalPackageException() {
        MailPackage bannedSubstance = new MailPackage("Romeo", "Juliet", new Package("Banned substance", 15));
        assertThrowsExactly(IllegalPackageException.class, () -> inspector.processMail(bannedSubstance));
    }

    @Test
    @DisplayName("Test weapons throw IllegalPackageException")
    public void testWeaponsThrowIllegalPackageException() {
        MailPackage weapons = new MailPackage("Austin Powers", "James Bond", new Package("Weapons", 5));
        assertThrowsExactly(IllegalPackageException.class, () -> inspector.processMail(weapons));
    }

    @Test
    @DisplayName("Test stolen package throw StolenPackageException")
    public void testStolenPackageThrowStolenPackageException() {
        MailPackage regularPackage = new MailPackage("Romeo", "Juliet", new Package("Flowers", 25));
        Thief thief = new Thief(20);
        MailPackage finalRegularPackage = (MailPackage) thief.processMail(regularPackage);

        assertThrowsExactly(StolenPackageException.class, () -> inspector.processMail(finalRegularPackage));
    }
}
