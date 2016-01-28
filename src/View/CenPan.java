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
import javax.swing.plaf.synth.SynthSpinnerUI;

import Control.LeftCenControl;

public class CenPan extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CenImage cenImage = new CenImage(); // �̹����� �ҷ����� �ܺ� Ŭ����
	JPanel[][] seatpan2 = new JPanel[6][12];// �¼��� ��׶��� �̹����� ���� �κ�
	public static JLabel[][] label = new JLabel[6][12];// �� �¼��� ǥ����
	JLayeredPane CenPanLayered = new JLayeredPane();// ��ư,�ؽ�Ʈ �� ��ġ �ǳ�
	Calendar nowTime;// Ķ���� ���� �ű� Ÿ�Թ޾ƿð�� �ٽ� �ʱ�ȭ�ؾ��� �׷��� ����
	static String inTime = "";// �Խǽð�
	static String outTime = "";// ��ǽð�
	static int ExtensionNum = 0;// ����Ƚ��
	LeftCenControl lcc;// Control Ŭ���� ����
	static String nt; // nowTime
	static String et; // endTime
	static boolean LoginCheck;// �α��� üũ
	static boolean SeatCheck; // ������ �¼���뿩�� �����´�.
	static int SeatCount1 = 0; // ���¼�
	static int SeatCount2 = 72; // �����¼�
	JLabel SeatInfo = new JLabel();

	public JLayeredPane SetCenPan() {
		CenPanLayered.setBounds(350, 50, 1050, 750);// ������ ����
		CenPanLayered.setLayout(null);// SetBounds�� ���� ��ġ�� �����ϹǷ� ���̾ƿ��� null
		CenPanLayered.setBackground(Color.black); // �׽�Ʈ�� �÷�����,,,, �ǹ̾���
		SeatInfo.setBounds(700, -220, 500, 500);
		SeatInfo.setForeground(Color.RED);
		SeatInfo.setFont(new Font(null, 0, 20));
		SeatThread st = new SeatThread(SeatInfo);
		st.start();
		CenPanLayered.add(SeatInfo);
		int row = 20;
		int col = 65;
		char rr = 'A';
		for (int i = 0; i < 6; i++) {

			if (i == 2 || i == 4) {
				col += 35;
			}
			for (int j = 0; j < 12; j++) {
				seatpan2[i][j] = new JPanel();
				seatpan2[i][j].setLayout(null);
				seatpan2[i][j].setBackground(Color.CYAN); // ���� �¼���
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
			row = 20;
		}
		cenImage.setBounds(0, 0, 1050, 1500);
		cenImage.setBackground(Color.black);

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
		et = (nowTime.get(Calendar.HOUR) + 1) + "��" + nowTime.get(Calendar.MINUTE) + "��" + nowTime.get(Calendar.SECOND)
				+ "��";

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
			SeatAssign();

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

			if (LoginCheck == true && SeatCheck == false) {
				// �α����� �Ǿ���, �¼� �̻�����̸� �¼� ���� ó��
				timeCheck(); // ���� �ð�, ����ð����� �޾ƿ´�.
				int choice = JOptionPane.showOptionDialog(null,
						"�Խ��� �Ͻðڽ��ϱ�?\n�¼�:" + label[i][j].getText() + "\n�Խǽð�:" + nt + "\n��ǿ����ð�:" + et
								+ "\n*��� ������ ��ǽð� 1�ð� ������ ����\n",
						"����", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
				if (choice == JOptionPane.YES_OPTION) {

					label[i][j].setText("�¼� �����..");
					label[i][j].setLocation(1, 26);
					SeatCount2--;
					lcc.setCheck(true);
					// ȸ��Ŭ������ �¼���뿩�ο� ���� HashMap ������Ʈ��
					SeatCheck = true; // �ߺ� ��������!
					lcc.setTime(nt, et, seatLocation, ExtensionNum);
					// �¼� ���ý� �ʱⰪ�� ControlŬ������ ���� ȸ��Ŭ������ �ѱ��.
					System.out.println("��� �꽺");
				} else if (choice == JOptionPane.NO_OPTION) {
					System.out.println("��� ��");
					lcc.setCheck(false);
				}

			}

		}

	}
}

class SeatThread extends Thread {
	JLabel SeatInfo;
	int i = 0;
	CenPan cp = new CenPan();

	public SeatThread(JLabel seatInfo) {
		this.SeatInfo = seatInfo;
	}

	@Override
	public void run() {
		while (true) {
			try {

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
			i++;
			SeatInfo.setText("[ ���� �¼�:" + cp.SeatCount2 + "  /  �� �¼� :" + cp.SeatCount1 + " ]");
		}
	}
}

class CenImage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img = null; // �̹������� �ȳ���
	//
	// @Override
	// public void paint(Graphics g) {
	// try {
	// img = ImageIO.read(new File("image/rectangle_blue_purple.jpg"));
	// } catch (IOException e) {
	// System.out.println("�̹��� �ҷ����� ����");
	// System.exit(0);
	// }
	//
	// g.drawImage(img, 0, 0, 1050, 1500, null, this);
	// }
}