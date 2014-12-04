import java.io.IOException;

/**
 * Peameetodit sisaldav class.
 *
 * @version 0.1
 * @author Olari Pipenberg
 * @since 1.7
 */
/*
 * Testimiseks: http://kanal2.ee/pluss/closed?id=23523
 * http://www.liveleak.com/view?i=671_1417498779
 * http://arhiiv.err.ee/vaata/etv-kuld-pop-3
 * http://kanal2.ee/pluss/closed?id=23662
 * http://arhiiv.err.ee/vaata/see-vana-hea-dixi/latest
 * http://etv.err.ee/v/paevakajasaated/aktuaalne_kaamera/saated2100/e9f43050-0b58-462b-a78a-677a85a5c403
 */
public class Main {

	public static void main(String[] args) throws IOException {
		String kasutaja = TextIO.getlnString();
		Url.uuriLinki(kasutaja);

	}

}
