package seventeam.tgbot.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "dog_owners")
public class DogOwner extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "passport", nullable = false)
    private String passport;
    @Column(name = "pets", nullable = false)
    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dog> pets;
}
