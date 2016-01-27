package Control;

import javax.swing.JOptionPane;

import View.CenPan;
import View.LeftPan;

public class LeftCenControl {
	LeftPan lp = new LeftPan();
	CenPan cp = new CenPan();
	String seatLocation = ""; // ÁÂ¼®À§Ä¡
	String nt = ""; // ÀÔ½Ç
	String et = ""; // Åð½Ç
	int ExtensionNum = 0;

	public void setCheck(boolean ok) {

		lp.setCheck(ok);

	}

	public void LeftToCheck(boolean ok) {
		cp.clickCheck(ok);
	}

	public void setTime(String nt, String et, String seatLocation, int ExtensionNum) {
		this.nt = nt;
		this.et = et;
		this.seatLocation = seatLocation;
		this.ExtensionNum = 0;

		lp.getSeatInfo(nt, et, seatLocation, ExtensionNum);
	}

}
