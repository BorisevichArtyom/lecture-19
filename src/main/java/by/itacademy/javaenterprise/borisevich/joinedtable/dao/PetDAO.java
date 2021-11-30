package by.itacademy.javaenterprise.borisevich.joinedtable.dao;

import by.itacademy.javaenterprise.borisevich.joinedtable.entity.Pet;
import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;

public interface PetDAO {

    void saveOrUpdate(Pet pet) throws DAOException;

    Pet findById(Long id) throws DAOException;

    void deleteById(Long id) throws DAOException;
}
