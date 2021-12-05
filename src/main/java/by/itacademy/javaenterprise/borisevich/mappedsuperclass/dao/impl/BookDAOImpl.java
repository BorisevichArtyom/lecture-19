package by.itacademy.javaenterprise.borisevich.mappedsuperclass.dao.impl;

import by.itacademy.javaenterprise.borisevich.mappedsuperclass.dao.BookDAO;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.entity.BlogPost;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.entity.Book;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Slf4j
public class BookDAOImpl implements BookDAO {
    private final EntityManagerFactory emFactory;

    public BookDAOImpl(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    public void saveOrUpdate(Book book) throws DAOException {
        EntityManager em = null;
        try {
            if (book.getTitle() == null) {
                throw new DAOException("book: " + book);
            }
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            Book book1 = em.find(Book.class, book.getId());
            if (book1 == null) {
                em.persist(book);
            } else {
                em.merge(book);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException("book: " + book, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Book findById(Long id) throws DAOException {
        Book book = null;
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            book = em.find(Book.class, id);
            if (book == null) {
                throw new DAOException("Cannot find book by this id:" + id);
            }
        } catch (Exception e) {
            throw new DAOException(" id:" + id, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return book;
    }


    @Override
    public void deleteById(Long id) throws DAOException {
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(Book.class, id));
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
