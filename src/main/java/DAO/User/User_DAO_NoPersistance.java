package DAO.User;

import Entity.User;

public class User_DAO_NoPersistance implements User_DAO{

    public User_DAO_NoPersistance(){

    }

    @Override
    public User authenticate(String username, String password) {
        //Use User_Repository
    }
}
