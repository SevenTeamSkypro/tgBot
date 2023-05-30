package seventeam.tgbot.exceptions;

public class NothingToReadException extends RuntimeException {
    public NothingToReadException() {
        super("Такого файла нет!");
    }
}