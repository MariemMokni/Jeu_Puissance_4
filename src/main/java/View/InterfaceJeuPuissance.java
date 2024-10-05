package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Observable;

import Model.Joueur;

public class InterfaceJeuPuissance extends Observable {
	private GridPane grilleJeu = new GridPane();
	private int nbLigne, nbColonne;
	private Button[][] tabButton;
	private StringProperty coupsJoueur1 = new SimpleStringProperty("");
	private StringProperty coupsJoueur2 = new SimpleStringProperty("");
	private Label champTexte = new Label();
	private Joueur joueur1;

	public InterfaceJeuPuissance(int nbLigne, int nbColonne) {
		this.nbLigne = nbLigne;
		this.nbColonne = nbColonne;
		tabButton = new Button[nbLigne][nbColonne];
	}

	public void dessiner() {
		for (int i = 0; i < nbLigne; i++) {
			for (int j = 0; j < nbColonne; j++) {
				tabButton[i][j] = new Button("    ");
				grilleJeu.add(tabButton[i][j], j, i);
				setCouleurButton(i, j, "#aaaaaa");
				int ligne = i;
				int colonne = j;
				tabButton[i][j].setOnAction(event -> {
					notifyObservers("Coup joué en Ligne:" + (ligne + 1) + ",Colonne:" + (colonne + 1));
				});
			}
		}
	}

	public Node getJoueur(Joueur j) {
		VBox vbox = new VBox();
		HBox hBoxNom = new HBox();
		HBox hBoxScore = new HBox();
		Label ltextNom = new Label("Nom : ");
		Label ltextScore = new Label("Score : ");
		Label ljnom = new Label(j.getNom());
		Label lscore = new Label("" + j.getScore());
		hBoxNom.getChildren().addAll(ltextNom, ljnom);
		hBoxScore.getChildren().addAll(ltextScore, lscore);
		vbox.getChildren().addAll(hBoxNom, hBoxScore);
		return vbox;
	}

	public void setCouleurButton(int numLigne, int numColonne, String couleur) {
		tabButton[numLigne][numColonne]
				.setStyle("-fx-background-radius: 150em; " + "-fx-min-width: 50px;" + "-fx-min-height: 50px; "
						+ "-fx-max-width: 50px; " + "-fx-max-height: 50px;" + "-fx-background-color:" + couleur + ";");
	}

	public GridPane getGrilleJeu() {
		return grilleJeu;
	}

	public Button[][] getTabButton() {
		return this.tabButton;
	}

	public void updateCoupsAffichage(String coupsJ1, String coupsJ2) {
		coupsJoueur1.set(coupsJ1);
		coupsJoueur2.set(coupsJ2);
		champTexte.setText("Coups Joueur 1: " + coupsJ1 + "\nCoups Joueur 2: " + coupsJ2);
	}

	public Labeled getChampTexte() {
		return champTexte;
	}

	public void updateTextField(Joueur joueur, String message) {
		if (joueur.equals(joueur1)) {
			coupsJoueur1.set(message);
		} else {
			coupsJoueur2.set(message);
		}
		champTexte.setText("Coups Joueur 1: " + coupsJoueur1.get() + "\nCoups Joueur 2: " + coupsJoueur2.get());
	}

	public void setJoueur1(Joueur joueur) {
		this.joueur1 = joueur;
	}
}
