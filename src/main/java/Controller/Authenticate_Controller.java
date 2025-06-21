package Controller;

import DAO.User.User_DAO;
import DAO.User.User_DAO_DB;
import DAO.User.User_DAO_File;
import DAO.User.User_DAO_NoPersistance;
import Entity.Persistency_Policy;
import Entity.Session_Info;
import Entity.User;

public class Authenticate_Controller {

    User_DAO dao;

    public Authenticate_Controller() {
    }

    public boolean start(String username, String password, Persistency_Policy persistency_policy){

        switch (persistency_policy) {
            case DB:
                dao = new User_DAO_DB();
                break;
            case FILE:
                dao = new User_DAO_File();
                break;
            case NO_PERSISTANCE:
                dao = new User_DAO_NoPersistance();
                break;
        }

        User user = dao.authenticate(username, password);

        //Aggiorna classe di sessione
        Session_Info session_info = Session_Info.get_session_info();
        session_info.setUser(user);
        session_info.setPersistency_policy(persistency_policy);
        session_info.setNext_boundery(/*HOMEPAGE_BOUNDERY*/);

    }
}
