/*

Le classi repository funzionano da livello dati non persistente. La loro dinamica si articola in:
- Sono classi che usano il pattern singleton.
- Alla loro istanziazione popolano delle entità esempio
- Tengono memoria delle entità esempio e delle modifiche che ad esse vengono fatte via DAO_NoPersistance
  nell'arco di una run del programma
- Quando un DAO_NoPersistance deve lavorare con un repository, ottiene l'istanza e si fa dare la lista delle entità
  fatti i suoi cambiamenti DAO_NoPersistance chiama il metodo set per aggiornare il repository.
*/

package Repository;

import Entity.User;

import java.util.ArrayList;
import java.util.List;

public class User_Repository {

    private static User_Repository instance;
    private List<User> lista_utenti;

    private User_Repository(){

        lista_utenti = new ArrayList<>();

        lista_utenti.add(new User("alice", "alice", 4, 50000));
        lista_utenti.add(new User("bob", "bob", 5, 1200));

    }

    public static User_Repository get_user_repository(){
        if(instance == null){
            instance = new User_Repository();
        }
        return instance;
    }

    public List<User> getLista_utenti() {
        return lista_utenti;
    }

    public void setLista_utenti(List<User> lista_utenti) {
        this.lista_utenti = lista_utenti;
    }
}

