package by.itacademy.javaenterprise.borisevich.mappedsuperclass;

import by.itacademy.javaenterprise.borisevich.mappedsuperclass.entity.Book;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.dao.BookDAO;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.dao.impl.BookDAOImpl;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookDAOImplTests {
    private static Book book = new Book();
    private static EntityManagerFactory emFactory;
    private static BookDAO bookDAO;

    @BeforeAll
    public static void beforeClass() throws Exception {
        book.setPages(100);
        book.setTitle("Hustler");
        book.setPublishingDate(new Date(1212121212121L));
        book.setVersion(2);
        emFactory=Persistence.createEntityManagerFactory("by.it");
        bookDAO= new BookDAOImpl(emFactory);
    }

    @Test
    public void testFindBookById() throws DAOException {
        assertNotNull(bookDAO.findById(1L));
        assertThrows(DAOException.class, () -> bookDAO.findById(0L));
    }

    @Test
    public void testSaveBook() throws DAOException {
        bookDAO.saveOrUpdate(book);
        assertEquals(book.getTitle(),bookDAO.findById(book.getId()).getTitle());
        bookDAO.deleteById(book.getId());
    }

    @Test
    public void testSaveBookNull() {
        assertThrows(DAOException.class, () ->  bookDAO.saveOrUpdate(new Book()));
    }

}
