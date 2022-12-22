package controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.JugadorDao;
import modelo.Jugador;

public class JugadorControlador {

	private Connection cn;

	public JugadorControlador(Connection cn) {
		this.cn=cn;
	}
	
	public Jugador getJugador(String idDiscord) throws SQLException {
		JugadorDao jugador=new JugadorDao(cn);
		return jugador.devuelveJugador(idDiscord);
	}

	public boolean agregarJugador(Jugador nuevoJugador) throws SQLException {
		boolean agregado=false;
		JugadorDao jugadorDao=new JugadorDao(cn);
		jugadorDao.agregarJugador(nuevoJugador);
		agregado=true;
		return agregado;
	}
	
	public boolean ganaEXPJugador(int EXP, Jugador jugador) throws SQLException {
		boolean agregado=false;
		JugadorDao jugadorDao=new JugadorDao(cn);
		int EXPtotal = jugador.getExp()+EXP;
		int nuevoNivel = jugadorDao.devuelveNivelNuevo(EXPtotal);
		jugadorDao.actualizaNivelJugador(nuevoNivel ,EXPtotal, jugador);
		agregado=true;
		return agregado;
	}
	
}
