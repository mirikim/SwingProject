package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Control.LeftCenControl;

public class CenPan extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CenImage cenImage = new CenImage(); // �̹����� �ҷ����� �ܺ� Ŭ����

	JPanel[][] seatpan2 = new JPanel[6][12];// �¼��� ��׶��� �̹����� ���� �κ�
	public static JLabel[][] label = new JLabel[6][12];// �� �¼��� ǥ����
	// SeatImage SeatImage[][] = new SeatImage[6][12];
	SeatImage SeatImage = new SeatImage();
	JLayeredPane CenPanLayered = new JLayeredPane();// ��ư,�ؽ�Ʈ �� ��ġ �ǳ�
	Calendar nowTime;// Ķ���� ���� �ű� Ÿ�Թ޾ƿð�� �ٽ� �ʱ�ȭ�ؾ��� �׷��� ����
	// static String inTime = "";// �Խǽð�
	// static String outTime = "";// ��ǽð�
	static int ExtensionNum = 0;// ����Ƚ��
	LeftCenControl lcc;// Control Ŭ���� ����
	static String nt; // nowTime
	static String et; // endTime
	static boolean LoginCheck;// �α��� üũ
	static boolean SeatCheck; // ������ �¼���뿩�� �����´�.
	static int SeatCount1 = 0; // ���¼�
	static int SeatCount2 = 72; // �����¼�
	JLabel SeatInfo = new JLabel();
	static String extensionHour = "";
	static boolean moveCheck = false;
	static int LExtensionNum = 0;

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
				// seatpan2[i][j].setBackground(cenImage2); // ���� �¼���
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
				label[i][j].setBounds(10, 2, 60, 15); // �ؽ�Ʈ �ֱ�
				label[i][j].setForeground(Color.red);

				// SeatImage[i][j].setBounds(0, 0, 70, 70);
				// SeatImage[i][j].setOpaque(true);// ���ϰ� true�ιٲٱ�

				seatpan2[i][j].add(label[i][j]);
				// seatpan2[i][j].add(SeatImage[i][j]);
				seatpan2[i][j].addMouseListener(new EventHandler(i, j));
				seatpan2[i][j].setOpaque(true);
				seatpan2[i][j].setVisible(true);
				seatpan2[i][j].add(SeatImage);
				SeatImage.setBounds(0, 0, 70, 70);
				SeatImage.setOpaque(false);// ���ϰ� true�ιٲٱ�
				add(seatpan2[i][j]);
				CenPanLayered.add(seatpan2[i][j]);

				SeatCount1++;

			}
			rr++;
			col += 80;
			row = 15;
		}
		cenImage.setBounds(0, 0, 1050, 1500);
		cenImage.setOpaque(false);

		CenPanLayered.add(cenImage);

		return CenPanLayered;
	}

	public void clickCheck(boolean LoginCheck, boolean seatCheck) {
		/*
		 * Control Ŭ�����κ��� Ŭ�����ص��Ǵ��� �ȵǴ��� ���� �޾ƿ�
		 */
		this.LoginCheck = LoginCheck;
		this.SeatCheck = seatCheck;
	}

	public void timeCheck() {

		nowTime = Calendar.getInstance();
		nt = (nowTime.get(Calendar.HOUR)) + "��" + nowTime.get(Calendar.MINUTE) + "��" + nowTime.get(Calendar.SECOND)
				+ "��";
		et = (nowTime.get(Calendar.HOUR)) + "��" + (nowTime.get(Calendar.MINUTE) + 1) + "��"
				+ nowTime.get(Calendar.SECOND) + "��";

	}

	public void moveCheck(String extensionHour, boolean moveCheck, int extensionNum) {
		this.extensionHour = extensionHour;
		this.moveCheck = moveCheck;
		this.LExtensionNum = extensionNum;

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

				if (LoginCheck == true && SeatCheck == false && moveCheck == false) {
					// �α����� �Ǿ���, �¼� �̻�����̸� �¼� ���� ó��
					SeatAssign();
				} else if (LoginCheck == true && SeatCheck == true && moveCheck == true) {

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
			timeCheck(); // ���� �ð�, ����ð����� �޾ƿ´�.

			int choice = JOptionPane.showOptionDialog(null,
					"�Խ��� �Ͻðڽ��ϱ�?\n �¼�:" + CenTabPan.curPaneTitle + seatLocation + "\n �Խǽð�:" + nt + "\n ��ǿ����ð�:" + et
							+ "\n *��� ������ ��ǽð� 1�ð� ������ ����\n",
					"����", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
			if (choice == JOptionPane.YES_OPTION) {
				label[i][j].addMouseListener(null);
				label[i][j].setText("�¼� �����..");
				label[i][j].setLocation(1, 26);
				SeatCount2--;
				lcc.setCheck(true);
				// ȸ��Ŭ������ �¼���뿩�ο� ���� HashMap ������Ʈ��
				SeatCheck = true; // �ߺ� ��������!
				lcc.setTime(nt, et, readingRoom, seatLocation, ExtensionNum);
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

			String original_readingRoomd = (String) LeftPan.memInfo[LeftPan.index].get(9);
			String seat = (String) LeftPan.memInfo[LeftPan.index].get(8);

			int choice = JOptionPane.showOptionDialog(null,
					" �̵��� �Ͻðڽ��ϱ�?\n �����¼�: " + original_readingRoomd + seat + "\n �̵��¼�: " + CenTabPan.curPaneTitle
							+ moveSeatLocation + "\n �Խǽð�: " + nt + "\n ��ǿ����ð�: " + et + "\n *��� ������ ��ǽð� 1�ð� ������ ����\n",
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

				for (int i = 9; i > 4; i--) {
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
				SeatCheck = true; // �ߺ� ��������!
				lcc.setTime(nt, extensionHour, readingRoom, moveSeatLocation, LExtensionNum);

				lcc.setMoveCheck(et, false, LExtensionNum);// �¼��̵��� �̵����� �ʱ�ȭ
				// �¼� ���ý� �ʱⰪ�� ControlŬ������ ���� ȸ��Ŭ������ �ѱ��.
			} else if (choice == JOptionPane.NO_OPTION) { // ��ҹ�ư�������
				lcc.setCheck(true);
				SeatCheck = true;
				lcc.setTime(nt, extensionHour, original_readingRoomd, seat, LExtensionNum);
				lcc.setMoveCheck(et, false, LExtensionNum);
				// return;
			} else {
				lcc.setCheck(true);
				SeatCheck = true;
				lcc.setTime(nt, extensionHour, original_readingRoomd, seat, LExtensionNum);
				lcc.setMoveCheck(et, false, LExtensionNum);
			}

		}

	}

}

class SeatThread extends Thread {
	JLabel SeatInfo;
	int i = 0;
	CenPan cp = new CenPan();
	LeftPan lp = new LeftPan();

	public SeatThread(JLabel seatInfo) {
		this.SeatInfo = seatInfo;
	}

	@Override
	public void run() {
		while (true) {
			Calendar ThreadTime = Calendar.getInstance();
			String ThreadTimeCheck = (ThreadTime.get(Calendar.HOUR)) + "��" + ThreadTime.get(Calendar.MINUTE) + "��"
					+ ThreadTime.get(Calendar.SECOND) + "��";
			try {

				Thread.sleep(100);

			} catch (InterruptedException e) {
				return;
			}
			i++;
			SeatInfo.setText("[ ���� �¼�:" + cp.SeatCount2 + "  /  �� �¼� :" + cp.SeatCount1 + " ]");

			for (int t = 0; t < lp.memInfo.length; t++) {
				if (lp.memInfo[t] == null) {
					// System.out.println(ThreadTimeCheck);
				} else if ((lp.memInfo[t] != null)) {
					if (lp.memInfo[t].size() > 6) {
						if (lp.memInfo[t].get(6).equals(ThreadTimeCheck)) {

							System.out.println("����ð� : " + ThreadTimeCheck + "��ǿ����ð�:" + lp.memInfo[t].get(6));

							lp.OutoCheckOut(t);

						}

					}

				}
			}

		}
	}
}

class CenImage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img = null; // �̹������� �ȳ���

	@Override
	public void paint(Graphics g) {
		try {
			img = ImageIO.read(new File("image/white.jpg"));
		} catch (IOException e) {
			System.out.println("�̹��� �ҷ����� ����");
			System.exit(0);
		}

		g.drawImage(img, 0, 0, 1050, 1500, null, this);
	}
}

class SeatImage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img = null; // �̹������� �ȳ���

	@Override
	public void paint(Graphics g) {
		try {
			img = ImageIO.read(new File("image/seat.png"));
		} catch (IOException e) {
			System.out.println("�̹��� �ҷ����� ����");
			System.exit(0);
		}

		g.drawImage(img, 0, 0, 70, 70, null, this);
	}
}