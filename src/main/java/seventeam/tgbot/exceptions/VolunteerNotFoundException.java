package seventeam.tgbot.exceptions;

public class VolunteerNotFoundException extends RuntimeException{
    public VolunteerNotFoundException() {
        super("Волонтёра с таким id нет!");
    }
}