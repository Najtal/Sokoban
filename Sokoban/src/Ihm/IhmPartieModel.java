package Ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bizz.Smap;

public class IhmPartieModel extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;

	private IhmPartieVue partie;
	private IhmPartieControlleur partieMenu;

	private Smap smap;
	private JPanel partieHeader;
	private JLabel jlRetour;

	private Model model;
	
	private GregorianCalendar debut;
	private int nbDeplacements;

	/**
	 * Controleur
	 * @param model 
	 * @param smap
	 */
	public IhmPartieModel(Model model, Smap smap) {

		this.model = model;
		this.smap = smap;
		this.setBackground(Param.COLOR_BACKGROUND_GAME);
		this.setLayout(new BorderLayout());
		this.debut = new GregorianCalendar();
		this.nbDeplacements = 0;
		
		System.out.println("chargement de la partie : " + smap.getNomMap());
		
		// On crée et ajoute la vue de la partie
		partie = new IhmPartieVue(this.getSmap(), this);
		this.add(partie, BorderLayout.CENTER);
		
		// On crée et on ajoute le controleur de la partie
		partieMenu = new IhmPartieControlleur(this, model, this.getSmap());
		this.add(partieMenu, BorderLayout.WEST);
		
		// On crée le Header
		partieHeader = new JPanel(new BorderLayout());
		partieHeader.setBackground(Color.DARK_GRAY);
		partieHeader.setPreferredSize(new Dimension(900, 60));
		
		JLabel titre = new JLabel(smap.getNomMap());
		titre.setBorder(new EmptyBorder(10, 35, 0, 20));
		titre.setFont(Param.FONT_TITRE);
		titre.setForeground(Color.WHITE);
		
		Image retour = Param.retour.getImage().getScaledInstance(45, 45, 1);
		jlRetour = new JLabel(new ImageIcon(retour));
		jlRetour.setBorder(new EmptyBorder(0, 0, 0, 50));
		jlRetour.addMouseListener(this);
		
		partieHeader.add(jlRetour, BorderLayout.EAST);
		
		partieHeader.add(titre, BorderLayout.WEST);
		
		this.add(partieHeader, BorderLayout.NORTH);
		
		// On change le contenu de la fenetre
		model.setPartie(this);
	}

	public void gagne() {
		// TODO Faire une plus belle victoire que ca ;)
		JOptionPane.showConfirmDialog(this, "Vous avez gagné ce niveau !\n Bien joué !", "Victoire !", JOptionPane.OK_OPTION);
		model.gagne();
		model.backToSelectMenu();
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		model.backToSelectMenu();
	}

	public IhmPartieVue getPartieVue() {
		return partie;
	}

	public GregorianCalendar getDateDebut() {
		return debut;
	}

	public int getNbDeplacements() {
		return this.nbDeplacements;
	}
	
	public void nbDeplacementsPlus() {
		this.nbDeplacements++;
	}


	/**
	 * @return the smap
	 */
	public Smap getSmap() {
		return smap;
	}
	
}
