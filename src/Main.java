import com.adresse.model.ManagerUtilisateur;
import com.adresse.model.Utilisateur;

import java.sql.SQLException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws SQLException {
        Utilisateur user = new Utilisateur("test", "test", "test@test.com", "1234");
        Utilisateur user2 = new Utilisateur("test", "test", "test@test.com", "1234");
        //test si le compte exist
        if(Objects.equals(user.getEmail(), ManagerUtilisateur.findByMail(user).getEmail())){
            System.out.println("Le compte existe déja");
        }
        else{
            //ajouter le compte en BDD
            System.out.println(ManagerUtilisateur.create(user));
        }



        //UPDTAE USERS
        user.setName("coucou");
        user.setFirstname("coucou");
        ManagerUtilisateur.update(user);

        //SELECT ALL
        ManagerUtilisateur.create(user2);
        System.out.println(ManagerUtilisateur.selectAll());


        //SUPPRIME un utilisateur par l'email
        System.out.println(ManagerUtilisateur.delete(user));


    }
}
