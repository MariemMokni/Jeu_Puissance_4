package Controller;

import java.util.List;

import Model.AfficherJoueur;
import Model.Joueur;
import Model.Partie;
import Model.DAO.PartieDAO;
import View.InterfaceListPartie;
import View.InterfaceListeJoueur;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ControlleurPartie {

	private BorderPane fenetre;
	private InterfaceListPartie interfaceListePartie;
	private TableView<Partie> tabView=new TableView<Partie>();


    public ControlleurPartie(InterfaceListPartie interfaceListePartie) {
        this.interfaceListePartie = interfaceListePartie;
        this.fenetre = new BorderPane();
        initFenetre();
        initListePartieAction();
    }
    
    private void initFenetre() {
        fenetre.setLeft(interfaceListePartie.getListPartiej1());
    }

    public void initListePartieAction() {
    	PartieDAO partieDAO = new PartieDAO();
        List<Partie> parties = partieDAO.findAll();
        TableView<Partie> tableParties = afficherListeParties(parties);
        fenetre.setCenter(tableParties);
    }
    
    public TableView<Partie> afficherListeParties(List<Partie> parties) {
        TableColumn<Partie, Integer> idPartie = new TableColumn<Partie, Integer>("id Partie");
        TableColumn<Partie, String> nomJoueur1 = new TableColumn<Partie, String>("nom Joueur 1");
        TableColumn<Partie, String> nomJoueur2 = new TableColumn<Partie, String>("nom Joueur 2");
        TableColumn<Partie, Integer> joueurScore1 = new TableColumn<Partie, Integer>("score Joueur 1");
        TableColumn<Partie, Integer> joueurScore2 = new TableColumn<Partie, Integer>("score Joueur 2");

        idPartie.setCellValueFactory(new PropertyValueFactory<>("IdP"));
        nomJoueur1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJ1().getNom()));
        nomJoueur2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJ2().getNom()));
        joueurScore1.setCellValueFactory(new PropertyValueFactory<>("ScoreJ1"));
        joueurScore2.setCellValueFactory(new PropertyValueFactory<>("ScoreJ2"));

        tabView.getColumns().addAll(idPartie, nomJoueur1, nomJoueur2, joueurScore1, joueurScore2);

        ObservableList<Partie> list1 = FXCollections.observableArrayList(parties);    
        tabView.setItems(list1);
        
        return tabView;    
    }



	public BorderPane getFenetre() {
        return fenetre;
    }
}
