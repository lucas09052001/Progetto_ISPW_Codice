/*

Le classi repository funzionano da livello dati non persistente. La loro dinamica si articola in:
- Sono classi che usano il pattern singleton.
- Alla loro istanziazione popolano delle entità esempio
- Tengono memoria delle entità esempio e delle modifiche che ad esse vengono fatte via DAO_NoPersistance
  nell'arco di una run del programma
- Quando un DAO_NoPersistance deve lavorare con un repository, ottiene l'istanza e si fa dare la lista delle entità
  fatti i suoi cambiamenti DAO_NoPersistance chiama il metodo set per aggiornare il repository.
*/

package repository;

import entity.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static UserRepository instance;
    private List<User> listaUtenti;

    private UserRepository(){

        listaUtenti = new ArrayList<>();

        listaUtenti.add(new User("alice", "alice", 3, 50000));
        listaUtenti.add(new User("bob", "bob", 2.4f, 2000));
        listaUtenti.add(new User("carlos", "carlos", 1.1f, 100));

    }

    public static UserRepository getUserRepository(){
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    public List<User> getListaUtenti() {
        return listaUtenti;
    }

    public void setListaUtenti(List<User> listaUtenti) {
        this.listaUtenti = listaUtenti;
    }
}

