package entity;

import boundery.BounderyEnum;

public class SessionInfo {
    private static SessionInfo sessionInfo;

    private User user;
    private PersistencyPolicy persistencyPolicy;
    private BounderyEnum nextBoundery;
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

    public BounderyEnum getNextBoundery() {
        return nextBoundery;
    }
    public void setNextBoundery(BounderyEnum bounderyEnum) {
        this.nextBoundery = bounderyEnum;
    }

    public PersistencyPolicy getPersistencyPolicy() {return persistencyPolicy;}
    public void setPersistencyPolicy(PersistencyPolicy persistencyPolicy) {this.persistencyPolicy = persistencyPolicy;}

    public String getLastError() {return lastError;}
    public void setLastError(String lastError) {this.lastError = lastError;}
}
