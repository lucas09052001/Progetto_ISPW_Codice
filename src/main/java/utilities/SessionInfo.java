package utilities;

import boundery.Boundaries;

public class SessionInfo {
    private static SessionInfo sessionInfo;

    private String username;
    private PersistencyPolicy persistencyPolicy;   //PER EVITARE LA SBATTI DEL LOG IN
    private Boundaries nextBoundery;                                              /*TBR*/
    private String lastError;

    private Version version = Version.V1;

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

    public Boundaries getNextBoundery() {
        return nextBoundery;
    }
    public void setNextBoundery(Boundaries boundaries) {
        this.nextBoundery = boundaries;
    }

    public PersistencyPolicy getPersistencyPolicy() {return persistencyPolicy;}
    public void setPersistencyPolicy(PersistencyPolicy persistencyPolicy) {this.persistencyPolicy = persistencyPolicy;}

    public String getLastError() {return lastError;}
    public void setLastError(String lastError) {this.lastError = lastError;}

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }
}
