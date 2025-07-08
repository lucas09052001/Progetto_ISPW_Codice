package repository;

import entity.discount.Discount;

import java.util.ArrayList;

public class DiscountRepository {

    private static DiscountRepository instance;
    private ArrayList<Discount> listaDiscounts;

    private DiscountRepository() {
        listaDiscounts = new ArrayList<>();

        // Sconti di esempio
        listaDiscounts.add(new Discount("SummerSale", "/images/summer.png", 20, 100, null));
        listaDiscounts.add(new Discount("WinterBlowout", "/images/winter.png", 30, 150, null));
        listaDiscounts.add(new Discount("Paninozzo", "/images/Panino.png", 20, 100, null));
        listaDiscounts.add(new Discount("Bibita", "/images/Bibita.png", 20, 100, null));
        listaDiscounts.add(new Discount("BlackFriday", "/images/blackfriday.jpg", 50, 200, "alice"));
        listaDiscounts.add(new Discount("CyberMonday", "/images/cyber.jpg", 40, 180, "alice"));
        listaDiscounts.add(new Discount("SpringFever", "/images/spring.jpg", 25, 120, "bob"));

    }

    public static DiscountRepository getInstance() {
        if (instance == null) {
            instance = new DiscountRepository();
        }
        return instance;
    }

    public ArrayList<Discount> getDiscountList() {
        return listaDiscounts;
    }

    public void setDiscountList(ArrayList<Discount> listaDiscounts) {
        this.listaDiscounts = listaDiscounts;
    }
}
