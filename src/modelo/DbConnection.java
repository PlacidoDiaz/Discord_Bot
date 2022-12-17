package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DbConnection {
	private static Dotenv config;

	Connection conn = null;
		
	public DbConnection() {
		config = Dotenv.configure().ignoreIfMissing().load();
		String usuario = config.get("USER");
		String password = config.get("PASS");
		String servidor = config.get("SERVER");
		String basedatos = config.get("DATABASE");
		
		String parametros = "?useSSL=false&serverTimezone=UTC";
		String url = "jdbc:mysql://"+ servidor + "/" + basedatos + parametros;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, usuario, password);
			if (conn != null) {
				System.out.println("Connecting database [" + conn + "] OK");
			}
		} catch (ClassNotFoundException e) { 
			System.out.println("Excepcion driver: " + e.getMessage());
		} catch (SQLException e) { 
			System.err.println("Excepcion conexion: " + e.getMessage());
		}

	}

	public Connection getConnection() {
		return conn;
	}

	public void disconnect() {
		System.out.println("Closing database: [" + conn + "] OK");
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}

	}
	 
	   
}

