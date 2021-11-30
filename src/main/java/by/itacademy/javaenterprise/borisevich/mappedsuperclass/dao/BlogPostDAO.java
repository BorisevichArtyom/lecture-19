package by.itacademy.javaenterprise.borisevich.mappedsuperclass.dao;

import by.itacademy.javaenterprise.borisevich.mappedsuperclass.entity.BlogPost;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;

public interface BlogPostDAO {

    void saveOrUpdate(BlogPost blogPost) throws DAOException;

    BlogPost findById(Long id) throws DAOException;

    void deleteById(Long id) throws DAOException;
}
