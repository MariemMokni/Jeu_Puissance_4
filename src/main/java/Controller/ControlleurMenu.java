package Controller;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class ControlleurMenu {
	private BorderPane fenetre;
	private Controlleur jeuControlleur;
	private controlleurListJ listeJoueurControlleur;
	private controlleurListJP listeJoueurControlleurP;
	private ControlleurPartie controlleurPartie;
	private controlleurFichier controlleurFichier;

	public ControlleurMenu(BorderPane fenetre, Controlleur jeuControlleur, controlleurListJ listeJoueurControlleur,
			ControlleurPartie controlleurPartie, controlleurListJP listeJoueurControlleurP,
			controlleurFichier controlleurFichier) {
		this.fenetre = fenetre;
		this.jeuControlleur = jeuControlleur;
		this.listeJoueurControlleur = listeJoueurControlleur;
		this.controlleurPartie = controlleurPartie;
		this.listeJoueurControlleurP = listeJoueurControlleurP;
		this.controlleurFichier = controlleurFichier;
	}

	public MenuBar creerMenu() {
		MenuBar menuBar = new MenuBar();
		Menu menuListeJoueur = new Menu("Gestion Joueur");
		Menu menuAfficherListeJoueur = new Menu("Afficher Liste Joueur");
		MenuItem sousItem1 = new MenuItem("Liste Joueur selon score");
		sousItem1.setOnAction(event -> {
			fenetre.setCenter(listeJoueurControlleur.getFenetre());
		});
		MenuItem sousItem2 = new MenuItem("Liste Joueur selon nombre de partie");
		sousItem2.setOnAction(event -> {
			fenetre.setCenter(listeJoueurControlleurP.getFenetre());
		});

		menuAfficherListeJoueur.getItems().addAll(sousItem1, sousItem2);

		menuListeJoueur.getItems().add(menuAfficherListeJoueur);

		Menu menuJeuPuissance = new Menu("Jeu Puissance");
		MenuItem itemJeuPuissance = new MenuItem("Jouer Jeu Puissance 4");
		itemJeuPuissance.setOnAction(event -> {
			fenetre.setCenter(jeuControlleur.getFenetre());
			jeuControlleur.gameControlleur();
		});

		Menu menuListePartie = new Menu("Gestion Partie");
		MenuItem itemListePartie = new MenuItem("Afficher Liste Partie");
		itemListePartie.setOnAction(event -> {
			fenetre.setCenter(controlleurPartie.getFenetre());
		});

		Menu menuListeFichier = new Menu("Fichier");
		MenuItem itemListeFichier = new MenuItem("Afficher Liste Partie");
		itemListeFichier.setOnAction(event -> {
			fenetre.setCenter(controlleurFichier.getFenetre());
		});

		menuJeuPuissance.getItems().add(itemJeuPuissance);
		menuListePartie.getItems().add(itemListePartie);
		menuListeFichier.getItems().add(itemListeFichier);

		menuBar.getMenus().addAll(menuListeJoueur, menuJeuPuissance, menuListePartie, menuListeFichier);

		menuBar.setStyle("-fx-background-color: pink;");
		menuJeuPuissance.setStyle(
				"-fx-background-color: pink; -fx-font-family: 'serif'; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: black;");
		menuListeJoueur.setStyle(
				"-fx-background-color: pink; -fx-font-family: 'serif'; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: black;");
		menuListePartie.setStyle(
				"-fx-background-color: pink; -fx-font-family: 'serif'; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: black;");
		menuListeFichier.setStyle(
				"-fx-background-color: pink; -fx-font-family: 'serif'; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: black;");
		return menuBar;
	}
}
