package seventeam.tgbot.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException() {
        super("Клиента с таким id нет!");
    }
}