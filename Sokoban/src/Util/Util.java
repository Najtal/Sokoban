package Util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// cc cours paoo

/**
 * The Class Util.
 */
public abstract class Util {

	/** The Constant DECIMAL. */
	private static final double DECIMAL = 0.00001;


	/**
	 * Check negative or zero.
	 * 
	 * @param d
	 *            double � v�rifier @ Lance une exception lorsque le param�tre
	 *            n'est pas un double n�gatif ou �gal � 0
	 */
	public static void checkNegativeOrZero(final double d) {
		if (d > 0.0) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Check object.
	 * 
	 * @param o
	 *            objet à vérifier @ Lance une exception lorsque le param�tre
	 *            est null
	 */
	public static void checkObject(final Object o) {

		if (o == null) {
			throw new IllegalArgumentException("Objet null");
		}
	}

	/**
	 * Check si une date est dans le passe
	 * 
	 * @param date
	 *            la date @ si l'argument est une date futur ou si l'argument
	 *            n'est pas un object valide
	 */
	public static void checkPastDate(final Calendar date) {
		final Calendar maintenant = GregorianCalendar.getInstance();
		checkObject(date);

		if (maintenant.before(date)) {
			throw new IllegalArgumentException("La date  " + date
					+ " est dans le futur");
		}

	}

	/**
	 * Check positive.
	 * 
	 * @param d
	 *            double � v�rifier @ Lance une exception lorsque le param�tre
	 *            n'est pas un double positif
	 */
	public static void checkPositive(final double d) {
		if (d < DECIMAL) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Check positive.
	 * 
	 * @param i
	 *            entier � v�rifier @ Lance une exception lorsque le param�tre
	 *            n'est pas un entier positif
	 */
	public static void checkPositive(final int i) {
		if (i <= 0) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Check positive or zero.
	 * 
	 * @param d
	 *            double � v�rifier @ Lance une exception lorsque le param�tre
	 *            n'est pas un double positif ou �gal � 0
	 */
	public static void checkPositiveOrZero(final double d) {
		if (d < 0.0) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Check positive pas zero.
	 * 
	 * @param i
	 *            the i @ the argument invalide exception
	 */
	public static void checkPositivePasZero(final int i) {
		if (i < 0) {
			throw new IllegalArgumentException(i + " est négative");
		}
	}

	/**
	 * Check string.
	 * 
	 * @param s
	 *            string a verifier @ Lance une exception lorsque le parametre
	 *            est ""
	 */
	public static void checkString(final String s) {
		checkObject(s);
		if (s.trim().equals("")) {
			throw new IllegalArgumentException("Aucune valeur trouv�e");
		}
	}

	/**
	 * Transforme une date en GregorianCalendar
	 * 
	 * @param date
	 *            la date a transformer
	 * @return le gregorianCalendar representant la date
	 */
	public static GregorianCalendar dateToCalendar(final Date date) {
		final GregorianCalendar cal = (GregorianCalendar) GregorianCalendar
				.getInstance();
		cal.setTime(date);
		return cal;
	}

}