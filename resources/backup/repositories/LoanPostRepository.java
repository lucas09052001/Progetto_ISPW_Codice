package repository;
import entity.loan.LoanInterval;
import entity.loan.loan_post.LoanPost;
import utilities.PathUtility;

import java.util.ArrayList;

public class LoanPostRepository {

    private static LoanPostRepository instance;
    private ArrayList<LoanPost> loanPostList;

    private LoanPostRepository() {
        loanPostList = new ArrayList<>();

        // Dati di esempio
        loanPostList.add(new LoanPost(
                "alice",
                "Calcolatrice",
                "Calcolatrice scientifica perfettamente funzionante.",
                LoanInterval.DAY,
                "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/Calcolatrice.png"
        ));

        loanPostList.add(new LoanPost(
                "alice",
                "Zaino",
                "Zaino capiente.",
                LoanInterval.WEEK,
                "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/ZainoTrekking.png"
        ));

        loanPostList.add(new LoanPost(
                "bob",
                "Appunti ISPW",
                "Per superare il corso con 30L e bacio accademico.",
                LoanInterval.WEEK,
                "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/Appunti.png"
        ));

        loanPostList.add(new LoanPost(
                "bob",
                "Cavo HDMI",
                "Cavo HDMI to HDMI.",
                LoanInterval.DAY,
                "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/CavoHDMI.png"
        ));

        loanPostList.add(new LoanPost(
                "carlos",
                "Matita",
                "Temperata",
                LoanInterval.MONTH,
                ""
        ));

    }

    public static LoanPostRepository getInstance() {
        if (instance == null) {
            instance = new LoanPostRepository();
        }
        return instance;
    }

    public ArrayList<LoanPost> getLoanPostList() {
        return loanPostList;
    }

    public void setLoanPostList(ArrayList<LoanPost> loanPostList) {
        this.loanPostList = loanPostList;
    }

    public void addLoanPost(LoanPost post) {
        loanPostList.add(post);
    }
}
