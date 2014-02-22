package Ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Param {

	public static final String NOM_APPLICATION = "CrAzy RabBits";

	public static final Color COLOR_BACKGROUND = new Color(112, 146, 190);
	public static final Color COLOR_BACKGROUND_HOVER = new Color(193, 210, 236);
	
	public static final Color COLOR_BACKGROUND_LOADING = new Color(255, 244, 222);
	public static final Color COLOR_BACKGROUND_GAME_MENU = new Color(234, 234, 234);
	public static final Color COLOR_BACKGROUND_GAME = new Color(246, 246, 246);
	
	public static final Color COLOR_INFO_MAP_TITRE = new Color(255, 200, 14);
	public static final Color COLOR_INFO_MAP = new Color(180, 230, 30);


	static final Font FONT_TITRE = new Font("Matisse ITC", Font.LAYOUT_LEFT_TO_RIGHT, 28);
	static final Font FONT_LEVEL_SELECTOR = new Font("Matisse ITC", Font.BOLD, 18);
	static final Font FONT_INFO_MAP_TITRE = new Font("Calibri Light", Font.LAYOUT_LEFT_TO_RIGHT, 16);
	static final Font FONT_INFO_MAP = new Font("Calibri Light", Font.LAYOUT_LEFT_TO_RIGHT, 14);
	static final Font FONT_MAP_INFO = new Font("Matisse ITC", Font.BOLD, 16);


	
	
	// IMAGES
	public static ImageIcon option = new ImageIcon("images/prog/option2.png");
	public static Image logo = (new ImageIcon("images/prog/logo.png")).getImage();
	public static Image unknow = new ImageIcon("images/prog/unknow2.png").getImage().getScaledInstance(80, 80, 1);
	public static ImageIcon retour = new ImageIcon("images/prog/back.png");
	public static ImageIcon loading = new ImageIcon("images/prog/loading.jpg");
	public static ImageIcon charniere = new ImageIcon("images/prog/charniere.png");

	public static Image arrow_top = new ImageIcon("images/prog/arrow_top.png").getImage().getScaledInstance(70, 70, 1);
	public static Image arrow_left = new ImageIcon("images/prog/arrow_left.png").getImage().getScaledInstance(70, 70, 1);
	public static Image arrow_botton = new ImageIcon("images/prog/arrow_bottom.png").getImage().getScaledInstance(70, 70, 1);
	public static Image arrow_right = new ImageIcon("images/prog/arrow_right.png").getImage().getScaledInstance(70, 70, 1);

	
	// PARTIE
	public static ImageIcon vide = new ImageIcon("images/partie/grass.jpg");
	public static ImageIcon boite = new ImageIcon("images/partie/box.jpg");
	public static ImageIcon oeuf = new ImageIcon("images/partie/oeuf.png");
	public static ImageIcon star1 = new ImageIcon("images/partie/star1.png");
	public static ImageIcon star2 = new ImageIcon("images/partie/star2.png");
	public static ImageIcon chemin = new ImageIcon("images/partie/path.jpg");
	
	public static ImageIcon max_0 = new ImageIcon("images/partie/r_0.png");
	public static ImageIcon max_1 = new ImageIcon("images/partie/r_45.png");
	public static ImageIcon max_2 = new ImageIcon("images/partie/r_90.png");
	public static ImageIcon max_3 = new ImageIcon("images/partie/r_135.png");
	public static ImageIcon max_4 = new ImageIcon("images/partie/r_180.png");
	public static ImageIcon max_5 = new ImageIcon("images/partie/r_225.png");
	public static ImageIcon max_6 = new ImageIcon("images/partie/r_270.png");
	public static ImageIcon max_7 = new ImageIcon("images/partie/r_315.png");







	
}
