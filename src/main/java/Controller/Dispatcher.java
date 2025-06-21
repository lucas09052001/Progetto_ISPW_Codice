package Controller;

import Boundery.Authenticate_Boundery.Authenticate_Boundery;
import Entity.User;

public class Dispatcher {

    public void start(){
        Authenticate_Boundery authenticateBoundery = new Authenticate_Boundery();
        User user = new User();
        authenticateBoundery.display_and_listen();

        //LOOP per prossima boundery

    }


}
