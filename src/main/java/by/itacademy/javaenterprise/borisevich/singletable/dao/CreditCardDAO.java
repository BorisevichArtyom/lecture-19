package by.itacademy.javaenterprise.borisevich.singletable.dao;

import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;
import by.itacademy.javaenterprise.borisevich.singletable.entity.BankAccount;
import by.itacademy.javaenterprise.borisevich.singletable.entity.CreditCard;

public interface CreditCardDAO {

    void saveOrUpdate(CreditCard creditCard) throws DAOException;

    CreditCard findById(Long id) throws DAOException;

    void deleteById(Long id) throws DAOException;
}
