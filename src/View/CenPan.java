package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Control.LeftCenControl;
/////////////////testest
public class CenPan extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CenImage cenImage = new CenImage();
	JPanel[][] seatpan2 = new JPanel[6][12];
	public static JLabel[][] label = new JLabel[6][12];
	JLayeredPane CenPanLayered = new JLayeredPane();
	Calendar nowTime;
	Calendar endTime;
	static String inTime = "";
	static String outTime = "";
	static int ExtensionNum = 0;
	LeftCenControl lcc;
	public static String nt; // nowTime
	static String et; // endTime
	static boolean ok;// �ߺ� ���� ������?

	public JLayeredPane SetCenPan() {
		CenPanLayered.setBounds(350, 50, 1050, 750);
		CenPanLayered.setLayout(null);
		CenPanLayered.setBackground(Color.black);
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
				label[i][j].setFont(new Font(null, 0, 10));// �۾�ũ��
				label[i][j].setBounds(1, 0, 60, 15); // �۾� �ؽ�Ʈ �ֱ�
				label[i][j].setForeground(Color.red);

				seatpan2[i][j].add(label[i][j]);
				seatpan2[i][j].addMouseListener(new EventHandler(i, j));
				seatpan2[i][j].setOpaque(true);
				seatpan2[i][j].setVisible(true);

				add(seatpan2[i][j]);
				CenPanLayered.add(seatpan2[i][j]);
				// col = 10;

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

	public void clickCheck(boolean ok) {
		this.ok = ok;
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
			lcc = new LeftCenControl();
			String[] str = { "�Խ�", "���" };
			String seatLocation = label[i][j].getText();
			LeftPan leftPan = new LeftPan();
			if (ok == true) {//ok���� LeftPan���� ������
				timeCheck(); // ���� �ð��� nt,et�� ����
				int choice = JOptionPane.showOptionDialog(null,
						"�Խ��� �Ͻðڽ��ϱ�?\n�¼�:" + label[i][j].getText() + "\n�Խǽð�:" + nt + "\n��ǿ����ð�:" + et
								+ "\n*��� ������ ��ǽð� 1�ð� ������ ����\n",
						"����", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
				if (choice == JOptionPane.YES_OPTION) {

					label[i][j].setText("�¼� �����..");
					label[i][j].setLocation(1, 26);
					
					lcc.setCheck(false);// �¼� �ߺ����� ����
					lcc.setTime(nt, et, seatLocation, ExtensionNum);
				} else {
					return;
				}

			}
			leftPan.setCheck(false);

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