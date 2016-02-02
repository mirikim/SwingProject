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
	String seatLocation = ""; // �¼���ġ
	String nt = ""; // �Խ�
	String et = ""; // ���
	String readingRoom = "";// ������
	int ExtensionNum = 0;
	Vector[] memList;// ž �޴��� ȸ�� ����Ʈ�� �����ֱ� ���� ��
	Vector[] memUsedList;// �¼� �̿��� ����Ʈ
	HashMap usedSeat;
	public static int SeatCount1 = 72;
	public static int SeatCount2 = 72;

	public void setMemberList(Vector[] memInfo, int index, HashMap usedSeat) {
		this.memList = memInfo;
		this.usedSeat = usedSeat;
		mv.memListup(memInfo, index, usedSeat);
	}

	public void setMemberList(HashMap usedSeat) {
		this.usedSeat = usedSeat;

	}

	public void delMemberList(String Id) {
		System.out.println(Id + "���̵𰡹���");
		lp.delUser(Id);
	}

	public void setCheck(boolean SeatLock) {
		/*
		 * �¼� Ŭ�������� ȸ��Ŭ������ �¼� �ߺ����� ������ ���� ���� �Ѱ��ش�.
		 */
		lp.setCheck(SeatLock);

	}

	public void setCheck(boolean LoginCheck, boolean seatCheck) {
		// �̰ŵ� �򰥸��ϱ� �����ε�
		/*
		 * ȸ��Ŭ�������� �¼�Ŭ������ �α��� ���� �α��� üũ�� �¼�Ŭ������ �Ѱ��ش�.
		 */
		cp.clickCheck(LoginCheck, seatCheck);
	}

	public void setTime(String nt, String et, String readingRoom, String seatLocation, int ExtensionNum) {
		/*
		 * ������ �¼��� �����ϸ� �¼�Ŭ���������� ����(�ð�,����ð�,�¼���ġ,����Ƚ��)���� ȸ��Ŭ������ �Ѱ��ش�
		 */
		this.nt = nt;
		this.et = et;
		this.seatLocation = seatLocation;
		this.ExtensionNum = 0;
		this.readingRoom = readingRoom;

		lp.getSeatInfo(nt, et, readingRoom, seatLocation, ExtensionNum);
		/*
		 * ȸ��Ŭ������ �޼���� �ٷ� ����
		 */
	}

	public void setMoveCheck(String extensionHour, boolean moveCheck, int extensionNum) {
		cp.moveCheck(extensionHour, moveCheck, extensionNum);
	}

}
