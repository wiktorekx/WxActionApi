package pl.wiktorekx.wxactionapi.api.exception;

public class NoFoundActionException extends ActionException {
    private final String actionName;

    public NoFoundActionException(String actionName) {
        this.actionName = actionName;
    }

    public NoFoundActionException(String message, String actionName) {
        super(message);
        this.actionName = actionName;
    }

    public NoFoundActionException(String message, Throwable cause, String actionName) {
        super(message, cause);
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }
}
