package Mvc;

import Controller.controlleurListJ;
import Controller.controlleurListJP;
import Controller.Controlleur;
import Controller.ControlleurMenu;
import Controller.ControlleurPartie;
import Controller.controlleurFichier;
import View.InterfaceListPartie;
import View.InterfaceListeJoueur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppPui extends Application {

	@Override
	public void start(Stage stage) {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 800, 600);
		InterfaceListeJoueur interfaceListeJoueur = new InterfaceListeJoueur();
		InterfaceListPartie interfaceListPartie = new InterfaceListPartie();
		Controlleur jeuPuissance4 = new Controlleur();
		controlleurListJ listeJoueur = new controlleurListJ(interfaceListeJoueur);
		ControlleurPartie listePartie = new ControlleurPartie(interfaceListPartie);
		controlleurListJP listeP = new controlleurListJP(interfaceListeJoueur);
		controlleurFichier controlleurFichier = new controlleurFichier(interfaceListPartie);
		ControlleurMenu menuControlleur = new ControlleurMenu(root, jeuPuissance4, listeJoueur,listePartie,listeP,controlleurFichier);
		root.setTop(menuControlleur.creerMenu());
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}