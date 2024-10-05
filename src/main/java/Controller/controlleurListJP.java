package Controller;

import Model.AfficherJoueur;
import Model.Joueur;
import Model.DAO.JoueurDAO;
import View.InterfaceListeJoueur;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public class controlleurListJP {

	private InterfaceListeJoueur interfaceListeJoueur;
	private BorderPane fenetre;

	public controlleurListJP(InterfaceListeJoueur interfaceListeJoueur) {
		this.interfaceListeJoueur = interfaceListeJoueur;
		this.fenetre = new BorderPane();
		initFenetre();
		initListeJoueurAction();
	}

	private void initFenetre() {
		fenetre.setLeft(interfaceListeJoueur.getListJoueur());
		fenetre.setRight(interfaceListeJoueur.getCoordonnerJoueur());
	}

	public void afficherListeJoueursSelonNombreDeParties(ObservableList<Joueur> joueurs) {

		TableView<Joueur> tableView = new TableView<>();

		TableColumn<Joueur, String> nomCol = new TableColumn<>("Nom");
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));

		TableColumn<Joueur, Integer> nombreDePartiesCol = new TableColumn<>("Nombre de Parties");

		nombreDePartiesCol.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(JoueurDAO.getNombreDePartiesJouees(cellData.getValue()))
						.asObject());

		tableView.getColumns().add(nomCol);
		tableView.getColumns().add(nombreDePartiesCol);

		tableView.setItems(joueurs);

		nombreDePartiesCol.setSortType(TableColumn.SortType.DESCENDING);
		tableView.getSortOrder().add(nombreDePartiesCol);
		tableView.sort();

		fenetre.setCenter(tableView);
	}

	private void initListeJoueurAction() {
		ObservableList<Joueur> joueurs = AfficherJoueur.getJoueurs();
		afficherListeJoueursSelonNombreDeParties(joueurs);
	}

	public BorderPane getFenetre() {
		return fenetre;
	}
}
