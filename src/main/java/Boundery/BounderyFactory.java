package Boundery;

import Boundery.Authenticate_Boundery.Authenticate_Boundery_MainFrame;
import Boundery.Error_Boundery.Error_Boundery_MainFrame;
import Boundery.Homepage.Homepage_Boundery_MainFrame;
import Entity.Session_Info;

import javax.swing.*;

public class BounderyFactory {

    public static JFrame generateBoundery(BounderyEnum boundery){

        switch (boundery){
            case HOMEPAGE:
                System.out.println("[SYSTEM] La fabbrica di Boundery sta creando una boundery HomePage");
                return new Homepage_Boundery_MainFrame();
            case AUTHENTICATE:
                System.out.println("[SYSTEM] La fabbrica di Boundery sta creando una boundery Authenticate");
                return new Authenticate_Boundery_MainFrame();
            case ERROR:
                System.out.println("[SYSTEM] La fabbrica di Boundery sta creando una boundery Error");
                Session_Info instance = Session_Info.get_session_info();
                return new Error_Boundery_MainFrame(instance.getLastError());
            default:
                return null;
        }

    }

}
