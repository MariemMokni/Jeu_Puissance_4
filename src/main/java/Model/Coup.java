package Model;

public class Coup {
    private int numColonneJ1;
    private int numColonneJ2;
    private int ligne;
    private Joueur joueur;
    private Partie partie;
    private int idP;
    private int idC;

    public Coup(int idc, int numColonneJ1, int numColonneJ2) {
        this.idC = idc;
        this.numColonneJ1 = numColonneJ1;
        this.numColonneJ2 = numColonneJ2;
    }

    public Coup(int numColonneJ1, int numColonneJ2, Joueur joueur, Partie partie) {
        this.numColonneJ1 = numColonneJ1;
        this.numColonneJ2 = numColonneJ2;
        this.joueur = joueur;
        this.partie = partie;
        this.idP = partie.getIdP();
    }

    public Coup(int numColonneJ1, int numColonneJ2, Partie partie, int idP, int idC) {
        this.numColonneJ1 = numColonneJ1;
        this.numColonneJ2 = numColonneJ2;
        this.partie = partie;
        this.idP = idP;
        this.idC = idC;
    }

    public int getCoupJ1() {
        return numColonneJ1;
    }

    public void setCoupJ1(int numColonneJ1) {
        this.numColonneJ1 = numColonneJ1;
    }

    public int getCoupJ2() {
        return numColonneJ2;
    }

    public void setCoupJ2(int numColonneJ2) {
        this.numColonneJ2 = numColonneJ2;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    @Override
    public String toString() {
        return "Coup [numColonneJ1=" + numColonneJ1 + ", numColonneJ2=" + numColonneJ2 + ", ligne=" + ligne + ", joueur=" + joueur + ", partie=" + partie + ", idP=" + idP + ", idC=" + idC + "]";
    }
}
