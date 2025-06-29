package main;

import boundery.BounderyFactory;

public class AppController {

    private AppController() {
        throw new IllegalStateException("Utility class");
    }

    public static void start() {
        System.out.println("[APP-CONTROLLER] App is starting");
        BounderyFactory.generateBoundery();
    }

    public static void useCaseCompletion() {
        System.out.println("[APP-CONTROLLER] UseCase handling completed.");
        BounderyFactory.generateBoundery();
    }

    public static void errorEncounterd(){
        System.out.println("[APP-CONTROLLER][NCE] An error occurred.");
        BounderyFactory.generateBoundery();
    }

    public static void criticalErrorEncountered(){
        System.out.println("[APP-CONTROLLER][CE] A critical error occurred. Shutting down.");
        BounderyFactory.generateBoundery();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.exit(1);
    }

}
