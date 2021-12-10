package by.itacademy.javaenterprise.borisevich.joinedtable;

import by.itacademy.javaenterprise.borisevich.joinedtable.dao.PetDAO;
import by.itacademy.javaenterprise.borisevich.joinedtable.dao.impl.PetDAOImpl;
import by.itacademy.javaenterprise.borisevich.joinedtable.entity.Pet;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PetDAOImplTests {
    private static Pet pet = new Pet();
    private static EntityManagerFactory emFactory;
    private static PetDAO petDAO;

    @BeforeAll
    public static void beforeClass() throws Exception {
        pet.setSpecies("dog");
        pet.setName("Artya");
        emFactory = Persistence.createEntityManagerFactory("by.it");
        petDAO = new PetDAOImpl(emFactory);
    }

    @Test
    public void testFindPetById() throws DAOException {
        Pet pet1 = new Pet();
        pet1.setSpecies("cat");
        pet1.setName("Leo");
        petDAO.saveOrUpdate(pet1);
        assertNotNull(petDAO.findById(pet1.getAnimalId()));
        petDAO.deleteById(pet1.getAnimalId());
    }

    @Test
    public void testSavePet() throws DAOException {
        petDAO.saveOrUpdate(pet);
        assertEquals(pet.getName(), petDAO.findById(pet.getAnimalId()).getName());
        petDAO.deleteById(pet.getAnimalId());
    }
}
