package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
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

	public static void setConn(Connection conn) {
		MyConnection.conn = conn;
	}
    
}
