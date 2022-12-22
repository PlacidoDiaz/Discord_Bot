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
			int hp=rs.getInt("hp");	
			int hpmax=rs.getInt("maxhp");	
			int item1=rs.getInt("item1");	
			int item2=rs.getInt("item2");	
			int item3=rs.getInt("item3");	
			int item4=rs.getInt("item4");	
			int item5=rs.getInt("item5");	
	   
			jugador =new Jugador(id, discid, usuario, dinero, lvl, exp, att, def, hp, hpmax, porcentaje, item1, item2, item3, item4, item5);
			//listaJugador.add(jugador);
			
		}
		//rs=null;
		//pst=null;
		return jugador;
	}
	
	// AÑADE JUGADOR
	public boolean agregarJugador(Jugador nuevoJugador) throws SQLException {
		boolean agregado=false;
		
		String sql = "INSERT INTO player VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = cn.prepareStatement(sql);

		System.out.println(nuevoJugador.getDiscid());
		
		pst.setInt(1, 0);
		pst.setString(2, nuevoJugador.getDiscid());
		pst.setString(3, nuevoJugador.getUsuario());
		pst.setInt(4, nuevoJugador.getDinero());
		pst.setInt(5, nuevoJugador.getLvl());
		pst.setInt(6, nuevoJugador.getExp());
		pst.setDouble(7, nuevoJugador.getPorcentaje());
		pst.setInt(8, nuevoJugador.getAtt());
		pst.setInt(9, nuevoJugador.getDef());
		pst.setInt(10, nuevoJugador.getHp());
		pst.setInt(11, nuevoJugador.getMaxhp());
		pst.setInt(12, 0);
		pst.setInt(13, 0);
		pst.setInt(14, 0);
		pst.setInt(15, 0);
		pst.setInt(16, 0);

		pst.executeUpdate();
		//pst=null;
		
		agregado=true;
		
		return agregado;
	}
	
	// DEVUELVE NIVEL DEPENDIENDO DE LA EXP
	public int devuelveNivelNuevo(int EXPtotal) throws SQLException {

		String sql="SELECT lvl FROM levels WHERE exp <= '"+EXPtotal+"' ORDER BY lvl DESC LIMIT 1";
		PreparedStatement pst=cn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		int nivel = 0;
		
		rs.next();
		nivel=rs.getInt("lvl");
		
		return nivel;
	}
	
	// ACTUALIZA NIVEL Y EXP
	public void actualizaNivelJugador(int nuevoNivel, int EXP, Jugador jugador) throws SQLException {
		
		String sql = "update player SET lvl=?, exp=? WHERE discid='"+jugador.getDiscid()+"' " ;
		PreparedStatement preparedStatement = cn.prepareStatement(sql);
	
		preparedStatement.setInt(1, nuevoNivel);
		preparedStatement.setInt(2, EXP);
	
		preparedStatement.executeUpdate();
		//preparedStatement=null;
		
	}
	
}
