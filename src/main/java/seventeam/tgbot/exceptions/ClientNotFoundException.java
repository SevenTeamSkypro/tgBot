package seventeam.tgbot.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String message) {
        super(message);
    }
}
