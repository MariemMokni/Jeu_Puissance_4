package Controller;

import Model.AfficherJoueur;
import Model.Joueur;
import View.InterfaceListeJoueur;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.collections.ObservableList;

public class controlleurListJ {

	private InterfaceListeJoueur interfaceListeJoueur;
	private BorderPane fenetre;

	public controlleurListJ(InterfaceListeJoueur interfaceListeJoueur) {
		this.interfaceListeJoueur = interfaceListeJoueur;
		this.fenetre = new BorderPane();
		initFenetre();
		initListeJoueurAction();
	}

	private void initFenetre() {
		fenetre.setLeft(interfaceListeJoueur.getListJoueur());
		fenetre.setRight(interfaceListeJoueur.getCoordonnerJoueur());
	}

	public void afficherListeJoueursSelonScore(ObservableList<Joueur> joueurs) {

		TableView<Joueur> tableView = new TableView<>();

		TableColumn<Joueur, String> nomCol = new TableColumn<>("Nom");
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));

		TableColumn<Joueur, Integer> scoreCol = new TableColumn<>("Score");
		scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

		tableView.getColumns().add(nomCol);
		tableView.getColumns().add(scoreCol);

		tableView.setItems(joueurs);

		scoreCol.setSortType(TableColumn.SortType.DESCENDING);
		tableView.getSortOrder().add(scoreCol);
		tableView.sort();

		fenetre.setCenter(tableView);
	}

	private void initListeJoueurAction() {
		ObservableList<Joueur> joueurs = AfficherJoueur.getJoueurs();
		afficherListeJoueursSelonScore(joueurs);
	}

	public BorderPane getFenetre() {
		return fenetre;
	}
}
