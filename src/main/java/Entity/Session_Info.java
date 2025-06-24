package Entity;

import Boundery.BounderyEnum;

public class Session_Info {
    private static Session_Info session_info;

    //Attributi per funzionamento (User, NextBoundery)
    private User user;
    private Persistency_Policy persistency_policy;
    private BounderyEnum bounderyEnum;
    private String lastError;

    private Session_Info(){}

    public static Session_Info get_session_info(){
        if(session_info == null){
            session_info = new Session_Info();
        }
        return session_info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BounderyEnum getBounderyEnum() {
        return bounderyEnum;
    }

    public void setBounderyEnum(BounderyEnum bounderyEnum) {
        this.bounderyEnum = bounderyEnum;
    }

    public Persistency_Policy getPersistency_policy() {
        return persistency_policy;
    }

    public void setPersistency_policy(Persistency_Policy persistency_policy) {
        this.persistency_policy = persistency_policy;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }
}
