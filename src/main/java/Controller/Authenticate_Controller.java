package Controller;

import Boundery.Authenticate_Boundery.Authenticate_Boundery_MainFrame;
import Boundery.Error_Boundery.Error_Boundery_MainFrame;
import Boundery.Homepage.Homepage_Boundery_MainFrame;
import DAO.User.User_DAO;
import DAO.User.User_DAO_DB;
import DAO.User.User_DAO_File;
import DAO.User.User_DAO_NoPersistance;
import Entity.Persistency_Policy;
import Entity.Session_Info;
import Entity.User;
import Exceptions.DAO_Exception;

import java.util.jar.JarOutputStream;

public class Authenticate_Controller {

    User_DAO dao;

    public Authenticate_Controller() {
    }

    public void login(String username, String password, Persistency_Policy persistency_policy){

        Session_Info session_info = Session_Info.get_session_info();

        try {
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
                case NULL:
                    System.out.println("[EE] No persistency policy was selected");
                    throw new IllegalArgumentException("No persistency policy was selected");   //No persistency_policy was selected
            }

            User user = dao.authenticate(username, password);

            //Aggiorna classe di sessione
            session_info.setUser(user);
            session_info.setPersistency_policy(persistency_policy);
            session_info.setNext_boundery(new Homepage_Boundery_MainFrame());

        } catch (IllegalArgumentException | DAO_Exception e) {
            new Error_Boundery_MainFrame(e.getMessage()).display_and_listen();
            session_info.setNext_boundery(new Authenticate_Boundery_MainFrame());
        } finally {
            session_info.getNext_Boundery().display_and_listen();
        }

    }
}
