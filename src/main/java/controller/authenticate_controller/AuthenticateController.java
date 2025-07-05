package controller.authenticate_controller;

import utilities.PersistencyPolicy;

public interface AuthenticateController {
    void login(String username, String password, PersistencyPolicy persistencyPolicy);
}
