package home_explorer.exception;

public class InvalidCommandException extends RuntimeException{
    public InvalidCommandException(){
        super();
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
