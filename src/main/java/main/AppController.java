package main;

import boundery.BounderyFactory;

public class AppController {

    private AppController() {
        throw new IllegalStateException("Utility class");
    }

    public static void start() {
        System.out.println("[SYSTEM] App is starting");
        BounderyFactory.generateBoundery();
    }

    public static void useCaseCompletion() {
        System.out.println("[SYSTEM] UseCase handling completed.");
        BounderyFactory.generateBoundery();
    }

    public static void errorEncounterd(){
        System.out.println("[SYSTEM] An error occurred.");
        BounderyFactory.generateBoundery();
    }

}
