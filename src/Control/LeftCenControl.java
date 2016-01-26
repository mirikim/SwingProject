package Control;

import javax.swing.JOptionPane;

import View.LeftPan;

public class LeftCenControl {
	LeftPan lp = new LeftPan();
	static String seatLocation = ""; // ÁÂ¼®À§Ä¡
	static String nt = ""; // ÀÔ½Ç½Ã°£
	static String et = ""; // Åð½Ç

	// public void seatClick() {
	// System.out.println("È£ÃâµÌ³Ä Control");
	// lp.LeftPanClear();
	// }

	public void setshowMessage(String seatlocation, String nt, String et) {
		this.seatLocation = seatlocation;
		this.nt = nt;
		this.et = et;
		// /lp.setTexstArea();
	}

}
