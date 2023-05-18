package seventeam.tgbot.exception;

import java.util.NoSuchElementException;

public class InfoNotFoundException extends NoSuchElementException {
    public InfoNotFoundException (String message){
        super(message);
    }
}
