package mailsender.post.exception;

public class IllegalPackageException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Package contains weapons or banned substance";
    }
}
