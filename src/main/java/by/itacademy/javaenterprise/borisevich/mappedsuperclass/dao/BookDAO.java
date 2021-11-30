package by.itacademy.javaenterprise.borisevich.mappedsuperclass.dao;

import by.itacademy.javaenterprise.borisevich.mappedsuperclass.entity.Book;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;

public interface BookDAO {

    void saveOrUpdate(Book book) throws DAOException;

    Book findById(Long id) throws DAOException;

    void deleteById(Long id) throws DAOException;
}
