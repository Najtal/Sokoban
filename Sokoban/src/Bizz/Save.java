package Bizz;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;


public class Save implements Serializable {

	private int id;
	private int nbDeplacements;
	private String nomJoueur;
	private GregorianCalendar dateDeSauvegarde;
	private GregorianCalendar dateDebutJeu;
	private long secondesDeJeu;

	/**
	 * Constructeur
	 * @param map Niveau joué
	 * @param nomJoueur Nom du joueur qui a gagné la partie
	 * @param dateDeDebutDeJeu Date de début du jeu
	 * @param nbDeplacements Nombre de déplacements effectués
	 */
	public Save(Smap map, String nomJoueur, GregorianCalendar dateDeDebutDeJeu, int nbDeplacements) {
		
		this.id = map.getIdentifiant();
		this.nbDeplacements = nbDeplacements;
		this.nomJoueur = nomJoueur;
		this.dateDeSauvegarde = new GregorianCalendar();
		this.dateDebutJeu = dateDeDebutDeJeu;
		
		secondesDeJeu = getDateDiff(dateDeSauvegarde.getTime(), dateDeDebutDeJeu.getTime(),TimeUnit.SECONDS);
	}
	
	
	/**
	 * Get a diff between two dates
	 * @param date1 the oldest date
	 * @param date2 the newest date
	 * @param timeUnit the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	private long getDateDiff(Date date2, Date date1, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}

	public int getId() {
		return id;
	}

	public int getNbDeplacements() {
		return nbDeplacements;
	}

	public GregorianCalendar getDateDebutJeu() {
		return dateDebutJeu;
	}

	public long getSecondesDeJeu() {
		return secondesDeJeu;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public GregorianCalendar getDateDeSauvegarde() {
		return dateDeSauvegarde;
	}
	
}
