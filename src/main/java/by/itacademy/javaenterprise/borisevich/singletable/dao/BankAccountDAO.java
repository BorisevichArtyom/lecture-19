package by.itacademy.javaenterprise.borisevich.singletable.dao;

import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;
import by.itacademy.javaenterprise.borisevich.singletable.entity.BankAccount;

public interface BankAccountDAO {

    void saveOrUpdate(BankAccount bankAccount) throws DAOException;

    BankAccount findById(Long id) throws DAOException;

    void deleteById(Long id) throws DAOException;
}
