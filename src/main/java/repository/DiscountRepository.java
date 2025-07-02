package repository;

import entity.discount.Discount;

import java.util.ArrayList;
import java.util.List;

public class DiscountRepository {

    private static DiscountRepository instance;
    private ArrayList<Discount> listaSconti;

    private DiscountRepository() {
        listaSconti = new ArrayList<>();

        // Sconti di esempio
        listaSconti.add(new Discount("SummerSale", "/images/summer.jpg", 20, 100, "alice"));
        listaSconti.add(new Discount("WinterBlowout", "/images/winter.jpg", 30, 150, null));
        listaSconti.add(new Discount("BlackFriday", "/images/blackfriday.jpg", 50, 200, null));
        listaSconti.add(new Discount("CyberMonday", "/images/cyber.jpg", 40, 180, "bob"));
        listaSconti.add(new Discount("SpringFever", "/images/spring.jpg", 25, 120, null));
    }

    public static DiscountRepository getInstance() {
        if (instance == null) {
            instance = new DiscountRepository();
        }
        return instance;
    }

    public ArrayList<Discount> getDiscountList() {
        return listaSconti;
    }

    public void setDiscountList(ArrayList<Discount> listaSconti) {
        this.listaSconti = listaSconti;
    }
}
