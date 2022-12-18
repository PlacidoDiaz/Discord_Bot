package modelo;

public class Jugador {
	String discid, usuario;
	int id, dinero, lvl, exp, att, def, hp, maxhp, item1, item2, item3, item4, item5;
	double porcentaje;
	
	// DATABASE
	public Jugador(int id,String discid, String usuario, int dinero, int lvl, int exp, int att, int def, 
			int hp, int maxhp, double porcentaje, int item1, int item2, int item3, int item4, int item5) {
		this.id = id;
		this.discid = discid;
		this.usuario = usuario;
		this.dinero = dinero;
		this.lvl = lvl;
		this.exp = exp;
		this.att = att;
		this.def = def;
		this.hp = hp;
		this.maxhp = maxhp;
		this.porcentaje = porcentaje;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.item4 = item4;
		this.item5 = item5;
	}

	// DEFAULT
	public Jugador() {
		this.dinero = 100;
		this.lvl = 1;
		this.exp = 0;
		this.porcentaje = 0.00;
		this.att = 5;
		this.def = 5;
		this.hp = 10;
		this.maxhp = 10;
		this.item1 = 0;
		this.item2 = 0;
		this.item3 = 0;
		this.item4 = 0;
		this.item5 = 0;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int deff) {
		this.def = deff;
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

	public int getItem1() {
		return item1;
	}

	public void setItem1(int item1) {
		this.item1 = item1;
	}

	public int getItem2() {
		return item2;
	}

	public void setItem2(int item2) {
		this.item2 = item2;
	}

	public int getItem3() {
		return item3;
	}

	public void setItem3(int item3) {
		this.item3 = item3;
	}

	public int getItem4() {
		return item4;
	}

	public void setItem4(int item4) {
		this.item4 = item4;
	}

	public int getItem5() {
		return item5;
	}

	public void setItem5(int item5) {
		this.item5 = item5;
	}
	
	
	
}
