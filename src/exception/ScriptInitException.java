package exception;

public class ScriptInitException extends RuntimeException {
    public ScriptInitException() {
        super("Error starting the script");
    }
}
