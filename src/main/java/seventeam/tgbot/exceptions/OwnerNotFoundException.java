package seventeam.tgbot.exceptions;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException() {
        super("Владельца с таким id нет!");
    }
}