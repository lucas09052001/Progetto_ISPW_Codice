package appcontroller_test;

import boundery.Boundaries;
import boundery.profile_boundery.ProfileCentralPanelV1;
import controller.profile_controller.ProfileControllerV1;
import dao.user_dao.UserDAONoPersistance;
import main.AppController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
