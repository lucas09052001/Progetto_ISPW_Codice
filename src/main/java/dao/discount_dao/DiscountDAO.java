package dao.discount_dao;

import entity.discount.Discount;
import exceptions.DAOException;

import java.util.ArrayList;

public interface DiscountDAO {
    ArrayList<Discount> fetchAll() throws DAOException;
    void redeem(Discount discount) throws DAOException;

}
