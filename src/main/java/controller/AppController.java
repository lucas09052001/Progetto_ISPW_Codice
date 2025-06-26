package controller;

import boundery.BounderyEnum;
import entity.SessionInfo;

public class AppController {

    private AppController() {
        throw new IllegalStateException("Utility class");
    }

    public static void start() {
        System.out.println("[SYSTEM] App is starting");
        BounderyDispatcher.dispatchNextBoundery();
    }

    public static void useCaseCompletion() {
        System.out.println("[SYSTEM] UseCase handling completed.");
        BounderyDispatcher.dispatchNextBoundery();
    }

    public static void errorEncounterd(){
        System.out.println("[SYSTEM] An error occurred.");
        BounderyDispatcher.dispatchNextBoundery();
    }

}
