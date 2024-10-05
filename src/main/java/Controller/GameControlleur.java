package Controller;

import java.util.Collections;
import java.util.List;

import Model.Joueur;
import Model.DAO.JoueurDAO;

public class GameControlleur {
	private final List<Joueur> listeJoueurs;
	private final Joueur joueur1;
	private final Joueur joueur2;

	public GameControlleur() {
		this.listeJoueurs = new JoueurDAO().findAll();
		Collections.shuffle(this.listeJoueurs);
		this.joueur1 = this.listeJoueurs.get(0);
		this.joueur2 = this.listeJoueurs.get(1);
	}

	public Joueur getJoueur1() {
		return this.joueur1;
	}

	public Joueur getJoueur2() {
		return this.joueur2;
	}

	public void startGame() {

	}
}
