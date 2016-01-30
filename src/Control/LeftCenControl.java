package Control;

import java.util.HashMap;
import java.util.Vector;

import View.CenPan;
import View.LeftPan;
import View.ManagerView;

public class LeftCenControl {
	LeftPan lp = new LeftPan();
	CenPan cp = new CenPan();
	ManagerView mv = new ManagerView();
	String seatLocation = ""; // 좌석위치
	String nt = ""; // 입실
	String et = ""; // 퇴실
	String readingRoom = "";// 열람실
	int ExtensionNum = 0;
	Vector[] memList;// 탑 메뉴의 회원 리스트를 보여주기 위한 것
	Vector[] memUsedList;// 좌석 이용자 리스트
	HashMap usedSeat;

	public void setMemberList(Vector[] memInfo, int index, HashMap usedSeat) {
		this.memList = memInfo;
		this.usedSeat = usedSeat;
		mv.memListup(memInfo, index, usedSeat);
	}
	public void setMemberList(HashMap usedSeat) {
		this.usedSeat = usedSeat;
		
	}

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

	public void setTime(String nt, String et, String readingRoom, String seatLocation, int ExtensionNum) {
		/*
		 * 유저가 좌석을 선택하면 좌석클래스에서는 현재(시간,종료시간,좌석위치,연장횟수)값을 회원클래스로 넘겨준다
		 */
		this.nt = nt;
		this.et = et;
		this.seatLocation = seatLocation;
		this.ExtensionNum = 0;
		this.readingRoom = readingRoom;

		lp.getSeatInfo(nt, et, readingRoom, seatLocation, ExtensionNum);
		/*
		 * 회원클래스의 메서드로 바로 전달
		 */
	}

	public void setMoveCheck(String extensionHour, boolean moveCheck, int extensionNum) {
		cp.moveCheck(extensionHour, moveCheck, extensionNum);
	}

}
