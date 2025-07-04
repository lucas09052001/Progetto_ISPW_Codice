package controller.profile_controller;

import dao.user_dao.UserDAO;
import dao.user_dao.UserDAOFactory;
import entity.SessionInfo;
import main.Observer;

public class ProfileControllerFactory {

    SessionInfo sessionInfo;
    UserDAOFactory daoFactory;

    public ProfileControllerFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
        this.daoFactory = new UserDAOFactory();
    }

    public ProfileController generate(Observer observer){
        System.out.println("    [PROFILE-CONTROLLER-FACTORY] Generating");
        ProfileController returnee;

        UserDAO dao = daoFactory.generate();

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new ProfileControllerV1(observer, dao);
            }
            case null, default -> {
                System.out.println("    [PROFILE-CONTROLLER-FACTORY] Reached unreachable code");
                throw new RuntimeException("[PROFILE-CONTROLLER-FACTORY] Reached unreachable code");
            }
        }
        System.out.println("    [PROFILE-CONTROLLER-FACTORY] Returning");
        return returnee;
    }

}
