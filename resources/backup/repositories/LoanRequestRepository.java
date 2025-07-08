package repository;

import entity.loan.LoanInterval;
import entity.loan.loan_post.LoanPost;
import entity.loan.loan_request.LoanRequest;
import utilities.PathUtility;

import java.util.ArrayList;

public class LoanRequestRepository {

    private static LoanRequestRepository instance;
    private ArrayList<LoanRequest> loanRequestList;

    private LoanRequestRepository() {
        loanRequestList.add(new LoanRequest(
                "bob",
                new LoanPost(
                        "alice",
                        "Calcolatrice",
                        "Calcolatrice scientifica perfettamente funzionante.",
                        LoanInterval.DAY,
                        "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/Calcolatrice.png"
                )
        ));

        loanRequestList.add(new LoanRequest(
                "bob",
                new LoanPost(
                        "alice",
                        "Zaino",
                        "Zaino capiente",
                        LoanInterval.WEEK,
                        "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/Zaino.png"
                )
        ));

        loanRequestList.add(new LoanRequest(
                "carlos",
                new LoanPost(
                        "bob",
                        "Cavo HDMI",
                        "Cavo HDMI to HDMI",
                        LoanInterval.DAY,
                        "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/CavoHDMI.png"
                )
        ));

        loanRequestList.add(new LoanRequest(
                "alice",
                new LoanPost(
                        "bob",
                        "Appunti di ISPW",
                        "Per superare il corso con 30L e bacio accademico.",
                        LoanInterval.WEEK,
                        "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/Appunti.png"
                )
        ));

        loanRequestList.add(new LoanRequest(
                "alice",
                new LoanPost(
                        "bob",
                        "Cavo HDMI",
                        "Cavo HDMI to HDMI",
                        LoanInterval.DAY,
                        "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/ImageRepository/CavoHDMI.png"
                )
        ));

    }

    public static LoanRequestRepository getInstance() {
        if (instance == null) {
            instance = new LoanRequestRepository();
        }
        return instance;
    }

    public ArrayList<LoanRequest> getLoanRequestList() {
        return loanRequestList;
    }

    public void setLoanRequestList(ArrayList<LoanRequest> loanRequestList) {
        this.loanRequestList = loanRequestList;
    }

    public void addLoanRequest(LoanRequest request) {
        loanRequestList.add(request);
    }
}

