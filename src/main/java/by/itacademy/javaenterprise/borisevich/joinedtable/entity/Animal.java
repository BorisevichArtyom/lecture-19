package by.itacademy.javaenterprise.borisevich.joinedtable.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long animalId;
    @Column(nullable = false)
    private String species;

    @Override
    public String toString() {
        return "Animal{" +
                "animalId=" + animalId +
                ", species='" + species + '\'' +
                '}';
    }
}
