package main;

import boundery.BounderyEnum;
import entity.SessionInfo;
import entity.User;

public class Main {
    public static void main(String[] args) {
        SessionInfo sessionInfo = SessionInfo.getSessionInfo();
        //sessionInfo.setNextBoundery(BounderyEnum.AUTHENTICATE);   QUESTA E' QUELLA CORRETTA

        // QUESTO PEZZO DI CODICE E' DEBUGGING CONSIST NEL CODICE PER EVITARE DI FARE IL LOG IN OGNI VOLTA. TUTTO QUELLO CHE STA TRA QUESTI COMMENTI
        // DOVREBBE ESSERE ELIMINATO PRIMA DELLA CONSEGNA E NON CONSIDEATO

        sessionInfo.setNextBoundery(BounderyEnum.HOMEPAGE);
        User user = new User("cicciobello", "cicciobello", 2.3f,3);
        sessionInfo.setUser(user);
         // TBR = To Be Removed


        AppController.start();
    }
}
