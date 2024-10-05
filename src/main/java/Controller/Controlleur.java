package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import Model.Coup;
import Model.CoupException;
import Model.CritereSelection;
import Model.Game;
import Model.Joueur;
import Model.Partie;
import Model.Position;
import Model.DAO.CoupDAO;
import Model.DAO.JoueurDAO;
import Model.DAO.PartieDAO;
import View.InterfaceJeuPuissance;


public class Controlleur {
    private BorderPane fenetre = new BorderPane();
    private Partie partieJeu;
    private InterfaceJeuPuissance interfaceJeuPuissance;
    private int tourDeJeu = 0;
    private boolean partieTerminee = false;
    private List<Coup> coupsEnAttente = new ArrayList<>();
    private List<Joueur> listeJoueurs;
    private Joueur joueur1;
    private Joueur joueur2;

    private StringProperty coupsJoueur1 = new SimpleStringProperty("");
    private StringProperty coupsJoueur2 = new SimpleStringProperty("");

    public Controlleur() {
        
    }

    public void gameControlleur() {
        int nbLigne = 6, nbColonne = 7;
        this.listeJoueurs = new JoueurDAO().findAll();

        if (this.listeJoueurs == null || this.listeJoueurs.isEmpty()) {
            System.err.println("Aucun joueur trouve dans la base de donnees.");
            return;
        }

        Collections.shuffle(this.listeJoueurs);

        if (this.listeJoueurs.size() < 2) {
            System.err.println("Il n'y a pas assez de joueurs pour démarrer une partie.");
            return;
        }

        this.joueur1 = this.listeJoueurs.get(0);
        this.joueur2 = this.listeJoueurs.get(1);

        partieJeu = new Partie(joueur1, joueur2);

        interfaceJeuPuissance = new InterfaceJeuPuissance(nbLigne, nbColonne);
        interfaceJeuPuissance.dessiner();
        fenetre.setLeft(interfaceJeuPuissance.getJoueur(joueur1));
        fenetre.setRight(interfaceJeuPuissance.getJoueur(joueur2));
        fenetre.setCenter(interfaceJeuPuissance.getGrilleJeu());
        fenetre.setBottom(interfaceJeuPuissance.getChampTexte());


        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColonne; j++) {
                final int numCol = j;
                interfaceJeuPuissance.getTabButton()[i][j].setOnAction(event -> gestionAction(numCol));
            }
        }
    }

    private void gestionAction(int numCol) {
        Game puissance = this.partieJeu.getPuissance();
        int numLigne;
        try {
            numLigne = puissance.getLigneVideByColonne(numCol);
            puissance.setCoup(numLigne, numCol, this.partieJeu.getJoueurCourant().getId());

            if (this.partieJeu.getJoueurCourant().getId() == this.partieJeu.getJ1().getId()) {
                interfaceJeuPuissance.setCouleurButton(5 - numLigne, numCol, "#FF0000");
            } else {
                interfaceJeuPuissance.setCouleurButton(5 - numLigne, numCol, "#00FF00");
            }

            tourDeJeu++;
            Coup coup = new Coup(numLigne, numCol, this.partieJeu.getJoueurCourant(), this.partieJeu);
            coupsEnAttente.add(coup);
            afficherCoups();
            interfaceJeuPuissance.updateTextField(this.partieJeu.getJoueurCourant(), "Coup joué en Ligne: " + (numLigne + 1) + ", Colonne: " + (numCol + 1));

            if (this.partieJeu.estGagnant(new Position(numLigne, numCol))) {
                afficherMessageFinPartie("PARTIE FINIE", "Le joueur " + this.partieJeu.getJoueurCourant().getNom() + " est le gagnant !");
                updateScoresAfterWin();
                this.partieJeu.initialiseGrille();
                gameControlleur();
            } else {
                if (this.partieJeu.estRemplie()) {
                
                    afficherMessageFinPartie("PARTIE NULLE", "La grille est remplie. La partie est déclarée nulle !");
                } else {
                    this.partieJeu.modifieRole();
                }
            }
        } catch (CoupException e) {
            afficherErreur("Erreur", "La colonne " + numCol + " est remplie !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateScoresAfterWin() throws SQLException {
        if (this.partieJeu.getRolejoueur() == this.partieJeu.getJ1().getId()) {
            this.partieJeu.getJ1().incrementerScore();
            updateScore(this.partieJeu.getJ1().getId(), this.partieJeu.getJ1().getScore());
            this.partieJeu.getJ2().decrementerScore();
            updateScore(this.partieJeu.getJ2().getId(), this.partieJeu.getJ2().getScore());
            this.partieJeu.setScoreJ1(10);
            this.partieJeu.setScoreJ2(-10);
          
        } else {
            this.partieJeu.getJ2().incrementerScore();
            this.partieJeu.getJ1().decrementerScore();
            updateScore(this.partieJeu.getJ1().getId(), this.partieJeu.getJ1().getScore());
            updateScore(this.partieJeu.getJ2().getId(), this.partieJeu.getJ2().getScore());
            this.partieJeu.setScoreJ1(-10);
            this.partieJeu.setScoreJ2(10);
            
        }
    }



    private void afficherMessageFinPartie(String titre, String contenu) {
        Alert iBox = new Alert(AlertType.INFORMATION);
        iBox.setHeaderText(titre);
        iBox.setContentText(contenu);
        iBox.showAndWait();
    }

    private void afficherErreur(String titre, String contenu) {
        Alert xBox = new Alert(AlertType.ERROR);
        xBox.setHeaderText(titre);
        xBox.setContentText(contenu);
        xBox.showAndWait();
    }

    public BorderPane getFenetre() {
        return this.fenetre;
    }

    public MenuBar getMenu() {
        MenuBar mBar = new MenuBar();
        Menu mnuGestionJeu = new Menu("Gestion Jeu");
        Menu mnuGestionJoueur = new Menu("Gestion Joueur");
        Menu mnuSimulation = new Menu("Simulation");
        Menu mnuHelp = new Menu("Help");
        mBar.getMenus().addAll(mnuGestionJeu, mnuGestionJoueur, mnuSimulation, mnuHelp);

        MenuItem mniLancerJeu = new MenuItem("Lancer Jeu");
        MenuItem mniQuitter = new MenuItem("Quitter");
        mnuGestionJeu.getItems().addAll(mniLancerJeu, mniQuitter);

        mniQuitter.setOnAction(event -> Platform.exit());
        mniLancerJeu.setOnAction(event -> gameControlleur());

        MenuItem mniListeJoueur = new MenuItem("Liste Joueur");
        MenuItem mniProfilJoueur = new MenuItem("Profil Joueur");
        mnuGestionJoueur.getItems().addAll(mniListeJoueur, mniProfilJoueur);

        mniListeJoueur.setOnAction(event -> Platform.exit());
        mniProfilJoueur.setOnAction(event -> gameControlleur());

        return mBar;
    }

    public static <T> List<T> verifierSi(List<T> source, CritereSelection<T> critere) {
		List<T> l = new ArrayList<>();
		for (int i = 0; i < source.size(); i++) {
			if (critere.verifier(source.get(i))) {
				l.add(source.get(i));
			}
		}
		return l;
	}
    
    private void afficherCoups() {
        StringBuilder coupsJoueur1 = new StringBuilder("Joueur 1 - " + joueur1.getNom() + " (Score: " + joueur1.getScore() + "): \n");
        StringBuilder coupsJoueur2 = new StringBuilder("Joueur 2 - " + joueur2.getNom() + " (Score: " + joueur2.getScore() + "): \n");

        for (Coup coup : coupsEnAttente) {
            if (coup.getJoueur().getId() == joueur1.getId()) {
                coupsJoueur1.append("Ligne: ").append(coup.getLigne() + 1).append(" Colonne: ").append(coup.getCoupJ1() + 1).append("\n");
            } else {
                coupsJoueur2.append("Ligne: ").append(coup.getLigne() + 1).append(" Colonne: ").append(coup.getCoupJ2() + 1).append("\n");
            }
        }

        interfaceJeuPuissance.updateCoupsAffichage(coupsJoueur1.toString(), coupsJoueur2.toString());
    }

    private void updateScore(int idJoueur, int nouveauScore) {
        JoueurDAO joueurDAO = new JoueurDAO();
        Joueur joueur = joueurDAO.find(new Joueur(idJoueur));
        joueur.setScore(nouveauScore);
        joueurDAO.update(joueur);
    }
}