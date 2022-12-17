package modelo;

public class Jugador {
	
	String discid, usuario;
	int dinero, lvl, exp, suelo, mapa, att, deff, hp, maxhp;
	double porcentaje;
	
	public Jugador(String discid, String usuario, int dinero, int lvl, int exp, int suelo, int mapa, int att, int deff, 
			int hp, int maxhp, double porcentaje) {
		super();
		this.discid = discid;
		this.usuario = usuario;
		this.dinero = dinero;
		this.lvl = lvl;
		this.exp = exp;
		this.suelo = suelo;
		this.mapa = mapa;
		this.att = att;
		this.deff = deff;
		this.hp = hp;
		this.maxhp = maxhp;
		this.porcentaje = porcentaje;
	}

	public String getDiscid() {
		return discid;
	}

	public void setDiscid(String discid) {
		this.discid = discid;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getSuelo() {
		return suelo;
	}

	public void setSuelo(int suelo) {
		this.suelo = suelo;
	}

	public int getMapa() {
		return mapa;
	}

	public void setMapa(int mapa) {
		this.mapa = mapa;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getDeff() {
		return deff;
	}

	public void setDeff(int deff) {
		this.deff = deff;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxhp() {
		return maxhp;
	}

	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
}
