package Entity;

import Boundery.Generic_Boundery;

public class Session_Info {
    private static Session_Info session_info;

    //Attributi per funzionamento (User, NextBoundery)
    private User user;
    private Generic_Boundery next_boundery;
    private Persistency_Policy persistency_policy;

    private Session_Info(){
        //Niente perché Singleton
    }

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

    public Generic_Boundery getNext_boundery() {
        return next_boundery;
    }

    public void setNext_boundery(Generic_Boundery next_boundery) {
        this.next_boundery = next_boundery;
    }

    public Persistency_Policy getPersistency_policy() {
        return persistency_policy;
    }

    public void setPersistency_policy(Persistency_Policy persistency_policy) {
        this.persistency_policy = persistency_policy;
    }
}
