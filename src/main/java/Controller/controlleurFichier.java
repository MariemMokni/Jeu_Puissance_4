package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Model.Partie;
import Model.DAO.PartieDAO;
import View.InterfaceListPartie;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class controlleurFichier {
	private BorderPane fenetre;
	private InterfaceListPartie interfaceListePartie;
	private TableView<Partie> tabView = new TableView<>();
	private Label selectedPartieLabel = new Label();
	

	public controlleurFichier(InterfaceListPartie interfaceListePartie) {
		this.interfaceListePartie = interfaceListePartie;
		this.fenetre = new BorderPane();
		initFenetre();
		initListePartieAction();
	}

	private void initFenetre() {
		fenetre.setLeft(interfaceListePartie.getListPartiej1());
		fenetre.setBottom(selectedPartieLabel);
	}

	public void initListePartieAction() {
		PartieDAO partieDAO = new PartieDAO();
		List<Partie> parties = partieDAO.findAll();
		TableView<Partie> tableParties = afficherListeParties(parties);
		fenetre.setCenter(tableParties);
	}

	public TableView<Partie> afficherListeParties(List<Partie> parties) {
		TableColumn<Partie, Integer> idPartie = new TableColumn<>("id Partie");
		TableColumn<Partie, String> nomJoueur1 = new TableColumn<>("nom Joueur 1");
		TableColumn<Partie, String> nomJoueur2 = new TableColumn<>("nom Joueur 2");
		TableColumn<Partie, Integer> joueurScore1 = new TableColumn<>("score Joueur 1");
		TableColumn<Partie, Integer> joueurScore2 = new TableColumn<>("score Joueur 2");
		TableColumn<Partie, Void> actionsColumn = new TableColumn<>("Actions");

		idPartie.setCellValueFactory(new PropertyValueFactory<>("IdP"));
		nomJoueur1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJ1().getNom()));
		nomJoueur2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJ2().getNom()));
		joueurScore1.setCellValueFactory(new PropertyValueFactory<>("ScoreJ1"));
		joueurScore2.setCellValueFactory(new PropertyValueFactory<>("ScoreJ2"));

		actionsColumn.setCellFactory(param -> new ButtonCell());

		tabView.getColumns().addAll(idPartie, nomJoueur1, nomJoueur2, joueurScore1, joueurScore2, actionsColumn);

		ObservableList<Partie> list1 = FXCollections.observableArrayList(parties);
		tabView.setItems(list1);

		return tabView;
	}

	public class ButtonCell extends TableCell<Partie, Void> {
		private final HBox buttonsBox = new HBox();
		private final Button exportButton = new Button("Export");
		private final Button importButton = new Button("Import");

		ButtonCell() {
			exportButton.setOnAction(event -> {
				Partie partie = getTableView().getItems().get(getIndex());
				ExportPartie(partie);
			});

			importButton.setOnAction(event -> {
				Partie partie = getTableView().getItems().get(getIndex());
				ImportPartie(partie);
			});

			buttonsBox.getChildren().addAll(exportButton, importButton);
			buttonsBox.setSpacing(5);
		}

		@Override
		protected void updateItem(Void item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				setGraphic(buttonsBox);
			} else {
				setGraphic(null);
			}
		}
	}

	private void ExportPartie(Partie partie) {
		String fileName = constructFileName(partie);
		String fileContent = constructFileContent(partie);
		writeFile(fileName, fileContent);
	}

	private void ImportPartie(Partie partie) {
        String fileName = constructFileName(partie);
        if (!new File(fileName).exists()) {
            selectedPartieLabel.setText("Exportez d'abord la partie avant de l'importer.");
            return;
        }
        String fileContent = readFile(fileName);
        selectedPartieLabel.setText(fileContent);
    }

    private String constructFileName(Partie partie) {
        return "parties/" + partie.getIdP() + "_" + partie.getJ1().getNom() + "_" + partie.getJ2().getNom() + ".txt";
    }

    private String constructFileContent(Partie partie) {
        return "ID Partie: " + partie.getIdP() + "\n" +
               "Joueur 1: " + partie.getJ1().getNom() + "\n" +
               "Joueur 2: " + partie.getJ2().getNom() + "\n" +
               "Score Joueur 1: " + partie.getScoreJ1() + "\n" +
               "Score Joueur 2: " + partie.getScoreJ2();
    }

    private void ensureDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private void writeFile(String fileName, String content) {
        ensureDirectoryExists("parties");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(String fileName) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }
    

	public BorderPane getFenetre() {
		return fenetre;
	}
}
