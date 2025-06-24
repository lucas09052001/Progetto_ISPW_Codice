package Controller;

import Boundery.Authenticate_Boundery.Authenticate_Boundery_MainFrame;
import Entity.Session_Info;

public class Dispatcher {

    public void start(){
        Session_Info instance = Session_Info.get_session_info();
        instance.setNext_boundery(new Authenticate_Boundery_MainFrame());
        instance.getNext_Boundery().display_and_listen();

    }


}
