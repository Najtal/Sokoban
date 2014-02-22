package Bizz;

import java.io.Serializable;
import java.util.ArrayList;

import Util.Util;

public class SaveMap implements Serializable {

	private ArrayList<Save> tabSave;
	private int nbSauvegarde;
	
	/**
	 * Constructeur
	 */
	public SaveMap() {
		
		this.tabSave = new ArrayList<>();
		for (int i = 0; i < 6*13+1; i++) {
			tabSave.add(i, null);
		}
		this.nbSauvegarde = 0;
		
	}
	
	public void addSave(Save ns) {
		Util.checkObject(ns);
		
		Save as = tabSave.get(ns.getId());
		if (as != null) {
			if (as.getNbDeplacements() < ns.getNbDeplacements())
				return;
		}
		
		tabSave.add(ns.getId(), ns);
		nbSauvegarde++;
	}
	
	public int getNbSauvegarde() {
		return nbSauvegarde;
	}

	public Save getSave(int id) {
		return tabSave.get(id);
	}

	public int getNbSauvegardeDansLevel(int i) {
		System.out.println(i);
		
		int count = 0;
		for (int j = i; j < i+13; j++) {
			if (tabSave.get(j) != null) 
				count++;
		}
		return count;
	}
	
}
