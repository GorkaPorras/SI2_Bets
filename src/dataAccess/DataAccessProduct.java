package dataAccess;


import java.util.Vector;
import domain.Mugimendua;
import domain.Erabiltzailea;

public class DataAccessProduct {
	public Vector<Mugimendua> getMugimenduak(String izena) {
		Erabiltzailea er = getErabiltzailea(izena);
		return er.getMugi();
	}

	public Erabiltzailea getErabiltzailea(String erab) {
		Erabiltzailea er = DataAccess.db.find(Erabiltzailea.class, erab);
		return er;
	}
}