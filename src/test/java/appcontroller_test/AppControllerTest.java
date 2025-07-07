package appcontroller_test;

import main.AppController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppControllerTest {

    private AppController appController;

    @BeforeEach
    void setUp(){
        appController = new AppController();
    }

    @Test
    void exceptionThrowing(){
        assertThrows(RuntimeException.class, () -> {appController.updateNewBoundary(null);} );
    }

}
