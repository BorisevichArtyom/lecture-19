package by.itacademy.javaenterprise.borisevich.mappedsuperclass.dao.impl;

import by.itacademy.javaenterprise.borisevich.mappedsuperclass.dao.BlogPostDAO;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.entity.BlogPost;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
@Slf4j
public class BlogPostDAOImpl implements BlogPostDAO {
    private final EntityManagerFactory emFactory;

    public BlogPostDAOImpl(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    public void saveOrUpdate(BlogPost blogPost) throws DAOException {
        EntityManager em = null;
        try {
            if (blogPost.getTitle() == null) {
                throw new DAOException("blogPost: " + blogPost);
            }
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            em.persist(blogPost);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new DAOException("blogPost: " + blogPost, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    @Override
    public BlogPost findById(Long id) throws DAOException {
        BlogPost blogPost = null;
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            blogPost = em.find(BlogPost.class, id);
            if (blogPost == null) {
                throw new DAOException(" Cannot find blogPost by this id:" + id);
            }
        } catch (Exception e) {
            throw new DAOException(" id:" + id, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return blogPost;
    }


    @Override
    public void deleteById(Long id) throws DAOException {
        EntityManager em = null;
        try {
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(BlogPost.class, id));
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
