package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Joueur;
import Model.Partie;

public class JoueurDAO extends DAO<Joueur>{
	private static Connection connection;
	
	 public  JoueurDAO() {
		 try {
				connection = DAO.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	@Override
	public List<Joueur> findAll() {
		String rq = "select * from joueur";
	    List<Joueur> l = new ArrayList<>();
	    try {
	        PreparedStatement ps = connection.prepareStatement(rq);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	        	Joueur j=new Joueur(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("score"));
				l.add(j);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return l;
	}

	@Override
	public Joueur find(Joueur joueur) {
		Joueur j = new Joueur();
	    String rq = "SELECT * FROM joueur WHERE id = ?";
	    try {
	        PreparedStatement ps = connection.prepareStatement(rq);
	        ps.setInt(1, joueur.getId());
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {	
	            j.setId(rs.getInt("id"));
	            j.setNom(rs.getString("nom"));
	            j.setScore(rs.getInt("score"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return j;
	}


	@Override
	public Joueur create(Joueur a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Joueur update(Joueur a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Joueur delete(Joueur a) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	 public static int getNombreDePartiesJouees(Joueur joueur) {
	        int nombreDeParties = 0;
	        try {
	            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(idP) AS nombreDeParties FROM partie WHERE joueur1 = ? OR joueur2 = ?");
	            ps.setInt(1, joueur.getId());
	            ps.setInt(2, joueur.getId());
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                nombreDeParties = rs.getInt("nombreDeParties");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return nombreDeParties;
	    }
}
