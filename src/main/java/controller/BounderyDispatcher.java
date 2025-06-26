package controller;

import boundery.BounderyEnum;

import static boundery.BounderyFactory.generateBoundery;

public class BounderyDispatcher {

    private BounderyDispatcher() {
        throw new IllegalStateException("Utility class");
    }

    public static void dispatchNextBoundery(){
        System.out.println("[SYSTEM] Dispatching next boundery");
        generateBoundery();
    }

}
