package Control;

import javax.swing.JOptionPane;

import View.LeftPan;

public class LeftCenControl {
	LeftPan lp = new LeftPan();
	String seatLocation = ""; // �¼���ġ
	public static String nt = ""; // �Խǽð�
	String et = ""; // ���
	int index = 0;
	int change =0;
	int extentionNum=0;
	// public void seatClick() {
	// System.out.println("ȣ��̳� Control");
	// lp.LeftPanClear();
	// }

	public void setshowMessage(String seatlocation, String nt, String et, int index) {
		this.seatLocation = seatlocation;
		this.nt = nt;
		this.et = et;
		this.index = index;
		lp.setIndex(index);
		
	}


}
