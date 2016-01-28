package Control;

import java.util.HashMap;

import View.CenPan;
import View.LeftPan;

public class LeftCenControl {
	LeftPan lp = new LeftPan();
	CenPan cp = new CenPan();
	String seatLocation = ""; // 좌석위치
	String nt = ""; // 입실
	String et = ""; // 퇴실
	int ExtensionNum = 0;

	public void setCheck(boolean SeatLock) {
		/*
		 * 좌석 클래스에서 회원클래스로 좌석 중복선택 방지를 위해 값을 넘겨준다.
		 */
		lp.setCheck(SeatLock);

	}

	public void setCheck(boolean LoginCheck, boolean seatCheck) {
		// 이거도 헷갈리니까 오버로딩
		/*
		 * 회원클래스에서 좌석클래스로 로그인 한후 로그인 체크값 좌석클래스로 넘겨준다.
		 */
		cp.clickCheck(LoginCheck, seatCheck);
	}

	public void setTime(String nt, String et, String seatLocation, int ExtensionNum) {
		/*
		 * 유저가 좌석을 선택하면 좌석클래스에서는 현재(시간,종료시간,좌석위치,연장횟수)값을 회원클래스로 넘겨준다
		 */
		this.nt = nt;
		this.et = et;
		this.seatLocation = seatLocation;
		this.ExtensionNum = 0;

		lp.getSeatInfo(nt, et, seatLocation, ExtensionNum);
		/*
		 * 회원클래스의 메서드로 바로 전달
		 */
	}

}
