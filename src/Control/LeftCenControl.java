package Control;

import javax.swing.JOptionPane;

import View.LeftPan;

public class LeftCenControl {
	LeftPan lp = new LeftPan();
	static String seatLocation = ""; // �¼���ġ
	static String nt = ""; // �Խǽð�
	static String et = ""; // ���

	// public void seatClick() {
	// System.out.println("ȣ��̳� Control");
	// lp.LeftPanClear();
	// }

	public void setshowMessage(String seatlocation, String nt, String et) {
		this.seatLocation = seatlocation;
		this.nt = nt;
		this.et = et;
		// /lp.setTexstArea();
	}

}
