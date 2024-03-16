package mailsender.post.exception;

public class StolenPackageException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Package contains stones";
    }
}
