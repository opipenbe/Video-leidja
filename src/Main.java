import java.io.IOException;

/**
 * Peameetodit sisaldav class.
 *
 * @version 0.3
 * @author Olari Pipenberg
 * @since 1.7
 */

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Sisesta link");
		String kasutaja = TextIO.getlnString();
		Url.uuriLinki(kasutaja);

	}

}
