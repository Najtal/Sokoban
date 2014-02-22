package Ihm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Bizz.Smap;

/**
 * Vue de la partie
 * @author Jean-Vital
 *
 */
public class IhmPartieVue extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private GregorianCalendar dateDebut;
	
	private IhmPartieModel partieModel;

	private int x=600, y=550;	// Taille du plateau de jeu (en px)
	
	private int nb_elem_largeur;
	private int nb_elem_hauteur;
	
	private int posX_elem_SG; // Position x de l'élément supérieur gauche du plateau de jeu
	private int posY_elem_SG; // Position y de l'élément supérieur gauche du plateau de jeu

	
	private int tailleElements = 40;
	private int[][] tabMap;
	private int[][] tabMapOriginal;
	
	private int maxX, maxY, maxRealX=0, maxRealY=0, xOeufException=-1, yOeufException=-1, oeufRealX = 0, oeufRealY = 0;

	static boolean  enCours = false;
	
	private ImageIcon vide;
	private ImageIcon mur;
	private ImageIcon chemin;
	private ImageIcon trouVide;
	private ImageIcon trouPlein;
	private ImageIcon oeuf;
	private ImageIcon max;

	private ImageIcon maxH;
	private ImageIcon maxHG;
	private ImageIcon maxG;
	private ImageIcon maxBG;
	private ImageIcon maxB;
	private ImageIcon maxBD;
	private ImageIcon maxD;
	private ImageIcon maxHD;

	
	
	public IhmPartieVue(Smap smap, IhmPartieModel partieModel) {

		this.setLayout(new GridLayout());
				
		this.partieModel = partieModel;
		this.dateDebut = new GregorianCalendar();

		this.tabMap = new int[smap.getHauteur()][smap.getLargeur()];
		this.tabMapOriginal = new int[smap.getHauteur()][smap.getLargeur()];
		for (int i = 0; i < smap.getHauteur(); i++) {
			for (int j = 0; j < smap.getLargeur(); j++) {
				tabMap[i][j] = smap.getElem(i, j);
				tabMapOriginal[i][j] = smap.getElem(i, j);
			}
		}
			
		this.setBackground(Param.COLOR_BACKGROUND_GAME);
		
		initialisationDesVE();
		
		initialisationDesImages();
		
		this.setPreferredSize(new Dimension(WIDTH*2, HEIGHT));
		
		this.repaint();
		this.revalidate();
		
	}

	private void initialisationDesImages() {

		// On initialise les surfaces
		vide = new ImageIcon(Param.vide.getImage().getScaledInstance(tailleElements, tailleElements, 1));
		mur = new ImageIcon(Param.boite.getImage().getScaledInstance(tailleElements, tailleElements, 1));
		chemin = new ImageIcon(Param.chemin.getImage().getScaledInstance(tailleElements, tailleElements, 1));
		trouVide = new ImageIcon(Param.star1.getImage().getScaledInstance(tailleElements, tailleElements, 1));
		trouPlein = new ImageIcon(Param.star2.getImage().getScaledInstance(tailleElements, tailleElements, 1));
		oeuf = new ImageIcon(Param.oeuf.getImage().getScaledInstance((tailleElements/2), (tailleElements/2), 1));
		max = new ImageIcon(Param.max_0.getImage().getScaledInstance(tailleElements, tailleElements, 1));

		maxH = new ImageIcon(Param.max_0.getImage().getScaledInstance(tailleElements, tailleElements, 1));
		maxHD = new ImageIcon(Param.max_1.getImage().getScaledInstance(tailleElements, tailleElements, 1));
		maxG = new ImageIcon(Param.max_6.getImage().getScaledInstance(tailleElements, tailleElements, 1));
		maxBG = new ImageIcon(Param.max_5.getImage().getScaledInstance(tailleElements, tailleElements, 1));
		maxB = new ImageIcon(Param.max_4.getImage().getScaledInstance(tailleElements, tailleElements, 1));
		maxBD = new ImageIcon(Param.max_3.getImage().getScaledInstance(tailleElements, tailleElements, 1));
		maxD = new ImageIcon(Param.max_2.getImage().getScaledInstance(tailleElements, tailleElements, 1));
		maxHG = new ImageIcon(Param.max_7.getImage().getScaledInstance(tailleElements, tailleElements, 1));

	}

	private void initialisationDesVE() {

		nb_elem_hauteur = Math.round(y/tailleElements);
		nb_elem_largeur = Math.round(x/tailleElements);
		
		posX_elem_SG = Math.round( (nb_elem_largeur/2) - (tabMap.length/2)); 
		posY_elem_SG = Math.round( (nb_elem_hauteur/2) - (tabMap[0].length/2)); 
	}


	public void paint(Graphics g) {
				
		// On actualise la map en gros
		for (int i = 0; i <= nb_elem_largeur; i++) {
			for (int j = 0; j <= nb_elem_hauteur; j++) {
				// On positionne 			
				if ((i < posX_elem_SG || i >= posX_elem_SG+tabMap.length) 
						|| (j < posY_elem_SG || j >= posY_elem_SG+tabMap[0].length)) {
							vide.paintIcon(this, g, i*tailleElements, j*tailleElements);
				}
			}
		}		
		
		// On parcours le tableau pour placer tous le elements de la map
		for (int k = 0; k < tabMap.length; k++) {
			for (int l = 0; l < tabMap[0].length; l++) {
				/*
				 * 0 : vide
				 * 1 : mur (entour le jeu)
				 * 2 : chemin (pratiquable par slayer)
				 * 3 : trou (la ou il faut mettre la boite)
				 * 4 : oeuf (position d'une boite (donc un chemin aussi)
				 * 5 : position de début du personnage
				 */
				if (tabMap[k][l] == 0)
					vide.paintIcon(this, g, (k+posX_elem_SG)*tailleElements, (l+posY_elem_SG)*tailleElements);
				
				if (tabMap[k][l] == 1)
					mur.paintIcon(this, g, (k+posX_elem_SG)*tailleElements, (l+posY_elem_SG)*tailleElements);
				
				if (tabMap[k][l] == 2)
					chemin.paintIcon(this, g, (k+posX_elem_SG)*tailleElements, (l+posY_elem_SG)*tailleElements);
				
				if (tabMapOriginal[k][l] == 3 && tabMap[k][l] == 3) {
					// Trou vide
					chemin.paintIcon(this, g, (k+posX_elem_SG)*tailleElements, (l+posY_elem_SG)*tailleElements);				
					trouVide.paintIcon(this, g, (k+posX_elem_SG)*tailleElements, (l+posY_elem_SG)*tailleElements);
				} else if (tabMapOriginal[k][l] == 3 && tabMap[k][l] == 4) {
					// Trou rempli
					chemin.paintIcon(this, g, (k+posX_elem_SG)*tailleElements, (l+posY_elem_SG)*tailleElements);				
					trouPlein.paintIcon(this, g, (k+posX_elem_SG)*tailleElements, (l+posY_elem_SG)*tailleElements);				
					//oeuf.paintIcon(this, g, (k+posX_elem_SG)*tailleElements, (k2+posY_elem_SG)*tailleElements);				
				} else if (tabMapOriginal[k][l] == 3 && tabMap[k][l] == 5) {
					// Trou pietiné 
					chemin.paintIcon(this, g, (k+posX_elem_SG)*tailleElements, (l+posY_elem_SG)*tailleElements);
					trouVide.paintIcon(this, g, (k+posX_elem_SG)*tailleElements, (l+posY_elem_SG)*tailleElements);				
				}
				
				if (tabMap[k][l] == 4) {					
					if (tabMapOriginal[k][l] != 3)
						chemin.paintIcon(this, g, (k+posX_elem_SG)*tailleElements, (l+posY_elem_SG)*tailleElements);
					if (k != xOeufException || l != yOeufException) {
						oeuf.paintIcon(this, g, (k+posX_elem_SG)*tailleElements+(tailleElements/4), (l+posY_elem_SG)*tailleElements+tailleElements/4);
					}
				}
				
				// Quand on trouve MAX
				if (tabMap[k][l] == 5) {
					if (tabMapOriginal[k][l] != 3)
						chemin.paintIcon(this, g, (k+posX_elem_SG)*tailleElements, (l+posY_elem_SG)*tailleElements);
					maxX = k;
					maxY = l;
				}
			}
			
			// On paint l'oeuf qui bouge
			if ((xOeufException != -1 && yOeufException != -1 ) && tabMap[xOeufException][yOeufException] == 4) {
				oeuf.paintIcon(this, g, (((xOeufException+posX_elem_SG)*tailleElements)+(tailleElements/4)-oeufRealX), (((yOeufException+posX_elem_SG)*tailleElements)+(tailleElements/4)-oeufRealY));	
			}
			
			// On paint MAX
			max.paintIcon(this, g, (((maxX+posX_elem_SG)*tailleElements)-maxRealX), (((maxY+posY_elem_SG)*tailleElements)-maxRealY));	

		}
	}

	/*
	 * ACTION DE DEPLACEMENT
	 */
	public synchronized void depHaut() {
		
		// TODO tourner softly
		/*if (max != maxH) {
			if (max == maxG) {
				max = maxHG;
			} else if (max == maxD) {
				max = maxHD;
			} else if (max == maxB) {
				max = maxBD;
				partieModel.repaint();
				max = maxD;
				partieModel.repaint();
				max = maxHD;
			}
			partieModel.repaint();
		}*/
		max = maxH;
		partieModel.repaint();
		deplacement(0, -1);
	}

	public synchronized void depGauche() {
		max = maxG;
		partieModel.repaint();
		deplacement(-1, 0);
	}

	public synchronized void depBas() {
		max = maxB;
		partieModel.repaint();
		deplacement(0, 1);
	}

	public synchronized void depDroite() {
		max = maxD;
		partieModel.repaint();
		deplacement(1, 0);		
	}
	
	/**
	 * calcule le deplacement 
	 * @param direction maxX ou maxY + 1 ou + -1
	 */
	private synchronized void deplacement(int x, int y) {
		if (enCours)
			return;
		
		enCours = true;
		
		int xOeuf = 0, yOeuf = 0;
		boolean deplacement = true;
		/*
		 * 0 : vide
		 * 1 : mur (entour le jeu)
		 * 2 : chemin (pratiquable par slayer)
		 * 3 : trou (la ou il faut mettre la boite)
		 * 4 : oeuf (position d'une boite (donc un chemin aussi)
		 * 5 : position de début du personnage
		 */
		//System.out.println("X:"+x+" - Y:"+y);
		if (tabMap[maxX+x][maxY+y] == 1) {
			deplacement = false;
		} else if (tabMap[maxX+x][maxY+y] == 2 || tabMap[maxX+x][maxY+y] == 3) {
			tabMap[maxX+x][maxY+y] = 5;
			tabMap[maxX][maxY] = 2;	
		} // s'il va sur un oeuf
		else if (tabMap[maxX+x][maxY+y] == 4) {
			// Si ok de deplacer l'oeuf
			if (tabMap[maxX+x+x][maxY+y+y] == 2 || tabMap[maxX+x+x][maxY+y+y] == 3) {
				// On défini les places finale
				tabMap[maxX+x+x][maxY+y+y] = 4;
				tabMap[maxX+x][maxY+y] = 5;
				tabMap[maxX][maxY] = 2;
				// On dit qu'il ne faut pas repaint l'oeuf, c'est le timer qui va le faire
				xOeufException = maxX+x+x;
				yOeufException = maxY+y+y;
			} else {
				deplacement = false;
			}
		}
		
		if (tabMapOriginal[maxX][maxY] == 3) {
			tabMap[maxX][maxY] = 3;	
		}
		
		Deplacement d = null;
		// Si partie gagnée
		if (partieGagnee()) {
			partieModel.repaint();
			partieModel.gagne();
			enCours = false;
		} else {
			if (deplacement) {
				d = new Deplacement(x, y, xOeuf, yOeuf);
				partieModel.nbDeplacementsPlus();
				d.start();					
			}
		}
		//enCours = false;
	}

	public boolean partieGagnee() {
		for (int i = 0; i < this.tabMap.length; i++) {
			for (int j = 0; j < this.tabMap[0].length; j++) {
				
				if (partieModel.getSmap().getElem(i, j) == 3 && this.tabMap[i][j] != 4)
					return false;
			}
		}

		return true;
	}
	
	public class Deplacement extends Thread {
	    private Timer timer;
	    private int counter = tailleElements; // the duration
	    private int delay = 3; // temps entre 
	    int x, y;
	    
	    public Deplacement(int x, int y, int xOeuf, int yOeuf) {
	    	this.x = x;
	    	this.y = y;
	    }
	
		public void run() {
	        ActionListener action = new ActionListener() {   
	            public void actionPerformed(ActionEvent event) {
	            	if(counter == 0){
	                    timer.stop();
	        			xOeufException = -1;
	        			yOeufException = -1;
	        			enCours = false;
	                    interrupt();
	                } else {	                	
	        			if (x!=0) {
	        				maxRealX = counter*x;
        					oeufRealX = counter*x;
        					partieModel.repaint();	
	        			} else {
        					maxRealY = counter*y;
        					oeufRealY = counter*y;
        					partieModel.repaint();
	        			} 
	                    counter--;
	                }
	            }
	        };
	
	        timer = new Timer(delay, action);
	        timer.setInitialDelay(0);
	        timer.start();
	        
		}
	}	
	
	
}
