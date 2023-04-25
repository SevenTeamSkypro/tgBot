package seventeam.tgbot.model;

public abstract class Shelter {
    private String address;

    public Shelter(String address) {
        this.address = address;
    }

    public Shelter() {

    }

    public String getAddress() {
        return address;
    }
}
