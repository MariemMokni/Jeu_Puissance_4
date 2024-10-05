package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import Model.MyConnection;

public abstract class DAO<T> {
	private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        String serveurBD = "jdbc:mysql://127.0.0.1:3306/puissance4";
        String login = "root";
        String motPasse = "";

        if (conn == null || conn.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(serveurBD, login, motPasse);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return conn;
    }

	public static Connection getConn() {
		return conn;
	}
	
	
	public abstract List<T> findAll();

	public abstract T find(T x);

	public abstract T create(T a);

	public abstract T update(T a);

	public abstract T delete(T a);

}
