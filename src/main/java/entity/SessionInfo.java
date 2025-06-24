package entity;

import boundery.BounderyEnum;

public class SessionInfo {
    private static SessionInfo session_info;

    //Attributi per funzionamento (User, NextBoundery)
    private User user;
    private PersistencyPolicy persistency_policy;
    private BounderyEnum bounderyEnum;
    private String lastError;

    private SessionInfo(){}

    public static SessionInfo get_session_info(){
        if(session_info == null){
            session_info = new SessionInfo();
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

    public PersistencyPolicy getPersistency_policy() {
        return persistency_policy;
    }

    public void setPersistency_policy(PersistencyPolicy persistency_policy) {
        this.persistency_policy = persistency_policy;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }
}
