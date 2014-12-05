import java.io.IOException;

/**
 * Url klass. Uurib kasutaja sisestatud linki.
 *
 * @version 0.1
 * @author Olari Pipenberg
 * @since 1.7
 */
public class Url {
	/**
	 * Meetod uuriLinki. Otsib 6iget video klassi.
	 * 
	 * @param url
	 *            kasutab parameetrina sisestatud urli
	 * @throws IOException
	 */
	public static void uuriLinki(String url) throws IOException {
		int tulemus = 0; // Otsingu tulemus
		for (int i = 0; i < url.length(); i++) {
			if (url.charAt(i) == 'e' && url.charAt(i + 1) == 'r'
					&& url.charAt(i + 2) == 'r') { // kui on err
				tulemus = 1;
				err(url);
				break;
			}
			if (url.charAt(i) == 'a' && url.charAt(i + 1) == 'l'
					&& url.charAt(i + 2) == '2') { // kui on kanal2
				tulemus = 1;
				kanal2(url);
				break;
			}
			if (url.charAt(i) == 't' && url.charAt(i + 1) == 'v'
					&& url.charAt(i + 2) == '3') { // kui on tv3
				tulemus = 1;
				tv3Play(url);
				break;
			}
		}
		if (tulemus == 0) // kui ei leia, siis kasutab yldist v6imalust
			yldine(url);
	}

	public static void kanal2(String sisestus) throws IOException {
		Kanal2 kanal2Obj = new Kanal2(sisestus);
		kanal2Obj.laeAlla();
		Generic.k2ivita(Kanal2.leiaVoog());
	}

	public static void err(String sisestus) throws IOException {
		Err errObject = new Err(sisestus);
		errObject.laeAlla();
		Generic.k2ivita(errObject.leiaVoog());
	}

	public static void tv3Play(String sisestus) {

	}

	public static void yldine(String sisestus) throws IOException {
		Generic genericObj = new Generic(sisestus);
		genericObj.laeAlla();
		Generic.k2ivita(Generic.leiaVoog(Generic.tekstMassiiviks()));
	}
}
