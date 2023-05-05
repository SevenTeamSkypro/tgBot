package seventeam.tgbot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "dog_owners")
public class DogOwner extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "pets", nullable = false)
    @OneToMany(mappedBy = "dogOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dog> pets;

    public DogOwner() {
    }

    public DogOwner(Long id, String firstName, String lastName, String phoneNumber, List<Dog> pets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DogOwner dogOwner = (DogOwner) o;
        return Objects.equals(id, dogOwner.id) && firstName.equals(dogOwner.firstName) && lastName.equals(dogOwner.lastName) && phoneNumber.equals(dogOwner.phoneNumber) && pets.equals(dogOwner.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName, phoneNumber, pets);
    }
}
