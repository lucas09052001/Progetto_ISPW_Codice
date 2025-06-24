package Controller;

import Entity.Session_Info;

import static Boundery.BounderyFactory.generateBoundery;

public class Dispatcher {

    public static void start(){
        Session_Info instance = Session_Info.get_session_info();
        System.out.println("[SYSTEM] Showing next_Boundery");
        generateBoundery(instance.getBounderyEnum());
    }


}
