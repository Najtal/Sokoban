package Ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IhmLoader extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private String titre = "Chargement";
	private Dimension dimFenetre = new Dimension(340, 160);	
	
    private JPanel panelBgImg;
    private JPanel panelTextInfo;
    private JLabel textInfo;

	
	public IhmLoader() {
		
		this.setSize(dimFenetre);
		this.setUndecorated(true);
		this.setTitle(titre);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		initBackgound();
        
        this.add(panelBgImg, BorderLayout.NORTH);
		
        initTextInfo();
        
        this.add(panelTextInfo, BorderLayout.SOUTH);
        
		this.setVisible(true);
	}


	private void initBackgound() {
        this.panelBgImg = new JPanel() {
        	private Dimension dimImage = new Dimension(340, 140);
            public void paintComponent(Graphics g) {
                Image img = new ImageIcon("images/prog/w_bgi.jpg").getImage();
                setPreferredSize(dimImage);
                setMinimumSize(dimImage);
                setMaximumSize(dimImage);
                setSize(dimImage);
                setLayout(null);
                
                g.drawImage(img, 0, 0, null);
            } 
        };		
	}
	
	private void initTextInfo() {
        panelTextInfo = new JPanel();
    	Dimension dimTxt = new Dimension(340, 20);

        textInfo = new JLabel("chargement...");
        textInfo.setSize(dimTxt);
        textInfo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        panelTextInfo.add(textInfo);
	}

	public void setText(String txt) {
		this.textInfo.setText(txt);
	}
	
}



















