import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Generic class. P6hiline class tuvastamata videote leidmiseks (ei ole err,
 * kanal2, tv3).
 *
 * @version 0.2
 * @author Olari Pipenberg
 * @since 1.7
 */

public class Generic {

	/**
	 * Meetod tekstMassiivks. Loeb faili ja lisab sisalduvad m2rgid massiivi.
	 * 
	 * @return tagastab m2rkide massiivi
	 * @throws IOException
	 */

	public static char[] tekstMassiiviks(String link) throws IOException {

		URL oracle = new URL(link);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				oracle.openStream()));
		int c = 0;
		char m[] = new char[500000];
		int i = 0;
		while ((c = in.read()) != -1) { // lisab massiivi t2ht t2he haaval
			char character = (char) c;
			m[i] = character;
			i++;
		}
		return m;
	}

	/**
	 * Meetod punktvahemikus.
	 * 
	 * @param v6tab
	 *            parameetriks sisestatud faili m2rkide massiivi
	 * 
	 * @return tagastab leitud .mp4 asukoha otsitava ID vahemikus
	 */
	public static int punktVahemikus(char m[]) {
		int asukoht = 0;
		for (int i = 0; i < m.length; i++) {
			if (m[i] == '.' && m[i + 1] == 'm' && m[i + 2] == 'p'
					&& m[i + 3] == '4') { // Otsib .mp4
				asukoht = i;
				break;
			}
		}
		return asukoht;
	}

	/**
	 * Meetod minPunkt. Leiab urli alguse.
	 * 
	 * @param v6tab
	 *            parameetriks sisestatud faili m2rkide massivi
	 * @return tagastab urli alguse m2rgi indeksi
	 */
	public static int minPunkt(char m[]) {
		int punkt = punktVahemikus(m);
		int min = 0;
		for (int i = punkt; i > 0; i--) {
			if (m[i] == '"' || m[i] == '\'') { // Sorteerib jutum2rkide vahelt
				min = i + 1;
				break;
			}
		}
		return min;
	}

	/**
	 * Meetod maxPunkt. Leiab urli l6pu.
	 * 
	 * @param m
	 *            v6tab parameetriks sisestatud faili m2rkide massiivi
	 * @return tagastab urli alguse m2rgi indeksi
	 */
	public static int maxPunkt(char m[]) {
		int punkt = punktVahemikus(m);
		int max = 0;
		for (int i = punkt; i < m.length; i++) {
			if (m[i] == '"' || m[i] == '\'') {
				max = i - 1;
				break;
			}
		}
		return max;
	}

	/**
	 * Meetod leiaVoog.
	 * 
	 * @return tagastab video lingi mida saab avada m2ngijaga
	 * @throws IOException
	 */
	public static String leiaVoog(char m[]) {
		String voog = "";
		int min = minPunkt(m);
		int max = maxPunkt(m);
		for (int i = min; i < max + 1; i++) {
			voog = voog + m[i];
		}
		return voog;
	}

	/**
	 * Meetod nimega eiT22ta. Kontrollib voo linki.
	 * 
	 * @param voog
	 *            parameetriks on sisestatud voo aadress
	 * @return tagastab 0, kui l2bib kontrolli
	 */
	public static int eiT22ta(String voog) {
		int kontroll = 0;
		for (int i = 0; i < voog.length(); i++) {
			if (voog.charAt(i) == '!' || voog.charAt(i) == '<'
					|| voog.charAt(i) == '>') {
				kontroll = 1;
			}
		}
		return kontroll;
	}

	/**
	 * Meetod k2ivita. K2ivitab m2ngija koos vajaliku aadressiga
	 * 
	 * @param voog
	 *            v6tab parameetriks sisestatud video aadressi
	 */

	public static void k2ivita(String voog) {

		try {
			if (eiT22ta(voog) == 0) {
				System.out.println("Voo url on " + voog);
				Runtime rt = Runtime.getRuntime();
				Process proc = rt.exec("vlc " + voog); // K2ivitab VLC
				proc.waitFor();
			}
		} catch (Throwable t) {
			t.printStackTrace();

		}

	}

}
