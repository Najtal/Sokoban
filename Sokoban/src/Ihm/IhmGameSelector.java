package Ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Bizz.Smap;

public class IhmGameSelector extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private Model model;
	
	private JPanel header;
	private JLabel titre;
	private JPanel thegrid;
	private JScrollPane scroller;
	private int lvl;
	
	JLabel jlRetour; // bouton retour
	
	public IhmGameSelector(Model model, int lvl) {
		this.model = model;
		this.lvl = lvl;
		
		this.setLayout(new BorderLayout());		
		this.setBackground(Param.COLOR_BACKGROUND);
						
		initHeader();
		
		this.add(header, BorderLayout.NORTH);
		
		initGridMap();
		
		scroller = new JScrollPane(thegrid);  
		scroller.setBorder(null);
		scroller.setWheelScrollingEnabled(true);
		scroller.getVerticalScrollBar().setUnitIncrement(12);
		this.add(scroller, BorderLayout.CENTER); 		
		
	}

	
	private void initHeader() {
		
		header = new JPanel(new BorderLayout());
		header.setBackground(Color.DARK_GRAY);
		header.setSize(WIDTH, 30);
		header.setPreferredSize(new Dimension(WIDTH, 60));
		
		titre = new JLabel("Choisissez la partie du niveau " + lvl);
		titre.setBorder(new EmptyBorder(10, 35, 0, 20));
		titre.setFont(Param.FONT_TITRE);
		titre.setForeground(Color.WHITE);
		
		header.add(titre, BorderLayout.WEST);
		
		Image retour = Param.retour.getImage().getScaledInstance(45, 45, 1);
		jlRetour = new JLabel(new ImageIcon(retour));
		jlRetour.setBorder(new EmptyBorder(0, 0, 0, 50));
		jlRetour.addMouseListener(this);
		
		header.add(jlRetour, BorderLayout.EAST);
	}
	
	
	private void initGridMap() {
		
		// TODO Mettre le selecteur de menu a l'horizontal
		//thegrid = new JPanel(new GridLayout(2, 0));

		thegrid = new JPanel(new GridLayout(0, 2));
		thegrid.setBackground(Param.COLOR_BACKGROUND);
		thegrid.setBorder(new EmptyBorder(20, 40, 0, 40));
		
		model.buildGrid(lvl);
		
		int i = 0;
		//Pour chauqe Jpanel on affiche dans le grid	
		for (JPanel map : model.getMapGrid()) {	
			
			JPanel empty = new JPanel();
			empty.setPreferredSize(new Dimension(50, 50));
			empty.setBackground(Param.COLOR_BACKGROUND);
			
			JPanel allEgg = new JPanel(new BorderLayout());
			allEgg.setBorder(new EmptyBorder(0, 5, 0, 5));
			allEgg.setOpaque(false);

			if (i%2 == 0) {
				allEgg.add(empty, BorderLayout.SOUTH);
			} else {
				allEgg.add(empty, BorderLayout.NORTH);
			}
			
			map.addMouseListener(this);
			
			allEgg.add(map);
			thegrid.add(allEgg);
			
			i++;
		}
		
	}
	


	@Override
	public void mouseClicked(MouseEvent me) {
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		for (JPanel map : model.getMapGrid()) {		
			if (me.getSource() == map) {
				map.setBorder(BorderFactory.createLineBorder(Color.white, 1));
				this.getRootPane().setCursor (Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent me) {
		for (JPanel map : model.getMapGrid()) {
			if (me.getSource() == map) {
				map.setBorder(BorderFactory.createLineBorder(Param.COLOR_BACKGROUND, 1));
				this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
		}		
	}

	@Override
	public void mousePressed(MouseEvent me) {		
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		
		if (me.getSource() == jlRetour) {
			System.out.println("kikou");
			model.setLevelSelector();
			return;
		}
		
		for (JPanel map : model.getMapGrid()) {
			if (me.getSource() == map) {
				Smap sm = model.getSmap(map);
				
				System.out.println("on lance la map : " + sm.getIdentifiant());

				
				if (model.estAccessible(sm) || model.estReussie(sm)) {
					model.lancerPartie(sm);
				}
			}
		}
	}

	
}
