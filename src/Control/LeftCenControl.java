package Control;

import View.CenPan;
import View.LeftPan;

public class LeftCenControl {
	LeftPan lp = new LeftPan();
	CenPan cp = new CenPan();
	String seatLocation = ""; // �¼���ġ
	String nt = ""; // �Խ�
	String et = ""; // ���
	int ExtensionNum = 0;

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

	public void setTime(String nt, String et, String seatLocation, int ExtensionNum) {
		/*
		 * ������ �¼��� �����ϸ� �¼�Ŭ���������� ����(�ð�,����ð�,�¼���ġ,����Ƚ��)���� ȸ��Ŭ������ �Ѱ��ش�
		 */
		this.nt = nt;
		this.et = et;
		this.seatLocation = seatLocation;
		this.ExtensionNum = 0;

		lp.getSeatInfo(nt, et, seatLocation, ExtensionNum);
		/*
		 * ȸ��Ŭ������ �޼���� �ٷ� ����
		 */
	}

	public void setMoveCheck(String extensionHour, boolean moveCheck,int extensionNum) {
		cp.moveCheck(extensionHour, moveCheck, extensionNum);
	}

}
