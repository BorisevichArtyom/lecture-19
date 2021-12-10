package by.itacademy.javaenterprise.borisevich.singletable;

import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;
import by.itacademy.javaenterprise.borisevich.singletable.dao.BankAccountDAO;
import by.itacademy.javaenterprise.borisevich.singletable.dao.impl.BankAccountDAOImpl;
import by.itacademy.javaenterprise.borisevich.singletable.entity.BankAccount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountDaoImplTests {
    private static BankAccount bankAccount = new BankAccount();
    private static EntityManagerFactory emFactory;
    private static BankAccountDAO bankAccountDAO;

    @BeforeAll
    public static void beforeClass() throws Exception {
        bankAccount.setAccount(111222333);
        bankAccount.setBankName("Goldman Sachs");
        bankAccount.setSwift("GOLDUS33");
        bankAccount.setOwner("Donald Trump");
        emFactory= Persistence.createEntityManagerFactory("by.it");
        bankAccountDAO=new BankAccountDAOImpl(emFactory);
    }

    @Test
    public void testFindBankAccountById() throws DAOException {
        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setOwner("Artyom Borisevich");
        bankAccount1.setAccount(1122);
        bankAccount1.setBankName("Goldman Sachs");
        bankAccount1.setSwift("GOLDUS33");
        bankAccountDAO.saveOrUpdate(bankAccount1);
        assertNotNull(bankAccountDAO.findById(bankAccount1.getId()));
        bankAccountDAO.deleteById(bankAccount1.getId());
    }

    @Test
    public void testSaveBankAccount() throws DAOException {
        bankAccountDAO.saveOrUpdate(bankAccount);
        assertEquals(bankAccount.getOwner(),bankAccountDAO.findById(bankAccount.getId()).getOwner());
        bankAccountDAO.deleteById(bankAccount.getId());
    }

}
