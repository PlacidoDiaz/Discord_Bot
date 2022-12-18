package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Jugador;

public class JugadorDao {

	private Connection cn;

	public JugadorDao(Connection cn) {
		this.cn=cn;
	}
	
	public Jugador devuelveJugador(String idDiscord) throws SQLException {
		//List<Jugador> listaJugador=new ArrayList<Jugador>();
		String sql="SELECT * FROM player WHERE discid = '"+idDiscord+"' ";
		PreparedStatement pst=cn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		Jugador jugador = null;
		
		while (rs.next()) {
			int id=rs.getInt("id");
			String discid=rs.getString("discid");
			String usuario=rs.getString("usuario");
			int dinero=rs.getInt("dinero");
			int lvl=rs.getInt("lvl");
			int exp=rs.getInt("exp");
			double porcentaje=rs.getDouble("porcentaje");
			int att=rs.getInt("att");
			int def=rs.getInt("def");	
			int hp=rs.getInt("def");	
			int hpmax=rs.getInt("def");	
			int item1=rs.getInt("item1");	
			int item2=rs.getInt("item2");	
			int item3=rs.getInt("item3");	
			int item4=rs.getInt("item4");	
			int item5=rs.getInt("item5");	
	   
			jugador =new Jugador(id, discid, usuario, dinero, lvl, exp, att, def, hp, hpmax, porcentaje, item1, item2, item3, item4, item5);
			//listaJugador.add(jugador);
			
		}
		return jugador;
	}
	
	// AÑADE JUGADOR
	public boolean agregarJugador(Jugador nuevoJugador) throws SQLException {
		boolean agregado=false;
		
		String sql = "INSERT INTO player VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = cn.prepareStatement(sql);

		System.out.println(nuevoJugador.getDiscid());
		
		preparedStatement.setInt(1, 0);
		preparedStatement.setString(2, nuevoJugador.getDiscid());
		preparedStatement.setString(3, nuevoJugador.getUsuario());
		preparedStatement.setInt(4, nuevoJugador.getDinero());
		preparedStatement.setInt(5, nuevoJugador.getLvl());
		preparedStatement.setInt(6, nuevoJugador.getExp());
		preparedStatement.setDouble(7, nuevoJugador.getPorcentaje());
		preparedStatement.setInt(8, nuevoJugador.getAtt());
		preparedStatement.setInt(9, nuevoJugador.getDef());
		preparedStatement.setInt(10, nuevoJugador.getHp());
		preparedStatement.setInt(11, nuevoJugador.getMaxhp());
		preparedStatement.setInt(12, 0);
		preparedStatement.setInt(13, 0);
		preparedStatement.setInt(14, 0);
		preparedStatement.setInt(15, 0);
		preparedStatement.setInt(16, 0);

		preparedStatement.executeUpdate();
		preparedStatement=null;
		
		agregado=true;
		
		return agregado;
	}
	
}
