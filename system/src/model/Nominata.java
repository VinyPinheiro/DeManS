package model;

import java.util.Vector;

public class Nominata {
	
	private Vector<Office> nominata;
	
	public Nominata(){
		nominata = new Vector<Office>();
	}
	
	public void addOffice(Office office){
		this.nominata.add(office);
	}
}
