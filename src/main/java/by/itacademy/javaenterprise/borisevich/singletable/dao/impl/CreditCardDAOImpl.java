package by.itacademy.javaenterprise.borisevich.singletable.dao.impl;


import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;
import by.itacademy.javaenterprise.borisevich.singletable.dao.CreditCardDAO;
import by.itacademy.javaenterprise.borisevich.singletable.entity.BankAccount;
import by.itacademy.javaenterprise.borisevich.singletable.entity.CreditCard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CreditCardDAOImpl implements CreditCardDAO {
    private final EntityManagerFactory emFactory;

    public CreditCardDAOImpl(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    public void saveOrUpdate(CreditCard creditCard) throws DAOException {
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            CreditCard creditCard2 = em.find(CreditCard.class, creditCard.getId());
            if (creditCard2 == null) {
                em.persist(creditCard);
            } else {
                em.merge(creditCard);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException("creditCard: " + creditCard, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public CreditCard findById(Long id) throws DAOException {
        CreditCard creditCard = null;
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            creditCard = em.find(CreditCard.class, id);
            if (creditCard == null) {
                throw new DAOException("Cannot find creditCard by this id:" + id);
            }
        } catch (Exception e) {
            throw new DAOException(" id:" + id, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return creditCard;
    }


    @Override
    public void deleteById(Long id) throws DAOException {
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(CreditCard.class, id));
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
