package Control;

import javax.swing.JOptionPane;

import View.LeftPan;

public class LeftCenControl {
	LeftPan lp = new LeftPan();
	String seatLocation = ""; // �¼���ġ
	String nt = ""; // �Խǽð�
	String et = ""; // ���

//	public void seatClick() {
//		System.out.println("ȣ��̳� Control");
//		 lp.LeftPanClear();
//	}

	public void getshowMessage(String seatlocation, String nt, String et) {
		this.seatLocation = seatlocation;
		this.nt = nt;
		this.et = et;
		JOptionPane.showMessageDialog(null, "�¼� : " + seatLocation + "\n�Խ� �ð� : " + nt + "\n���� ��� �ð� : " + et);
	}

}
