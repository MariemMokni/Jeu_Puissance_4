package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Controller.Controlleur;

public class GestionJoueur {
	List<Joueur> list = new ArrayList<>();

	public GestionJoueur() {
		remplirList();
	}

	public void remplirList() {
		list.add(new Joueur(1, "Joueur 01", 10));
		list.add(new Joueur(2, "Joueur 02", 20));
		list.add(new Joueur(3, "Joueur 03", 30));
		list.add(new Joueur(4, "Joueur 04", 10));
		list.add(new Joueur(5, "Joueur 05", 20));
		list.add(new Joueur(6, "Joueur 06", 30));
	}

	public List<Joueur> listJoueurTrieByScore() {
		Collections.sort(list);
		return list;
	}

	public List<Joueur> listJoueurTrieByNom() {
		Collections.sort(list, new Comparator<Joueur>() {
			@Override
			public int compare(Joueur o1, Joueur o2) {
				return (o1.getNom().compareTo(o2.getNom()) * -1);
			}
		});
		return list;
	}

	public List<Joueur> listJoueurTrieById() {
		Collections.sort(list, (o1, o2) -> {
			if (o1.getId() > o2.getId())
				return 1;
			else if (o1.getId() < o2.getId())
				return -1;
			return 0;
		});
		return list;
	}

	public List<Joueur> listFiltreByNom(String nomRecherche) {
		List<Joueur> resultat = list.stream().filter(joueur -> joueur.getNom().contains(nomRecherche))
				.collect(Collectors.toList());

		return resultat;
	}

	public List<Joueur> listFiltreByScore(int scoreMin) {
		List<Joueur> resultat = list.stream().filter(joueur -> joueur.getScore() > scoreMin)
				.collect(Collectors.toList());

		return resultat;
	}

	public List<Joueur> listFiltreByNomScore(String nomRecherche, int scoreMin) {
		List<Joueur> resultat;
		resultat = Controlleur.verifierSi(this.list, j -> {
			Joueur jj = (Joueur) j;
			if (jj.getNom().contains(nomRecherche) && jj.getScore() > scoreMin)
				return true;
			return false;
		});
		return resultat;
	}

	public List<Joueur> getList() {
		return list;
	}

	public void setList(List<Joueur> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GestionJoueur [list=" + list + "]";
	}

}
