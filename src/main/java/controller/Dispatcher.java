package controller;

import entity.SessionInfo;

import static boundery.BounderyFactory.generateBoundery;

public class Dispatcher {

    private Dispatcher() {
        throw new IllegalStateException("Utility class");
    }

    public static void start(){
        SessionInfo instance = SessionInfo.get_session_info();
        System.out.println("[SYSTEM] Showing next_Boundery");
        generateBoundery(instance.getBounderyEnum());
    }


}
