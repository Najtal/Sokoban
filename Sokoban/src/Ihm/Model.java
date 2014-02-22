package Ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bizz.Save;
import Bizz.SaveMap;
import Bizz.Smap;

public class Model {

	private IhmLoader loader;
	// Maps
	private static ArrayList<Smap> aMap;
	public int nbMapChargees = 0;
	// Save
	private static SaveMap mSav;
	public int nbSavegarde = 0;

	// Fenetre
	private JFrame maFenetre;
	
	// Menu de sélection de niveau
	private IhmLevelSelector levelSelector;
	private ImageIcon levelImage;

	// Grid de sélection de la map
	private ArrayList<JPanel> mapGrid;
	private int selectPartieLvl;
	
	// Chargement
	private JPanel chargement;
	
	// Partie
	private IhmPartieModel partie;
	private Smap MapEnCours;


	/**
	 * Default constructor
	 * @param loader ref de la JFRAME visible lors du chargement
	 */
	public Model(IhmLoader loader) {
		
		this.loader = loader;
		this.loader.setText("Initialisation des données");
		this.aMap = new ArrayList<Smap>();	
		
		// On défini la fenetre
		this.maFenetre = new JFrame();
		
		maFenetre.setSize(900, 600);
		maFenetre.setTitle(Param.NOM_APPLICATION);
		maFenetre.setLocationRelativeTo(null);
		maFenetre.setResizable(false);
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre.setIconImage(Param.logo);
		
		maFenetre.getContentPane().setBackground(Param.COLOR_BACKGROUND);
		
		chargement = new IhmChargement();
		
		// On charge
		try {
			load();
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(loader, "Une erreur s'est produite lors du chargement des fichiers du jeu.");
			exit();
		}
				
	}
	



	/**
	 * Methode appelée a l'initialisation de la class Model.
	 * @throws InterruptedException 
	 */
	private void load() throws InterruptedException {
		
		// Charge tous les niveaux en mémoire
		this.loader.setText("chargement des données du jeu en mémoire");
		initLvls();
		
		Thread.currentThread().sleep(100);

		this.loader.setText("chargement des sauvegardes");
		initSavs();
		
		Thread.currentThread().sleep(100);
		
		this.loader.setText("chargement des images");
		loadImages();
		
	}

	/**
	 * Charge dans le modele toute les maps.
	 */
	private void initLvls() {
		try {
	        FileInputStream fis = new FileInputStream ("data/maps.dta");
	        ObjectInputStream ois = new ObjectInputStream (fis);
	        
	        while (fis.available() > 0) {
	            Smap map = (Smap) ois.readObject();
		        aMap.add(map);
		        nbMapChargees++;
	        }
	        
	        ois.close();

		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * Charge dans le modèle les données de jeu sauvegardées.
	 */
	private void initSavs() {
		try {
	        FileInputStream fis = new FileInputStream(System.getProperty("user.home")+
   				 "/AppData/Local/CrazyRabbits/save.dta");
	        ObjectInputStream ois = new ObjectInputStream (fis);
	        while (fis.available() > 0) {
	        	SaveMap savem = (SaveMap) ois.readObject();
	        	this.mSav = savem;
	        }
	        ois.close();
		} catch (ClassNotFoundException | IOException e) {
			// Pas encore de partie enregistrée -> premiere ouverture du jeu -> on crée le fichier
			
			// On génére un nouveau objet de sauvegarde
			this.mSav = new SaveMap();
			
			// On crée le répertoire de sauvegarde
			boolean success = (new File(System.getProperty("user.home")+"/AppData/Local/CrazyRabbits")).mkdirs();
			if (!success) {
				JOptionPane.showMessageDialog(maFenetre, "Erreur lors de l'écriture des fichiers de sauvegarde", "Fatal Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
			// On enregistre le fichier de sauvegarde
			enregistreSaves(mSav);
			
		}
	}
	
	/**
	 * Enregistre la structure de sauvegarde
	 * @param sm structure de sauvegarde
	 */
	private void enregistreSaves(SaveMap sm) {
		try {
	         ObjectOutputStream flotEcriture = new ObjectOutputStream(
	        		 new FileOutputStream(System.getProperty("user.home")+
	        				 "/AppData/Local/CrazyRabbits/save.dta"));
        	 flotEcriture.writeObject(sm);
	         flotEcriture.close();
	      } catch(IOException e) {
	    	  System.out.println(e.getMessage());
	    	  e.printStackTrace();
	      }
	}
	
	/**
	 * Charge dans le modèle toute les images nécessaires.
	 */
	private void loadImages() {
		// TODO faire tous les chargements d'image ici
	}

	/**
	 * Affiche les maps (utile pour le debug)
	 */
	private void afficherMaps() {
		for (Smap m : aMap) {
			System.out.println(m.toString());			
		}
	}
	
	
	/**
	 * Construit les JPanels du grid
	 */
	void buildGrid(int lvl) {

		this.mapGrid = new ArrayList<JPanel>();
		
		/*
		 * 6 levels
		 * 13 maps par level.
		 * donc 
		 */
		
		int debMap = (lvl-1)*13;
		
		// Pour chaque SMAP on cree une JPanel
		for (int i = debMap; i < debMap+13; i++) {
					
			Smap smap = aMap.get(i);
			
			// IMAGE
			JPanel map = new JPanel(new BorderLayout());
			
			map.setBackground(Param.COLOR_BACKGROUND);
			
			map.setBorder(BorderFactory.createLineBorder(Param.COLOR_BACKGROUND, 1));
				
			JLabel pic;
			
			// INFO
			JPanel infoPanel = new JPanel(new BorderLayout());
			infoPanel.setOpaque(false);
			JPanel info = new JPanel(new GridLayout(0, 1));
			info.setOpaque(false);
			info.setBorder(new EmptyBorder(20, 0, 10, 20));
			
			JLabel nom = new JLabel("nom: \t"+smap.getNomMap());
			JLabel niveau = new JLabel("niveau: \t"+smap.getLvlDifficulte());
			info.add(nom);
			nom.setFont(Param.FONT_INFO_MAP_TITRE);
			nom.setForeground(Param.COLOR_INFO_MAP_TITRE);
			info.add(niveau);
			niveau.setFont(Param.FONT_INFO_MAP);
			niveau.setForeground(Param.COLOR_INFO_MAP);
			
			if (this.estReussie(smap) || this.estAccessible(smap)) {
				if (this.estReussie(smap)) {
					// TODO
					System.out.println("map: " + smap.getIdentifiant() + " est gagnée");
					Save s = getSav(smap);
					JLabel temps = new JLabel("temps: \t" + s.getSecondesDeJeu() + " secondes");
					temps.setFont(Param.FONT_INFO_MAP);
					temps.setForeground(Param.COLOR_INFO_MAP);
					JLabel joueur = new JLabel("nombre de coups: \t" + s.getNbDeplacements());
					joueur.setFont(Param.FONT_INFO_MAP);
					joueur.setForeground(Param.COLOR_INFO_MAP);
					info.add(temps);
					info.add(joueur);
				}
					pic = new JLabel(smap.getImage());
					pic.setBorder(new EmptyBorder(10, 10, 10, 20));
					map.add(pic, BorderLayout.WEST);
								
			} else {
				pic = new JLabel(new ImageIcon(Param.unknow));
				pic.setBorder(new EmptyBorder(10, 25, 10, 20));
				map.add(pic, BorderLayout.WEST);
			}

			infoPanel.add(info, BorderLayout.NORTH);
			map.add(infoPanel, BorderLayout.CENTER);
			
			mapGrid.add(map);
		}
	}
	
	
	/**
	 * Indique si cette map est accessible ou pas
	 * @param smap carte
	 * @return vrai si la carte peut etre jouee, faux si non
	 */
	public boolean estAccessible(Smap smap) {

		if (smap.getIdentifiant()%13 <= 3 && smap.getIdentifiant()%13 != 0) 
			return true;
		
		int nbMapGagneesDuLevel = mSav.getNbSauvegardeDansLevel(selectPartieLvl);
		//System.out.println("elem sauves dans level: " + selectPartieLvl + " = " + nbElemDuLevel);
		
		if(smap.getIdentifiant() <= (selectPartieLvl-1)*13 + nbMapGagneesDuLevel+2) {
			System.out.println("map id:"+smap.getIdentifiant()+ ", code: " +(selectPartieLvl-1)*13 + nbMapGagneesDuLevel+2);
			return true;
		}
		return false;
	}

	private Save getSav(Smap smap) {
		return mSav.getSave(smap.getIdentifiant());
	}

	public boolean estReussie(Smap smap) {
		if (mSav.getSave(smap.getIdentifiant()) != null)
			return true;
		return false;
	}

	
	public Smap getSmap(JPanel map) {
		for (int i = 0; i < mapGrid.size(); i++) {
			if (mapGrid.get(i) == map) {
				return aMap.get(i+(13*(selectPartieLvl-1)));
			}
		}
		return null;
	}


	/**
	 * Récupère le grid de JPanels de la sélection du niveau précédemment construite
	 * @return
	 */
	public ArrayList<JPanel> getMapGrid() {
		return mapGrid;
	}
	
	public int getNbMapChargees() {
		return nbMapChargees;
	}

	public int getNbSavegarde() {
		return nbSavegarde;
	}
	
	
	
	public static ArrayList<Smap> getaMap() {
		return aMap;
	}

	private void exit() {
		// TODO
		
	}

	
	public void setLevelSelector(IhmLevelSelector ihmLevelSelector) {
		this.levelSelector = ihmLevelSelector;
		maFenetre.getContentPane().removeAll();
		maFenetre.add(ihmLevelSelector);
		maFenetre.revalidate();			
	}
	

	public void setLevelSelector() {
		maFenetre.getContentPane().removeAll();
		maFenetre.add(new IhmLevelSelector(this));
		maFenetre.revalidate();	
	}

	public void setGameSelector(int i) {
		
		this.selectPartieLvl = i;
		JPanel gameSelector = new IhmGameSelector(this, i);
		
		maFenetre.getContentPane().removeAll();
		maFenetre.add(gameSelector);
		maFenetre.revalidate();			
	}

	public void setMenuSelectorVisibility(boolean b) {
		maFenetre.setVisible(true);
	}

	/**
	 * Appelée lors de la sélection d'une map
	 * @param smap carte a charger
	 */
	public void lancerPartie(Smap smap) {
		
		maFenetre.getContentPane().removeAll();
		maFenetre.add(chargement);
		maFenetre.revalidate();
		
		MapEnCours = smap;
		new IhmPartieModel(this, smap);
	}
	
	/**
	 * Partie finie
	 */
	public void gagne() {
		// enregistrer victoire !
		//JOptionPane.showInternalInputDialog(maFenetre, "Quel est votre nom ?");
		Save ns = new Save(MapEnCours, "Slayer", this.partie.getDateDebut(), this.partie.getNbDeplacements());
		mSav.addSave(ns);
		
		// Enregistre le fichier
		this.enregistreSaves(this.mSav);
				
		/* réinitialise
		partie = null;
		MapEnCours = null;
		setGameSelector(selectPartieLvl);
		*/
	}
	
	/**
	 * Appelée par l'IhmPartieModel pour s'afficher
	 * @param partie
	 */
	public void setPartie(IhmPartieModel partie) {
		
		this.partie = partie;
		
		maFenetre.getContentPane().removeAll();
		maFenetre.add(partie);
		maFenetre.revalidate();
	}
	
	/**
	 * Appelée lors du retour d'une partie
	 */
	public void backToSelectMenu() {
		setGameSelector(selectPartieLvl);
		MapEnCours = null;
	}



	/**
	 * recoit un int entre 1 et 6 et doit renvoyer si tous les maps de ce niveau ont été réalisés
	 * @param i numéro du niveau
	 * @return si le niveau i peut etre joué
	 */
	public boolean isLevelDispo(int lvl) {
		if (lvl == 1)
			return true;
				
		if (mSav.getNbSauvegardeDansLevel(lvl-1) == 13)
			return true;
		
		return false;
	}

	public void setLevelImage(ImageIcon imageIcon) {
		this.levelImage = imageIcon;
	}
	
	public ImageIcon getLevelImage() {
		return levelImage;
	}
	
	public int getSelectPartieLvl() {
		return selectPartieLvl;
	}

	public JFrame getFrame() {
		return maFenetre;
	}


	
}
