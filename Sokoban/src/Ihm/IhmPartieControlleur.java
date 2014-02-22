package Ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bizz.Smap;

/**
 * Contrôleur de la partie
 * @author Jean-Vital
 */
public class IhmPartieControlleur extends JPanel implements KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private Smap map;
	private Model model;
	private IhmPartieModel partieModel;
	
	private JLabel top;
	private JLabel left;
	private JLabel bottom;
	private JLabel right;

	public IhmPartieControlleur(IhmPartieModel ihmPartieModel, Model model, Smap smap) {

		this.partieModel = ihmPartieModel;
		this.model = model;
		this.map = smap;
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(300, HEIGHT));
		this.setBackground(Param.COLOR_BACKGROUND_GAME_MENU);
		
		
		// On ajoute l'image du level;
		JPanel north = new JPanel(new BorderLayout());
		north.setOpaque(false);
		JLabel level = new JLabel(model.getLevelImage(), JLabel.CENTER);
		level.setBorder(new EmptyBorder(20, 0, 15, 0));
		north.add(level, BorderLayout.NORTH);
		
		JPanel northInfo = new JPanel(new GridLayout(0, 1));
		northInfo.setOpaque(false);
		JLabel jlI1 = new JLabel("Monde : " + model.getSelectPartieLvl(), JLabel.CENTER);
		JLabel jlI2 = new JLabel("Niveau : " + smap.getIdentifiant()%13, JLabel.CENTER);
		JLabel jlI3 = new JLabel("Difficulté : " + smap.getLvlDifficulte() + "/3", JLabel.CENTER);
		
		jlI1.setFont(Param.FONT_MAP_INFO);
		jlI2.setFont(Param.FONT_MAP_INFO);
		jlI3.setFont(Param.FONT_MAP_INFO);
		
		jlI1.setForeground(Color.DARK_GRAY);
		jlI2.setForeground(Color.DARK_GRAY);
		jlI3.setForeground(Color.DARK_GRAY);
		
		jlI1.setBorder(new EmptyBorder(4, 0, 4, 0));
		
		northInfo.add(jlI1);
		northInfo.add(jlI2);
		northInfo.add(jlI3);
		
		north.add(northInfo, BorderLayout.CENTER);

		this.add(north, BorderLayout.NORTH);
		
		
		// On ajouter les 4 touches 
		JPanel keys = new JPanel(new GridLayout(2, 3));
		keys.setOpaque(false);

		top = new JLabel(new ImageIcon(Param.arrow_top));
		left = new JLabel(new ImageIcon(Param.arrow_left));
		bottom = new JLabel(new ImageIcon(Param.arrow_botton));
		right = new JLabel(new ImageIcon(Param.arrow_right));

		top.addMouseListener(this);
		left.addMouseListener(this);
		bottom.addMouseListener(this);
		right.addMouseListener(this);
		
		keys.add(new JLabel(" "));
		keys.add(top);
		keys.add(new JLabel(" "));
		keys.add(left);
		keys.add(bottom);
		keys.add(right);
		
		keys.setBorder(new EmptyBorder(20, 25, 30, 25));
		
		this.add(keys, BorderLayout.SOUTH);
		
		
		// On ajoute le listener de touches
		model.getFrame().addKeyListener(this);
		
	}

	
	/**
	 * Methode appelée lors d'un appel de touche ou des flèches
	 * @param keyCode
	 */
	private void actionDeplacement(int keyCode) {
				
		if (partieModel.getPartieVue().enCours)
			return;
		
		if (keyCode == 38) {
			partieModel.getPartieVue().depHaut();
		} else if (keyCode == 37) {
			partieModel.getPartieVue().depGauche();
		} else if (keyCode == 40) {
			partieModel.getPartieVue().depBas();
		} else if (keyCode == 39) {
			partieModel.getPartieVue().depDroite();
		}			

	}
	
	
	public Smap getMap() {
		return map;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		actionDeplacement(ke.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		if (me.getSource() == top) {
			actionDeplacement(38);
		} else if (me.getSource() == left) {
			actionDeplacement(37);
		} else if (me.getSource() == bottom) {
			actionDeplacement(40);
		} else if (me.getSource() == right) {
			actionDeplacement(39);
		}
	}

}



