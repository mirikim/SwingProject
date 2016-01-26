package Control;

import javax.swing.JOptionPane;

import View.LeftPan;

public class LeftCenControl {
	LeftPan lp = new LeftPan();
	String seatLocation = ""; // 좌석위치
	String nt = ""; // 입실시간
	String et = ""; // 퇴실

//	public void seatClick() {
//		System.out.println("호출됫냐 Control");
//		 lp.LeftPanClear();
//	}

	public void getshowMessage(String seatlocation, String nt, String et) {
		this.seatLocation = seatlocation;
		this.nt = nt;
		this.et = et;
		JOptionPane.showMessageDialog(null, "좌석 : " + seatLocation + "\n입실 시간 : " + nt + "\n예상 퇴실 시간 : " + et);
	}

}
