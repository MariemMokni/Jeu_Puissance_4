package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Joueur;
import Model.Partie;

public class PartieDAO extends DAO<Partie> {
	private Connection connection;

	public PartieDAO() {
		try {
			connection = DAO.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Partie create(Partie p) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO partie (joueur1, joueur2, score1 , score2) VALUES (?, ?,?,?)");
			ps.setInt(1, p.getJ1().getId());
			ps.setInt(2, p.getJ2().getId());
			ps.setInt(3, p.getScoreJ1());
			ps.setInt(4, p.getScoreJ2());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;

	}

	@Override
	public List<Partie> findAll() {
		String rq = "select * from partie";
		List<Partie> l = new ArrayList<>();
		JoueurDAO daoj = new JoueurDAO();
		try {
			PreparedStatement ps = connection.prepareStatement(rq);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int idP = rs.getInt("idP");
				int idj1 = rs.getInt("joueur1");
				int idj2 = rs.getInt("joueur2");
				Joueur j1 = daoj.find(new Joueur(idj1));
				Joueur j2 = daoj.find(new Joueur(idj2));
				int Score1 = rs.getInt("score1");
				int Score2 = rs.getInt("score2");
				Partie p = new Partie(idP, j1, j2, Score1, Score2);
				l.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public Partie find(Partie x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partie update(Partie a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partie delete(Partie a) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateScores(int idPartie, int scoreJ1, int scoreJ2) {
		try {

			String query = "UPDATE partie SET score1 = ?, score2 = ? WHERE idp = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, scoreJ1); // Score du joueur 1
			statement.setInt(2, scoreJ2); // Score du joueur 2
			statement.setInt(3, idPartie); // ID de la partie

			int rowsUpdated = statement.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Les scores des joueurs ont été mis à jour avec succès.");
			} else {
				System.out.println("Aucun enregistrement mis à jour.");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
