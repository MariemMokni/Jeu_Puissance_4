package Model;

import java.util.Scanner;

import javafx.scene.control.Labeled;

public class Joueur implements Comparable<Joueur> {
	private int id;
	private String nom;
	private int score;
	private String prenom;
	private int nombreDeParties;

	public Joueur() {

	}

	public Joueur(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void incrementerScore() {
		this.score++;
	}

	public void decrementerScore() {
		this.score--;
	}

	public Joueur(int id, String nom, int score) {
		this.nom = nom;
		this.id = id;
		this.score = score;
	}

	public Joueur(int id, String nom, String prenom, int score) {
		this.nom = nom;
		this.id = id;
		this.score = score;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Nom: " + nom + "\n    identifiant: " + id + "\n    Score: " + score + ")\n";
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getNombreDeParties() {
		return nombreDeParties;
	}

	public void setNombreDeParties(int nombreDeParties) {
		this.nombreDeParties = nombreDeParties;
	}

	public int ChoisierCoup() {
		System.out.print("Choisir une colonne: ");
		Scanner clavier = new Scanner(System.in);
		int numColonne = clavier.nextInt();
		return numColonne - 1;
		/*
		 * int numColonne=(int)(Math.random()*7); return numColonne;
		 */
	}

	@Override
	public int compareTo(Joueur o) {
		if (this.score > o.score)
			return 1;
		else if (this.score < o.score)
			return -1;
		return 0;
	}

}
