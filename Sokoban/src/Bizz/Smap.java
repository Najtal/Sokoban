package Bizz;

import java.awt.Image;
import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;

import Util.Util;

public class Smap implements Serializable {

	/*	Tableau de composition de la carte
	 * [ [h0l0] [h0l1] [h0l2]
	 *   [h1l0] [h1l1] [h1l2]
	 *   [h2l0] [h2l1] [h2l2]]
	 */
	private int[][] composition;
	private int hauteur, largeur;
	private String nomMap;
	private int lvlDifficulte;
	private String auteur;
	private GregorianCalendar dateDeCreation;
	private int identifiant; // numero de la map [0,77]
	private ImageIcon image;

	private static int incrementeurId = 1;
	
	/*
	 * 0 : vide
	 * 1 : mur (entour le jeu)
	 * 2 : chemin (pratiquable par slayer)
	 * 3 : trou (la ou il faut mettre la boite)
	 * 4 : boite (position d'une boite (donc un chemin aussi)
	 * 5 : position de début du personnage
	 */

	public Smap(int[][] comp, String nomMap, int lvl, String auteur, Image icon1) throws Exception {

		Util.checkObject(comp);
		Util.checkString(nomMap);
		Util.checkPositive(lvl);
		if (lvl > 5 || lvl < 1) 
			throw new Exception();
		Util.checkString(auteur);
		Util.checkObject(icon1);
				
		this.composition = comp;
		this.hauteur = comp.length;
		this.largeur = comp[0].length;
		
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				if (composition[i][j] < 0 || composition[i][j] > 5)
					throw new Exception();
			}
		}
		
		this.nomMap = nomMap;
		this.lvlDifficulte = lvl;
		this.auteur = auteur;
		this.image = new ImageIcon(icon1);
		
		this.dateDeCreation = new GregorianCalendar();	
		this.identifiant = incrementeurId;
		incrementeurId++;
	}

	/**
	 * Récupère le code de l'élément en une position
	 * @param hauteur hauteur dans le tableau
	 * @param largeur largeur dans le tableau
	 * @return le code valeur de l'élément a la position donnée
	 */
	public int getElem(int hauteur, int largeur) {
		if (hauteur < 0 
				|| hauteur > this.hauteur 
				|| largeur < 0
				|| largeur > this.largeur)
			return -1;
		
		return composition[hauteur][largeur];
	}
	
	/**
	 * récupère tout le tableau de composition de la map
	 * @return composition
	 */
	public int[][] getComposition() {
		return composition;
	}

	/**
	 * retourne la hauteur du jeu
	 * @return hauteur
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * retourne la largeur du jeu
	 * @return largeur
	 */
	public int getLargeur() {
		return largeur;
	}

	/**
	 * retourne le nom de la map
	 * @return nom map
	 */
	public String getNomMap() {
		return nomMap;
	}

	/**
	 * retourne le niveau de difficulté (1 - 5)
	 * @return niveau de difficulté
	 */
	public int getLvlDifficulte() {
		return lvlDifficulte;
	}

	/**
	 * retourne le nom de l'auteur de la map
	 * @return auteur de la map
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * retourne la date de création de la map
	 * @return date de création de la map
	 */
	public GregorianCalendar getDateDeCreation() {
		return dateDeCreation;
	}
	
	/**
	 * retourne l'identifiant unique de la map
	 * @return identifiant de la map
	 */
	public int getIdentifiant() {
		return identifiant;
	}
	
	/**
	 * renvoie l'image
	 * @return l'image de la map
	 */
	public ImageIcon getImage() {
		return image;
	}
	
	@Override
	/**
	 * Affiche toute les informations de la map
	 */
	public String toString() {

		String toret = "\n";
		
		toret += "nom : \t\t" + this.getNomMap() + "\n";
		toret += "auteur : \t" + this.getAuteur() + "\n";
		toret += "niveau : \t" + this.getLvlDifficulte() + "\n";
		toret += "id : \t\t" + this.getIdentifiant() + "\n\n";
		
		for (int i = 0; i < this.getLargeur(); i++) {
			for (int j = 0; j < this.getHauteur(); j++) {
				toret += this.getElem(j, i) + " ";
			}
			toret += "\n";
		}
		return toret;
	}
	
}
