import java.io.IOException;

/**
 * Peameetodit sisaldav class.
 *
 * @version 0.1
 * @author Olari Pipenberg
 * @since 1.7
 */
/*
 * Proovimiseks: http://kanal2.ee/pluss/closed?id=23523
 * http://www.liveleak.com/view?i=671_1417498779
 * http://arhiiv.err.ee/vaata/etv-kuld-pop-3
 * http://kanal2.ee/pluss/closed?id=23662
 * http://arhiiv.err.ee/vaata/see-vana-hea-dixi/latest
 */
public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Sisesta link");
		String kasutaja = TextIO.getlnString();
		Url.uuriLinki(kasutaja);
		

	}

}
