/**
 * Allalaadimise class. Laeb alla videot sisaldava lehe.
 *
 * @version 0.1
 * @author Olari Pipenberg
 * @since 1.7
 */

public class Allalaadimine { // TODO v6iks olla java lahendus

	private String link;

	/**
	 * Konstruktor klassil Allalaadimine
	 * 
	 * @param userlink
	 *            v6tab parameetriks sisestatud urli
	 */
	public Allalaadimine(String userlink) {
		link = "wget" + " " + userlink + " -O fail";
	}

	public String getLink() {
		return link;
	}

	/**
	 * Meetod wget. Kutsub v2lja UNIX k2surea Runtime ja Process abil ja
	 * sisestab kasutaja poolt antud k2su
	 */
	public void wget() {
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(getLink());
			proc.waitFor();
			System.out.println("URLi L2htekood on allalaetud");
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
