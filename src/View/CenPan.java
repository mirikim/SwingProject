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

public class CenPan extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CenImage cenImage = new CenImage();
	JPanel[][] seatpan2 = new JPanel[6][12];
	JLabel[][] label = new JLabel[6][12];
	JLayeredPane CenPanLayered = new JLayeredPane();
	Calendar nowTime = Calendar.getInstance();
	Calendar endTime = Calendar.getInstance();
	static String inTime = "";
	static String outTime = "";
	static int ExtensionNum = 0;

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

	class EventHandler implements MouseListener {
		int i = 0;
		int j = 0;

		public EventHandler(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			String nt = (nowTime.get(Calendar.HOUR) + 12) + "��" + nowTime.get(Calendar.MINUTE) + "��"
					+ nowTime.get(Calendar.SECOND) + "��";
			String et = (nowTime.get(Calendar.HOUR) + 15) + "��" + nowTime.get(Calendar.MINUTE) + "��"
					+ nowTime.get(Calendar.SECOND) + "��";
			String[] str = { "�Խ�", "���" };
			LeftPan leftPan = new LeftPan();

			System.out.println(leftPan.getCheck());
			if (leftPan.getCheck() == true) {

				int choice = JOptionPane.showOptionDialog(null,
						"�Խ��� �Ͻðڽ��ϱ�?\n�¼�:" + label[i][j].getText() + "\n�Խǽð�:" + nt + "\n��ǿ����ð�:" + et
								+ "\n*��� ������ ��ǽð� 1�ð� ������ ����\n",
						"����", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
				if (choice == JOptionPane.YES_OPTION) {
					inTime = nt;
					outTime = et;
					leftPan.jf.memInfo[0].set(4, inTime);
					leftPan.jf.memInfo[0].set(5, outTime);
					leftPan.jf.memInfo[0].set(6, ExtensionNum);
					leftPan.jta.setText("   " + leftPan.jf.memInfo[0].get(0) + " ȸ���� �湮�� ȯ���մϴ�.\n\n �Խǽð� : "
							+ leftPan.jf.memInfo[0].get(4) + "\n\n ��ǿ����ð� : " + leftPan.jf.memInfo[0].get(5) + "\n\n ����Ƚ�� :"
							+leftPan.jf.memInfo[0].get(6));
					
					
					//�ȹٲ��.
					label[i][j].setText("�¼� �����..");
					label[i][j].setLocation(1, 26);
					Frame confirmFrame = new Frame();
					confirmFrame.setSize(200, 200);
					Panel confirmPanel = new Panel();
					Label testest = new Label("Test");
					confirmPanel.add(testest);
					confirmFrame.add(confirmPanel);
					for(int i=0;i<leftPan.jf.memInfo[0].size();i++){
						System.out.println((i+1)+" : "+leftPan.jf.memInfo[0].get(i));
					}
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