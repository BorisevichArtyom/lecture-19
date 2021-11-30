package by.itacademy.javaenterprise.borisevich.singletable;

import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;
import by.itacademy.javaenterprise.borisevich.singletable.dao.CreditCardDAO;
import by.itacademy.javaenterprise.borisevich.singletable.dao.impl.CreditCardDAOImpl;
import by.itacademy.javaenterprise.borisevich.singletable.entity.CreditCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreditCardDAOImplTest {
    private static CreditCard creditCard = new CreditCard();
    private static EntityManagerFactory emFactory;
    private static CreditCardDAO creditCardDAO;

    @BeforeAll
    public static void beforeClass() throws Exception {
        creditCard.setCardNumber(44411111);
        creditCard.setExpMonth("Jan");
        creditCard.setExpYear("2017");
        creditCard.setOwner("Bill Gates");
        emFactory= Persistence.createEntityManagerFactory("by.it");
        creditCardDAO=new CreditCardDAOImpl(emFactory);
    }

    @Test
    public void testFindCreditCardById() throws DAOException {
        CreditCard creditCard1 = new CreditCard();
        creditCard1.setCardNumber(11);
        creditCard1.setExpMonth("Jun");
        creditCard1.setExpYear("2017");
        creditCard1.setOwner("Artyom Borisevich");
        creditCardDAO.saveOrUpdate(creditCard1);
        assertNotNull(creditCardDAO.findById(creditCard1.getId()));
        creditCardDAO.deleteById(creditCard1.getId());
    }

    @Test
    public void testSaveCreditCard() throws DAOException {
        creditCardDAO.saveOrUpdate(creditCard);
        assertEquals(creditCard.getOwner(),creditCardDAO.findById(creditCard.getId()).getOwner());
        creditCardDAO.deleteById(creditCard.getId());
    }
}
