import java.io.IOException;

/**
 * Lihtsamate RTMP voogude leidmise class.
 *
 * @version 0.1
 * @author Olari Pipenberg
 * @since 1.7
 */

public class Rtmp {

	private String link;

	/**
	 * Konstruktor klassile Rtmp.
	 * 
	 * @param sisestus
	 *            v6tab parameetriks sisestatud urli
	 */
	public Rtmp(String sisestus) { // Konstruktor
		link = sisestus;
	}

	public String getLink() {
		return link;
	}

	/**
	 * Meetod fileIdMin. Leiab voo aadressi esimese m2rgi.
	 * 
	 * @return
	 * @throws IOException
	 */
	public int fileIdMin() throws IOException {
		char tekst[] = Generic.tekstMassiiviks(getLink());
		int asukoht = 0;
		for (int i = 0; i < tekst.length; i++) {
			if (tekst[i] == 'r' && tekst[i + 1] == 't' && tekst[i + 2] == 'm'
					&& tekst[i + 3] == 'p') { // Otsing
				asukoht = i;
				break;
			}
		}
		return asukoht;
	}

	/**
	 * Meetod leiaVoog.
	 * 
	 * @return tagastab voo aadressi
	 * @throws IOException
	 */
	public String leiaVoog() throws IOException {
		char tekst[] = Generic.tekstMassiiviks(getLink());
		String tulemus = "";
		int min = fileIdMin();
		for (int i = min; i < tekst.length; i++) {
			if (tekst[i] == '"' || tekst[i] == '\'')
				break;
			tulemus = tulemus + tekst[i];
		}
		return tulemus;
	}

}
