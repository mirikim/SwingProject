package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Control.LeftCenControl;

public class CenPan2 extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CenPan cen = new CenPan(); // �̹����� �ҷ����� �ܺ� Ŭ����
	JPanel[][] seatpan2 = new JPanel[6][12];// �¼��� ��׶��� �̹����� ���� �κ�
	public static JLabel[][] label = new JLabel[6][12];// �� �¼��� ǥ����
	JLayeredPane CenPanLayered = new JLayeredPane();// ��ư,�ؽ�Ʈ �� ��ġ �ǳ�
	Calendar nowTime;// Ķ���� ���� �ű� Ÿ�Թ޾ƿð�� �ٽ� �ʱ�ȭ�ؾ��� �׷��� ����
	static String inTime = "";// �Խǽð�
	static String outTime = "";// ��ǽð�
	static int ExtensionNum = 0;// ����Ƚ��
	LeftCenControl lcc;// Control Ŭ���� ����
	// static String nt; // nowTime
	// static String et; // endTime
	// static boolean LoginCheck;// �α��� üũ
	// static boolean SeatCheck; // ������ �¼���뿩�� �����´�.
	static int SeatCount1 = 0; // ���¼�
	static int SeatCount2 = 72; // �����¼�
	JLabel SeatInfo = new JLabel();
	static String extensionHour = "";
	static boolean moveCheck = false;
	static int LExtensionNum = 0;

	// CenTabPan cenTab = new CenTabPan();
	public JLayeredPane SetCenPan() {
		CenPanLayered.setSize(1000, 750);
		// CenPanLayered.setBounds(350, 50, 1050, 750);// ������ ����
		CenPanLayered.setLayout(null);// SetBounds�� ���� ��ġ�� �����ϹǷ� ���̾ƿ��� null
		// CenPanLayered.setBackground(Color.black); // �׽�Ʈ�� �÷�����,,,, �ǹ̾���
		SeatInfo.setBounds(700, -220, 500, 500);
		SeatInfo.setForeground(Color.RED);
		SeatInfo.setFont(new Font(null, 0, 20));
		SeatThread st = new SeatThread(SeatInfo);
		st.start();
		CenPanLayered.add(SeatInfo);
		int row = 15;
		int col = 65;
		char rr = 'A';
		for (int i = 0; i < 6; i++) {

			if (i == 2 || i == 4) {
				col += 35;
			}
			for (int j = 0; j < 12; j++) {
				seatpan2[i][j] = new JPanel();
				seatpan2[i][j].setLayout(null);
				seatpan2[i][j].setBackground(Color.LIGHT_GRAY); // ���� �¼���
				if (j == 0) {
					seatpan2[i][j].setBounds(row, col, 70, 70);
				} else {
					row += 80;
					seatpan2[i][j].setBounds(row, col, 70, 70);
				}
				label[i][j] = new JLabel(rr + "��" + (j + 1) + "��");
				// �󺧿� �¼��� ��ġ�� �����Ѵ�.
				label[i][j].setFont(new Font(null, 0, 10));// �۾�ũ��
				// ��Ʈ �� �۽�ũ�� ����� ���
				label[i][j].setBounds(1, 0, 60, 15); // �ؽ�Ʈ �ֱ�
				label[i][j].setForeground(Color.red);

				seatpan2[i][j].add(label[i][j]);
				seatpan2[i][j].addMouseListener(new EventHandler(i, j));
				seatpan2[i][j].setOpaque(true);
				seatpan2[i][j].setVisible(true);
				add(seatpan2[i][j]);
				CenPanLayered.add(seatpan2[i][j]);
				SeatCount1++;

			}
			rr++;
			col += 80;
			row = 15;
		}
		cen.cenImage.setBounds(0, 0, 1050, 1500);
		cen.cenImage.setOpaque(false);
		// cenImage.setBackground(Color.black);

		CenPanLayered.add(cen.cenImage);

		return CenPanLayered;
	}

	class EventHandler implements MouseListener {
		int i = 0;
		int j = 0;

		public EventHandler(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!label[i][j].getText().equals("�¼� �����..")) {

				if (cen.LoginCheck == true && cen.SeatCheck == false && cen.moveCheck == false) {
					// �α����� �Ǿ���, �¼� �̻�����̸� �¼� ���� ó��
					SeatAssign();
				} else if (cen.LoginCheck == true && cen.SeatCheck == true && cen.moveCheck == true) {

					SeatMove();
				}
			} else {
				// �¼�������ϰ�� �̺�Ʈ�߻�x
				return;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void SeatAssign() {
			lcc = new LeftCenControl();

			// ���۷����� �ʱ�ȭ�� �ٽ��Ѵ�.
			String[] str = { "�Խ�", "���" };
			String seatLocation = label[i][j].getText();
			String readingRoom = CenTabPan.curPaneTitle;

			// �α����� �Ǿ���, �¼� �̻�����̸� �¼� ���� ó��
			cen.timeCheck(); // ���� �ð�, ����ð����� �޾ƿ´�.
			System.out.println(cen.nt + "�Խ�ntntntntn");
			System.out.println(cen.et + "�Խ�etetetetet");
			int choice = JOptionPane.showOptionDialog(null,
					"�Խ��� �Ͻðڽ��ϱ�?\n�¼�:" + CenTabPan.curPaneTitle + seatLocation + "\n�Խǽð�:" + cen.nt + "\n��ǿ����ð�:"
							+ cen.et + "\n*��� ������ ��ǽð� 1�ð� ������ ����\n",
					"����", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
			if (choice == JOptionPane.YES_OPTION) {
				label[i][j].addMouseListener(null);
				label[i][j].setText("�¼� �����..");
				label[i][j].setLocation(1, 26);
				SeatCount2--;
				lcc.setCheck(true);
				// ȸ��Ŭ������ �¼���뿩�ο� ���� HashMap ������Ʈ��
				cen.SeatCheck = true; // �ߺ� ��������!
				lcc.setTime(cen.nt, cen.et, readingRoom, seatLocation, ExtensionNum);
				// �¼� ���ý� �ʱⰪ�� ControlŬ������ ���� ȸ��Ŭ������ �ѱ��.
			} else if (choice == JOptionPane.NO_OPTION) {
				lcc.setCheck(false);
			}
		}

		public void SeatMove() {
			lcc = new LeftCenControl();

			String[] str1 = { "�̵�", "���" };
			String moveSeatLocation = label[i][j].getText();

			String readingRoom = CenTabPan.curPaneTitle;

			String original_readingRoomd = (String) LeftPan.memInfo[LeftPan.index].get(8);
			String seat = (String) LeftPan.memInfo[LeftPan.index].get(7);

			int choice = JOptionPane.showOptionDialog(null,
					" �̵��� �Ͻðڽ��ϱ�?\n�����¼�: " + original_readingRoomd + seat + "\n�̵��¼�: " + CenTabPan.curPaneTitle
							+ moveSeatLocation + "\n�Խǽð�: " + cen.nt + "\n��ǿ����ð�: " + cen.et
							+ "\n*��� ������ ��ǽð� 1�ð� ������ ����\n",
					"����", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str1, str1[0]);
			if (choice == JOptionPane.YES_OPTION) {

				char row = seat.charAt(0);// A,B,C,D....
				int col = Integer.parseInt(seat.charAt(2) + "");// 1��,2��....
				int rowNum = 0;
				if (row == 'A')
					rowNum = 0;
				else if (row == 'B')
					rowNum = 1;
				else if (row == 'C')
					rowNum = 2;
				else if (row == 'D')
					rowNum = 3;
				else if (row == 'E')
					rowNum = 4;
				else if (row == 'F')
					rowNum = 5;

				for (int i = 8; i > 3; i--) {
					LeftPan.memInfo[LeftPan.index].remove(i);
					// �Խǽð� ��ǽð� ����Ƚ��,�¼� ����
				}
				SeatCount2++;
				label[i][j].setText("�¼� �����..");
				label[i][j].setLocation(1, 26);
				if (original_readingRoomd.equals("1������")) {
					CenPan.label[rowNum][col - 1].setText(row + "��" + col + "��");
					CenPan.label[rowNum][col - 1].setBounds(1, 0, 60, 15);
					// LeftPanClear();// �¼� ��� ������ �α׾ƿ�
				} else if (original_readingRoomd.equals("2������")) {
					CenPan2.label[rowNum][col - 1].setText(row + "��" + col + "��");
					CenPan2.label[rowNum][col - 1].setBounds(1, 0, 60, 15);
				}

				SeatCount2--;
				lcc.setCheck(true);
				// ȸ��Ŭ������ �¼���뿩�ο� ���� HashMap ������Ʈ��
				cen.SeatCheck = true; // �ߺ� ��������!
				lcc.setTime(cen.nt, extensionHour, readingRoom, moveSeatLocation, LExtensionNum);

				lcc.setMoveCheck(cen.et, false, LExtensionNum);// �¼��̵��� �̵����� �ʱ�ȭ
				// �¼� ���ý� �ʱⰪ�� ControlŬ������ ���� ȸ��Ŭ������ �ѱ��.
			} else if (choice == JOptionPane.NO_OPTION) { // ��ҹ�ư�������
				lcc.setCheck(true);
				cen.SeatCheck = true;
				lcc.setTime(cen.nt, extensionHour, original_readingRoomd, seat, LExtensionNum);
				lcc.setMoveCheck(cen.et, false, LExtensionNum);
				// return;
			} else {
				lcc.setCheck(true);
				cen.SeatCheck = true;
				lcc.setTime(cen.nt, extensionHour, original_readingRoomd, seat, LExtensionNum);
				lcc.setMoveCheck(cen.et, false, LExtensionNum);
			}

		}

	}

}