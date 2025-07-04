package dao.user_dao;

import entity.SessionInfo;
import exceptions.NoMatchException;

public class UserDAOFactory {
    SessionInfo sessionInfo;

    public UserDAOFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public UserDAO generate() throws NoMatchException{

        UserDAO returnee;

        System.out.println("        [DAO-FACTORY] Instantiating dao");
        switch (sessionInfo.getPersistencyPolicy()){
            case DB -> { returnee = new UserDAODB();
            }
            case FILE -> { returnee = new UserDAOFile();
            }
            case NO_PERSISTANCE -> { returnee = new UserDAONoPersistance();
            }
            case null, default -> {  throw new NoMatchException("In UserDaoFactory reached unreachable code");
            }
        }

        System.out.println("        [DAO-FACTORY] Returning");
        return returnee;
    }

}
