package appcontroller_test;

import boundery.Boundaries;
import exceptions.FactoryException;
import main.AppController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.PersistencyPolicy;
import utilities.SessionInfo;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppControllerTest {
    private AppController appController;

    @BeforeEach
    void setUp(){
        appController = new AppController();
        SessionInfo.getSessionInfo().setPersistencyPolicy(PersistencyPolicy.NULL);
    }

    @Test
    void testFactoryExceptionThrown(){
        Exception exception =
                assertThrows(FactoryException.class, () -> {appController.updateNewBoundery(Boundaries.PROFILE);});
    }

}
