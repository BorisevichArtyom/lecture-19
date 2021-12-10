package by.itacademy.javaenterprise.borisevich.mappedsuperclass;

import by.itacademy.javaenterprise.borisevich.mappedsuperclass.entity.BlogPost;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.dao.BlogPostDAO;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.dao.impl.BlogPostDAOImpl;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BlogPostDAOImplTests {
    private static BlogPost blogPost = new BlogPost();
    private static EntityManagerFactory emFactory;
    private static BlogPostDAO blogPostDAO;

    @BeforeAll
    public static void beforeClass() throws Exception {
        blogPost.setTitle("article");
        blogPost.setPublishingDate(new Date(1212121212121L));
        blogPost.setUrl("https://developer.mozilla.org");
        blogPost.setVersion(3);
        emFactory= Persistence.createEntityManagerFactory("by.it");
        blogPostDAO=new BlogPostDAOImpl(emFactory);
    }

    @Test
    public void testFindBlogpostById() throws DAOException {
        assertNotNull(blogPostDAO.findById(1L));
        assertThrows(DAOException.class, () -> blogPostDAO.findById(0L));
    }

    @Test
    public void testSaveBlogpost() throws DAOException {
        blogPostDAO.saveOrUpdate(blogPost);
        assertEquals(blogPost.getTitle(),blogPostDAO.findById(blogPost.getId()).getTitle());
        blogPostDAO.deleteById(blogPost.getId());
    }

    @Test
    public void testSaveBlogpostNull() {
        assertThrows(DAOException.class, () -> blogPostDAO.saveOrUpdate(new BlogPost()));
    }
}
