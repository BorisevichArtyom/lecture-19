package by.itacademy.javaenterprise.borisevich.joinedtable.dao.impl;

import by.itacademy.javaenterprise.borisevich.joinedtable.entity.Pet;
import by.itacademy.javaenterprise.borisevich.joinedtable.dao.PetDAO;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.entity.Book;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PetDAOImpl implements PetDAO {
    private final EntityManagerFactory emFactory;

    public PetDAOImpl(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    public void saveOrUpdate(Pet pet) throws DAOException {
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            Pet pet1 = em.find(Pet.class, pet.getAnimalId());
            if (pet1 == null) {
                em.persist(pet);
            } else {
                em.merge(pet);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException("pet: " + pet, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Pet findById(Long id) throws DAOException {
        Pet pet = null;
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            pet = em.find(Pet.class, id);
            if (pet == null) {
                throw new DAOException("Cannot find pet by this id:" + id);
            }
        } catch (Exception e) {
            throw new DAOException(" id:" + id, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return pet;
    }

    @Override
    public void deleteById(Long id) throws DAOException {
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(Pet.class, id));
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
