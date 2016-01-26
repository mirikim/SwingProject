package Control;

import javax.swing.JOptionPane;

import View.LeftPan;

public class LeftCenControl {
	LeftPan lp = new LeftPan();
	String seatLocation = ""; // ÁÂ¼®À§Ä¡
	public static String nt = ""; // ÀÔ½Ç½Ã°£
	String et = ""; // Åð½Ç
	int index = 0;
	int change =0;
	// public void seatClick() {
	// System.out.println("È£ÃâµÌ³Ä Control");
	// lp.LeftPanClear();
	// }

	public void setshowMessage(String seatlocation, String nt, String et, int index) {
		this.seatLocation = seatlocation;
		this.nt = nt;
		this.et = et;
		this.index = index;
		lp.setIndex(index);
		// /lp.setTexstArea();
		//¾Æ¾¾¹ß
	}

}
