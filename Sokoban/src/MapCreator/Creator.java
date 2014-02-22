package MapCreator;

import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Bizz.Smap;

public class Creator extends JFrame {
    
	private static final int TAILLE_IMAGE_MAP = 80;
	private static final String CHEMIN = "images/oeufs/";
	
	public static void main(String[] args) {
		
		ArrayList<Smap> mapTab = new ArrayList<Smap>(0);	
		
		
		// niveau 1
		int m1[][] = {	{ 0, 0, 1, 1, 1, 1, 1, 1, 1},
						{ 0, 0, 1, 2, 2, 2, 2, 2, 1},
						{ 0, 0, 1, 2, 3, 3, 3, 2, 1},
						{ 0, 0, 1, 2, 3, 3, 3, 2, 1},
						{ 0, 0, 1, 2, 4, 2, 4, 2, 1},
						{ 1, 1, 1, 1, 2, 2, 2, 1, 1},
						{ 1, 2, 2, 4, 4, 4, 4, 2, 1},
						{ 1, 2, 2, 2, 2, 2, 2, 2, 1},
						{ 1, 2, 2, 1, 1, 1, 2, 2, 1},
						{ 1, 2, 5, 1, 1, 1, 2, 2, 1},
						{ 1, 1, 1, 1, 1, 1, 1, 1, 1}};
		
		int m2[][] = {	{ 1, 1, 1, 1, 1, 0},
						{ 1, 3, 3, 3, 1, 0},
						{ 1, 1, 4, 2, 1, 0},
						{ 1, 1, 5, 2, 1, 1},
						{ 1, 2, 4, 4, 2, 1},
						{ 1, 2, 2, 2, 1, 1},
						{ 1, 2, 2, 1, 1, 0},
						{ 1, 1, 1, 1, 0, 0}};
			
		Image icon1 = new ImageIcon(CHEMIN + "e1" +".png").getImage().getScaledInstance(TAILLE_IMAGE_MAP, TAILLE_IMAGE_MAP, 1);
		
		try {
			
			mapTab.add(new Smap(m1, "Map titre num 1", 1, "Slayer", icon1));
	
			for (int j = 1; j < 77; j++) {
				Image icon = new ImageIcon(CHEMIN + "e" + (((j)%13)+1)+".png").getImage().getScaledInstance(TAILLE_IMAGE_MAP, TAILLE_IMAGE_MAP, 1);
				mapTab.add(new Smap(m2, "Map titre num "+(j+1), 1, "Slayer", icon));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		try {
	         ObjectOutputStream flotEcriture = new ObjectOutputStream(new FileOutputStream("data/maps.dta"));

	         System.out.println("nombre de maps : " + mapTab.size());
	         
	         for (Smap map : mapTab) {
	        	 System.out.println("on écrit : " + map.getNomMap());
	        	 flotEcriture.writeObject(map);
	         }
	         
	         flotEcriture.close();
	      }

	      catch(IOException e) {
	    	  System.out.println(e.getMessage());
	    	  e.printStackTrace();
	      }
		
		
	}
	
}
