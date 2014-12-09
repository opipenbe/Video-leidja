import java.io.IOException;

/**
 * Kanal2 videote klass.
 *
 * @version 0.1
 * @author Olari Pipenberg
 * @since 1.7
 */
public class Kanal2 {

	private String link;

	/**
	 * Konstruktor classile Kanal2.
	 * 
	 * @param sisestus
	 *            v6tab parameetriks sisestatud urli
	 */
	public Kanal2(String sisestus) {
		link = sisestus;
	}

	public String getLink() {
		String leheapi = "http://kanal2.ee/video/playerPlaylistApi/?id=";
		return leheapi + leiaId(link);
	}

	/**
	 * Meetod leiaId. Otsib sisestatud urlist video api id.
	 * 
	 * @param leht
	 * @return tagastab video API lehe id
	 */
	public static String leiaId(String leht) {
		String id = "";
		for (int i = leht.length() - 1; i > 0; i--) {
			char numbrid = leht.charAt(i);
			if (leht.charAt(i) == '=')// peatab otsingu kui satub kokku m2rgiga
				break;
			id = numbrid + id;
		}
		return id;
	}

	/**
	 * Meetod vooIdMin. Otsib voo ID esimese m2rgi.
	 * 
	 * @return tagastab esimese voo ID m2rgi asukoha
	 * @throws IOException
	 */
	public int vooIdMin() throws IOException {
		char tekst[] = Generic.tekstMassiiviks(getLink());
		int asukoht = 0;
		for (int i = 0; i < tekst.length; i++) {
			if (tekst[i] == 'k' && tekst[i + 1] == '2' && tekst[i + 2] == 'l'
					&& tekst[i + 3] == 'q') { // Leiab k2l asukoha
				asukoht = i;
				break;
			}
		}
		return asukoht;
	}

	/**
	 * Meetod leiaVooId. Otsib voo ID.
	 * 
	 * @return tagastab voo ID
	 * @throws IOException
	 */
	public String leiaVooId() throws IOException {
		String id = "";
		char tekst[] = Generic.tekstMassiiviks(getLink());
		int algus = vooIdMin();
		for (int i = algus; i < tekst.length; i++) {
			if (tekst[i] == '"')
				break;
			id = id + tekst[i];
		}
		System.out.println("Video ID on: " + id);
		return id;
	}

	/**
	 * Meetod leiaVoog. Seob kokku voo ID vastava serveriga.
	 * 
	 * @return tagastab voo aadressi, mida saab k2ivitada pleieriga
	 * @throws IOException
	 */
	public String leiaVoog() throws IOException {
		String server = "rtmp://kanal2-egress.cdn.mind.ee/kanal2vod//";
		String voog = server + leiaVooId();
		return voog;
	}
}
