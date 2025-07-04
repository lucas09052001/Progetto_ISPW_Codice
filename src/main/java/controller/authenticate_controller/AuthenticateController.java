package controller.authenticate_controller;

import entity.PersistencyPolicy;

public interface AuthenticateController {
    void login(String username, String password, PersistencyPolicy persistencyPolicy);
}
