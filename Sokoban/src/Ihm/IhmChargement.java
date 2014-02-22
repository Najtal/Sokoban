package Ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IhmChargement extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPanel header;
	private JLabel titre;
	
	private JPanel chargement;
	
	public IhmChargement()  {
		
		this.setLayout(new BorderLayout());

		initHeader();
	
		// On change le titre
		titre.setText("Chargement  du  monde...");
		
		// On affiche le lapin de chargement...
		chargement = new JPanel(new BorderLayout());
		chargement.setBackground(Param.COLOR_BACKGROUND_LOADING);
		JLabel picLoad = new JLabel(Param.loading);
		chargement.add(picLoad, BorderLayout.CENTER);
		this.add(chargement, BorderLayout.CENTER);
		
		header.setBackground(Param.COLOR_BACKGROUND_LOADING);
		titre.setForeground(Color.DARK_GRAY);
							
	}
	
	
	
	private void initHeader() {
		
		header = new JPanel(new BorderLayout());
		header.setBackground(Param.COLOR_BACKGROUND);
		header.setSize(WIDTH, 30);
		header.setPreferredSize(new Dimension(WIDTH, 60));
		
		titre = new JLabel("Choisissez une carte");
		titre.setBorder(new EmptyBorder(10, 35, 0, 20));
		titre.setFont(Param.FONT_TITRE);
		titre.setForeground(Color.WHITE);
		
		header.add(titre, BorderLayout.WEST);
		this.add(header, BorderLayout.NORTH);
	}
}
