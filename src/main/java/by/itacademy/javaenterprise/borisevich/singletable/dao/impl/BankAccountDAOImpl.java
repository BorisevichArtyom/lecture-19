package by.itacademy.javaenterprise.borisevich.singletable.dao.impl;

import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;
import by.itacademy.javaenterprise.borisevich.singletable.dao.BankAccountDAO;
import by.itacademy.javaenterprise.borisevich.singletable.entity.BankAccount;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Slf4j
public class BankAccountDAOImpl implements BankAccountDAO {
    private final EntityManagerFactory emFactory;

    public BankAccountDAOImpl(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    public void saveOrUpdate(BankAccount bankAccount) throws DAOException {
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            BankAccount bankAccount2 = em.find(BankAccount.class, bankAccount.getId());
            if (bankAccount2 == null) {
                em.persist(bankAccount);
            } else {
                em.merge(bankAccount);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException("bankAccount: " + bankAccount, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public BankAccount findById(Long id) throws DAOException {
        BankAccount bankAccount = null;
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            bankAccount = em.find(BankAccount.class, id);
            if (bankAccount == null) {
                throw new DAOException("Cannot find bankAccount by this id:" + id);
            }
        } catch (Exception e) {
            throw new DAOException(" id:" + id, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return bankAccount;
    }


    @Override
    public void deleteById(Long id) throws DAOException {
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(BankAccount.class, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException(" id:" + id, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
