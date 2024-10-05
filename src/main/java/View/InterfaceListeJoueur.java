package View;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class InterfaceListeJoueur {

	private VBox listJoueur;
	private Label coordonnerJoueur;

	public InterfaceListeJoueur() {
		listJoueur = new VBox();
		coordonnerJoueur = new Label("");
	}

	public VBox getListJoueur() {
		return listJoueur;
	}

	public void setListJoueur(VBox listJoueur) {
		this.listJoueur = listJoueur;
	}

	public Label getCoordonnerJoueur() {
		return coordonnerJoueur;
	}

	public void setCoordonnerJoueur(Label coordonnerJoueur) {
		this.coordonnerJoueur = coordonnerJoueur;
	}

}
