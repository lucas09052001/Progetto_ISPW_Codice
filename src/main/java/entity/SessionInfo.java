package entity;

import boundery.BounderyEnum;
import boundery.BounderyFactory;

public class SessionInfo {
    private static SessionInfo sessionInfo;

    private String username;
    private PersistencyPolicy persistencyPolicy;   //PER EVITARE LA SBATTI DEL LOG IN
    private BounderyEnum nextBoundery;                                              /*TBR*/
    private String lastError;

    private SessionInfo(){}

    public static SessionInfo getSessionInfo(){
        if(sessionInfo == null){
            sessionInfo = new SessionInfo();
        }
        return sessionInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
