package mailsender.post;

import mailsender.model.mail.MailPackage;
import mailsender.model.pack.Package;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThiefTest {
    private static MailPackage[] mailPackages;
    @BeforeAll
    public static void setUp() {
        mailPackages = new MailPackage[3];
        mailPackages[0] = new MailPackage("Romeo", "Juliet", new Package("Flowers", 15));
        mailPackages[1] = new MailPackage("Romeo", "Juliet", new Package("Flowers", 25));
        mailPackages[2] = new MailPackage("Austin Powers", "James Bond", new Package("weapons", 5));
    }

    @Test
    @DisplayName("Test method processMail")
    public void testProcessMail() {
        Thief thief = new Thief(5);
        final MailPackage expected = new MailPackage("Romeo", "Juliet", new Package("stones instead of Flowers", 0));
        MailPackage actual = (MailPackage) thief.processMail(mailPackages[0]);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetStolenValue() {
        Thief thief = new Thief(5);

        for (MailPackage mailPackage : mailPackages) {
            thief.processMail(mailPackage);
        }

        final int expected = 45;
        int actual = thief.getStolenValue();

        assertEquals(expected, actual);
    }
}
