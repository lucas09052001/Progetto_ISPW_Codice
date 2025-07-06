package controller_test;

import controller.authenticate_controller.AuthenticateController;
import controller.authenticate_controller.AuthenticateControllerV1;
import controller.loanitem_controller.LoanItemController;
import main.AppController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.PersistencyPolicy;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ControllerTest {

    private AppController appController;

    @BeforeEach
    void setUp(){
        appController = new AppController();
    }

    @Test
    void LoanItemTest(){

    }

}
