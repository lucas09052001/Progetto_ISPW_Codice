package entity;

import boundery.BounderyEnum;

public class SessionInfo {
    private static SessionInfo sessionInfo;

    //Attributi per funzionamento (User, NextBoundery)
    private User user;
    private PersistencyPolicy persistencyPolicy;
    private BounderyEnum bounderyEnum;
    private String lastError;

    private SessionInfo(){}

    public static SessionInfo getSessionInfo(){
        if(sessionInfo == null){
            sessionInfo = new SessionInfo();
        }
        return sessionInfo;
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

    public PersistencyPolicy getPersistencyPolicy() {
        return persistencyPolicy;
    }

    public void setPersistencyPolicy(PersistencyPolicy persistencyPolicy) {
        this.persistencyPolicy = persistencyPolicy;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }
}
