import java.io.IOException;

/**
 * ERR videote class.
 *
 * @version 0.1
 * @author Olari Pipenberg
 * @since 1.7
 */

public class Err {
	private String link;

	/**
	 * Konstruktor klassile Err.
	 * 
	 * @param sisestus
	 *            v6tab parameetriks sisestatud urli
	 */
	public Err(String sisestus) { // Konstruktor
		link = sisestus;
	}

	public String getLink() {
		return link;
	}

	/**
	 * Meetod laeAlla. Teeb uue Allalaadimise objekti ja v6imaldab
	 * allalaadimise.
	 * 
	 */
	public void laeAlla() {
		Allalaadimine wgetObject = new Allalaadimine(link);
		wgetObject.wget();
	}

	/**
	 * Meetod fileIdMin. Otsib allalaetud failis voo ID esimese m2rgi asukoha.
	 * 
	 * @return tagastab voo ID alguse
	 * @throws IOException
	 */

	public static int fileIdMin() throws IOException {
		char tekst[] = Generic.tekstMassiiviks();
		int asukoht = 0;
		for (int i = 0; i < tekst.length; i++) {
			if (tekst[i] == 'f' && tekst[i + 1] == 'i' && tekst[i + 2] == 'l'
					&& tekst[i + 3] == 'e' && tekst[i + 4] == '=') { // Otsing
				asukoht = i + 5;
				break;
			}
		}
		return asukoht;
	}

	/**
	 * Meetod leiaVooId. Otsib voo ID.
	 * 
	 * @return tagastab Voo ID
	 * @throws IOException
	 */

	public static String leiaVooId() throws IOException {
		String id = "mp4:";
		char tekst[] = Generic.tekstMassiiviks();
		int algus = fileIdMin();
		for (int i = algus; i < tekst.length; i++) {
			if (tekst[i] == '&')
				break;
			id = id + tekst[i];
		}
		return id;
	}

	/**
	 * Meetod leiavoog. Seob kokku voo ID vastava serveriga.
	 * 
	 * @return tagastab video lingi mida saab avada m2ngijaga
	 * @throws IOException
	 */
	public String leiaVoog() throws IOException {
		String link = getLink();
		String saade = "rtmp://media.err.ee:80/etvsaated/";
		String arhiiv = "rtmp://media.err.ee:80/arhiiv/";
		String tulemus = saade;
		for (int i = 0; i < 20; i++) { // Otsib vastava voo urliga server
			if (link.charAt(i) == 'a' && link.charAt(i + 1) == 'r'
					&& link.charAt(i + 2) == 'h' && link.charAt(i + 3) == 'i'
					&& link.charAt(i + 4) == 'i' && link.charAt(i + 5) == 'v') {
				tulemus = arhiiv;
				break;
			}
		}
		tulemus = tulemus + leiaVooId();
		return tulemus;
	}

}
