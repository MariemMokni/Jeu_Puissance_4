package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AfficherJoueur {
    public static ObservableList<Joueur> getJoueurs() {
        ObservableList<Joueur> joueurs = FXCollections.observableArrayList();
        Connection conn = null;
        Statement ps = null;
        ResultSet rs = null;

        try {
            conn = MyConnection.getConnection();
            String requete = "SELECT * FROM joueur";
            ps = conn.createStatement();
            rs = ps.executeQuery(requete);

            while (rs.next()) {
                int id = rs.getInt("Id");
                String nom = rs.getString("Nom");
                int score = rs.getInt("Score");
                Joueur joueur = new Joueur(id, nom, score);
                joueurs.add(joueur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception lors de la recuperation des donnees de la base de donnees");
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return joueurs;
    }
}
