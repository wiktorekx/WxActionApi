package pl.wiktorekx.wxactionapi.api.exception;

public class ActionException extends Exception {

    public ActionException() {
        super();
    }

    public ActionException(String message) {
        super(message);
    }

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
