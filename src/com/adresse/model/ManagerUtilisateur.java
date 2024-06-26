package com.adresse.model;

import java.sql.*;
import java.util.ArrayList;

public class ManagerUtilisateur {
    private static final Connection connexion = Database.getConnexion();
    public static Utilisateur create(Utilisateur user) throws SQLException {
        //créer un objet Utilisateur
        Utilisateur userAdd = new Utilisateur();
        //try la requête
        try {
            //connecter à la bdd
            Statement smt = connexion.createStatement();
            //préparation de la requête
            String req = "INSERT INTO users(name,firstname,email,password) VALUE(?,?,?,?)";
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            //binder les paramètres
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            //exécuter la requête
            int row = preparedStatement.executeUpdate();
            //tester si la requête à réussi
            if(row > 0) {
                userAdd.setName(user.getName());
                userAdd.setFirstname(user.getFirstname());
                userAdd.setEmail(user.getEmail());
                userAdd.setPassword(user.getPassword());
            }
            //recupérer l'enregistrement
        }
        //lever l'erreur SQL
        catch (SQLException e){
            e.printStackTrace();
        }
        //retourne un objet utilisateur complet
        return userAdd;
    }


    public static Utilisateur update(Utilisateur user) throws SQLException {
        //créer un objet Utilisateur
        Utilisateur userUpdate = new Utilisateur();
        //try la requête
        try {
            //connecter à la bdd
            Statement smt = connexion.createStatement();
            //préparation de la requête
            String req = "UPDATE users SET name = ?, firstname= ?  WHERE email = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            //binder les paramètres
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getEmail());


            //exécuter la requête
            int row = preparedStatement.executeUpdate();
            //tester si la requête à réussi
            if(row > 0) {
                userUpdate.setName(user.getName());
                userUpdate.setFirstname(user.getFirstname());

            }
            //recupérer l'enregistrement
        }
        //lever l'erreur SQL
        catch (SQLException e){
            e.printStackTrace();
        }
        //retourne un objet utilisateur complet
        return userUpdate;
    }



    public static Utilisateur delete(Utilisateur user) throws SQLException {
        //créer un objet Utilisateur
        Utilisateur userDelete = new Utilisateur();
        //try la requête
        try {
            //connecter à la bdd
            Statement smt = connexion.createStatement();
            //préparation de la requête
            String req = "DELETE from users  WHERE email = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            //binder les paramètres

            preparedStatement.setString(1, user.getEmail());


            //exécuter la requête
            int row = preparedStatement.executeUpdate();
            //tester si la requête à réussi
            if(row > 0) {
                userDelete.setName(user.getName());
                userDelete.setFirstname(user.getFirstname());

            }
            //recupérer l'enregistrement
        }
        //lever l'erreur SQL
        catch (SQLException e){
            e.printStackTrace();
        }
        //retourne un objet utilisateur complet
        return userDelete;
    }



    public static ArrayList<Utilisateur> selectAll() throws SQLException{
        ArrayList<Utilisateur> users = new ArrayList<>();
        try {
            //connexion à la bdd
            Statement smt = connexion.createStatement();
            //requête
            String req = "SELECT id, name, firstname, email, password FROM users";
            //préparer la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

            //exécuter la requête
            ResultSet reponse = preparedStatement.executeQuery();
            //boucle pour parcourir le résultat
            while (reponse.next()) {
                if(reponse.getString(1) !=null){
                    //setter les nouvelles valeurs
                    Utilisateur userRecup = new Utilisateur();
                    userRecup.setId(reponse.getInt(1));
                    userRecup.setName(reponse.getString("name"));
                    userRecup.setFirstname(reponse.getString("firstname"));
                    userRecup.setEmail(reponse.getString("email"));
                    userRecup.setPassword(reponse.getString("password"));
                    users.add(userRecup);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //retourne l'array Utilisateur
        return users;
    }




    public static Utilisateur findByMail(Utilisateur user) throws SQLException{
        Utilisateur userRecup = new Utilisateur();
        try {
            //connexion à la bdd
            Statement smt = connexion.createStatement();
            //requête
            String req = "SELECT id, name, firstname, email, password FROM users WHERE email = ?";
            //préparer la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            //binder le paramètre
            preparedStatement.setString(1, user.getEmail());
            //exécuter la requête
            ResultSet reponse = preparedStatement.executeQuery();
            //boucle pour parcourir le résultat
            while (reponse.next()) {
                if(reponse.getString(1) !=null){
                    //setter les nouvelles valeurs
                    userRecup.setId(reponse.getInt(1));
                    userRecup.setName(reponse.getString("name"));
                    userRecup.setFirstname(reponse.getString("firstname"));
                    userRecup.setEmail(reponse.getString("email"));
                    userRecup.setPassword(reponse.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //retourne l'objet Utilisateur
        return userRecup;
    }
}
