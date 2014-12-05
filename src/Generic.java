import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Generic class. P6hiline class tuvastamata videote leidmiseks (ei ole err,
 * kanal2, tv3).
 *
 * @version 0.1
 * @author Olari Pipenberg
 * @since 1.7
 */

public class Generic {
	private String link;

	/**
	 * Konstruktor Generic classile.
	 * 
	 * @param sisestus
	 *            v6tab parameetriks sisestatud urli
	 */
	public Generic(String sisestus) {
		link = sisestus;
	}

	/**
	 * Meetod laeAlla. Teeb uue Allalaadimise objekti ja v6imaldab
	 * allalaadimise.
	 */
	public void laeAlla() {
		Allalaadimine wgetObject = new Allalaadimine(link);
		wgetObject.wget();
	}

	/**
	 * Meetod tekstMassiivks. Loeb faili ja lisab sisalduvad m2rgid massiivi.
	 * 
	 * @return tagastab m2rkide massiivi
	 * @throws IOException
	 */
	public static char[] tekstMassiiviks() throws IOException {
		File file = new File("fail");
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		int c = 0;
		char m[] = new char[500000];
		int i = 0;
		while ((c = buffer.read()) != -1) { // lisab massiivi t2ht t2he haaval
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
		System.out.println("URLi algus " + min);
		return min;
	}

	/**
	 * Meetod maxPunkt. Leiab urli alguse.
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
		System.out.println("URLi l6pp " + max);
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
		int max = maxPunkt(m);
		int min = minPunkt(m);
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
			} else {
				System.out.println("Programm ei toeta seda lehte!");
			}
		} catch (Throwable t) {
			t.printStackTrace();

		}

	}

}
