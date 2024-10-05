package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Model.Coup;
import Model.MyConnection;

public class CoupDAO extends DAO<Coup>{
	private Connection connection;

    public CoupDAO() {
    	try {
			connection = DAO.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public Coup create(Coup c) {
		 try {
	            PreparedStatement ps = connection.prepareStatement("INSERT INTO coup (numColJ1,numColJ2 ,idP) VALUES (?, ?,?)");
	            ps.setInt(1, c.getCoupJ1());
	            ps.setInt(1, c.getCoupJ2());
	            ps.setInt(2, c.getPartie().getIdP());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return c;
		 
	}


	@Override
	public Coup update(Coup a) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Coup delete(Coup a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coup> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coup find(Coup x) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
