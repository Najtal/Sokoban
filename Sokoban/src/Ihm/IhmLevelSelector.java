package Ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IhmLevelSelector extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private Model model;
	
	private JPanel header;
	private JLabel titre;
	private JPanel thegrid;

	private HashMap<Integer, JPanel> mapElemMenuNiveau;
	
	public IhmLevelSelector (Model model) {
		this.model = model;
		this.setLayout(new BorderLayout());
				
		this.setBackground(Param.COLOR_BACKGROUND);
						
		initHeader();
		
		this.add(header, BorderLayout.NORTH);
		
		this.mapElemMenuNiveau = new HashMap<>();
		
		initGridMap();

		this.add(thegrid, BorderLayout.CENTER); 		
		
		model.setLevelSelector(this);
	}

	
	private void initHeader() {
		
		header = new JPanel(new BorderLayout());
		header.setBackground(Color.DARK_GRAY);
		header.setSize(WIDTH, 30);
		header.setPreferredSize(new Dimension(WIDTH, 60));
		
		titre = new JLabel("Choisissez votre niveau de folie");
		titre.setBorder(new EmptyBorder(10, 35, 0, 20));
		titre.setFont(Param.FONT_TITRE);
		titre.setForeground(Color.WHITE);
		
		header.add(titre, BorderLayout.WEST);
	}
	
	
	private void initGridMap() {

		thegrid = new JPanel(new GridLayout(2, 3));
		thegrid.setBackground(Param.COLOR_BACKGROUND);
		thegrid.setBorder(new EmptyBorder(20, 40, 0, 40));
		
		
		for (int i = 0; i < 6; i++) {
			
			JPanel rabbit = new JPanel(new BorderLayout());
			
			rabbit.setBorder(new EmptyBorder(5, 5, 5, 5));
			rabbit.setOpaque(false);
			JLabel image = new JLabel(new ImageIcon("images/rabbits/rab"+i+".png"));
			rabbit.add(image);
			
			JLabel niveauTitre = new JLabel();
			// Check si le level est accessible ou pas.
			if (model.isLevelDispo(i+1)) {
				niveauTitre.setText("Niveau "+(i+1));
			} else {
				niveauTitre.setText("BlOqUé");
				niveauTitre.setForeground(Color.LIGHT_GRAY);
			}
			niveauTitre.setBorder(new EmptyBorder(0, 0, 50, 0));
			niveauTitre.setFont(Param.FONT_LEVEL_SELECTOR);
			niveauTitre.setHorizontalAlignment(JLabel.CENTER);
			rabbit.add(niveauTitre, BorderLayout.SOUTH);
			
			mapElemMenuNiveau.put(i, rabbit);
			rabbit.addMouseListener(this);
			
			thegrid.add(rabbit);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent me) {
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent me) {
		this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
	}

	@Override
	public void mousePressed(MouseEvent me) {		
	}

	@Override
	public void mouseReleased(MouseEvent me) {

		for (int i = 0; i < mapElemMenuNiveau.size(); i++) {
			if (me.getSource() == mapElemMenuNiveau.get(i)) {
				if(model.isLevelDispo(i+1)) {
					model.setLevelImage(new ImageIcon("images/rabbits/rab"+i+".png"));
					model.setGameSelector(i+1);
				}
			}
		}
	}
	
}