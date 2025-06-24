import Boundery.BounderyEnum;
import Controller.Dispatcher;
import Entity.Session_Info;

public class Main {
    public static void main(String[] args) {
        Session_Info instance = Session_Info.get_session_info();
        instance.setBounderyEnum(BounderyEnum.AUTHENTICATE);
        Dispatcher.start();
    }
}
