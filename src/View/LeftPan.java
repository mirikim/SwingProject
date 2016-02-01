package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Control.LeftCenControl;

public class LeftPan extends JoinFrame {

	JLayeredPane LeftLayeredPane = new JLayeredPane();
	LeftImage LeftImage = new LeftImage();
	static JTextArea jta = new JTextArea();
	JLabel lid = new JLabel("ID :");
	static JTextField loginId = new JTextField(20);
	JLabel lpsw = new JLabel("PW :");
	JTextField jpsw = new JTextField(20);
	JButton login = new JButton("�α���");
	JButton join = new JButton("ȸ������");
	JButton logout = new JButton("�α׾ƿ�");
	JButton extension = new JButton("����");
	JButton Out = new JButton("���");
	JButton move = new JButton("�̵�");
	static boolean ok = false;
	String id = "asdf";
	String pwsd = "1111";
	JoinFrame jf;
	LeftCenControl lcc;
	static int index = 0;
	static String nt;
	static String et;
	static String seatLocation;
	static String readingRoom;
	static int ExtensionNum;
	static int extensionNum = 0;

	public JLayeredPane SetLeftPan() {
		LeftLayeredPane.setBounds(0, 0, 350, 800);
		LeftLayeredPane.setLayout(null);
		LeftLayeredPane.setOpaque(false);
		LeftImage.setBounds(0, 0, 350, 800);
		jta.setBounds(0, 100, 350, 250);
		jta.setBackground(Color.white);
		lid.setForeground(Color.white);
		lpsw.setForeground(Color.white);
		lid.setBounds(35, 250, 40, 20);
		loginId.setBounds(60, 250, 200, 20);
		lpsw.setBounds(30, 300, 40, 20);
		jpsw.setBounds(60, 300, 200, 20);
		login.setBounds(55, 350, 100, 30);
		join.setBounds(160, 350, 100, 30);
		logout.setBounds(10, 400, 80, 30);
		extension.setBounds(90, 400, 80, 30);
		Out.setBounds(180, 400, 80, 30);
		move.setBounds(260, 400, 80, 30);
		login.addActionListener(new EventHandler());
		join.addActionListener(new EventHandler());
		logout.addActionListener(new EventHandler());
		extension.addActionListener(new EventHandler());
		Out.addActionListener(new EventHandler());
		move.addActionListener(new EventHandler());
		LeftLayeredPane.add(lid);
		LeftLayeredPane.add(loginId);
		LeftLayeredPane.add(lpsw);
		LeftLayeredPane.add(jpsw);
		LeftLayeredPane.add(login);
		LeftLayeredPane.add(join);
		LeftLayeredPane.add(logout);
		LeftLayeredPane.add(extension);
		LeftLayeredPane.add(Out);
		LeftLayeredPane.add(move);
		LeftLayeredPane.add(jta);
		LeftLayeredPane.add(LeftImage);
		jta.setVisible(false);
		logout.setVisible(false);
		extension.setVisible(false);
		Out.setVisible(false);
		move.setVisible(false);
		return LeftLayeredPane;
	}

	class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			switch (command) {
			case "�α���": {
				login();
			}
				break;
			case "ȸ������":
				jf = new JoinFrame();
				jf._JoinFrame();

				break;
			case "�α׾ƿ�":

				JOptionPane.showMessageDialog(null, "�α׾ƿ��մϴ�.");
				LeftPanClear();
				break;
			case "���":
				CheckOut();
				break;
			case "����":
				ExtendOutTime();
				break;
			case "�̵�":
				MoveRight();
				break;
			}
		}
	}

	public void login() {
		lcc = new LeftCenControl();

		String logintext = "";
		if (memcheck.contains(loginId.getText())) {
			index = (int) hsmem.get(loginId.getText());
			if (jpsw.getText().equals(memInfo[index].get(1))) {
				if (memcheck.contains(joinId.getText())) {
					index = memcheck.indexOf(joinId.getText());
				}
				joinId.setText("");
				JOptionPane.showMessageDialog(null, "�α��� ����");
				loginId.setVisible(false);
				lid.setVisible(false);
				jpsw.setVisible(false);
				lpsw.setVisible(false);
				login.setVisible(false);
				join.setVisible(false);
				jta.setVisible(true);

				if (memInfo[index].size() > 4) {
					logintext += "�¼� ��ġ : " + memInfo[index].get(8) + memInfo[index].get(7) + "\n�Խ� �ð� : "
							+ memInfo[index].get(4) + "\n\n ��ǿ����ð� : " + memInfo[index].get(5) + "\n\n ����Ƚ�� :"
							+ memInfo[index].get(6);

				}
				jta.setText("\n\n  " + loginId.getText() + " ȸ���� ȯ���մϴ�.\n\n" + logintext);
				// jta.setOpaque(false);
				logout.setVisible(true);
				extension.setVisible(true);
				Out.setVisible(true);
				move.setVisible(true);
				setCheck(true, (boolean) usedSeat.get(loginId.getText()));
				// �α��� üũ, �¼� ���üũ �¼�Ŭ������ �¼��� ������� ���̵����� �ƴ��� �ʱⰪ�� ����

			}
			// ���̵� �����ϸ�

		} else if (loginId.getText().length() == 0 || jpsw.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "ID��  PW�� �Է����ּ���.");
		} else if (!hsmem.containsKey(loginId.getText())) {
			JOptionPane.showMessageDialog(null, "ID �Ǵ� PW�� ��ġ���� �ʽ��ϴ�.");
		}
	}

	public void CheckOut() {

		if (usedSeat.get(loginId.getText()).equals(false)) {
			JOptionPane.showMessageDialog(null, "�������� �¼��� �����Ƿ� �α׾ƿ��մϴ�.");
			LeftPanClear();// �α׾ƿ�
		} else if (usedSeat.get(loginId.getText()).equals(true)) {
			String readingroomCheck = (String) memInfo[index].get(8);
			String seat = (String) memInfo[index].get(7);
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
				memInfo[index].remove(i);
				// �Խǽð� ��ǽð� ����Ƚ��,�¼� ����

			}
			JOptionPane.showMessageDialog(null, "����մϴ�.");
			setCheck(false);
			if (readingroomCheck.equals("1������")) {
				CenPan.label[rowNum][col - 1].setText(row + "��" + col + "��");
				CenPan.label[rowNum][col - 1].setBounds(1, 0, 60, 15);
				// LeftPanClear();// �¼� ��� ������ �α׾ƿ�
			} else if (readingroomCheck.equals("2������")) {
				CenPan2.label[rowNum][col - 1].setText(row + "��" + col + "��");
				CenPan2.label[rowNum][col - 1].setBounds(1, 0, 60, 15);
			}

			LeftPanClear();// �¼� ��� ������ �α׾ƿ�

		}
	}

	public void ExtendOutTime() {
		{
			Calendar nowTime = Calendar.getInstance();

			if (memInfo[index].size() < 5) {
				JOptionPane.showMessageDialog(null, "�¼������� �����ϴ�. �¼������� �� �� ������ �����մϴ�.");
				return;
			} else {
				String outTime = (String) memInfo[index].get(5);
				int remainHour = Integer.parseInt(outTime.substring(0, 1)) - nowTime.get(Calendar.HOUR);// �����ִ�
				// �ð������
				System.out.println(remainHour + "�ð�����");
				if (remainHour > 1) {
					JOptionPane.showMessageDialog(null, "������ ��ǿ����ð� 1�ð������� �����մϴ�.");

				} else if (remainHour <= 1) {

					String[] str = { "����", "���" };
					String extensionHour = (nowTime.get(Calendar.HOUR) + 4) + "��" + nowTime.get(Calendar.MINUTE) + "��"
							+ nowTime.get(Calendar.SECOND) + "��";
					extensionNum = (int) memInfo[index].get(6) + 1;
					// String seatlocation = (String) jf.memInfo[index].get(7);
					// String inHour = (String) jf.memInfo[index].get(4);

					int choice = JOptionPane.showOptionDialog(null,
							"���� �Ͻðڽ��ϱ�??\n�¼�:" + memInfo[index].get(8) + memInfo[index].get(7) + "\n�Խǽð�:"
									+ memInfo[index].get(4) + "\n\n ��ǿ����ð� : " + extensionHour + "\n\n ����Ƚ�� :"
									+ extensionNum + "\n*��� ������ ��ǽð� 1�ð� ������ ����\n",

							"����", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
					if ((int) memInfo[index].get(6) > 2) {
						JOptionPane.showMessageDialog(null, "������ 3ȸ������ �����մϴ�.(" + memInfo[index].get(6) + "/3)");

					} else if (choice == JOptionPane.YES_OPTION) {
						this.et = extensionHour;
						memInfo[index].set(5, extensionHour);// ��ǽð�
						// ����
						memInfo[index].set(6, extensionNum);// ����Ƚ��
						// ����
						JOptionPane.showMessageDialog(null, "����" + extensionNum + "ȸ �ϼ̽��ϴ�. (" + extensionNum + "/3)");

						jta.setText("\n\n " + memInfo[index].get(0) + " ȸ���� �湮�� ȯ���մϴ�.\n\n �¼� : "
								+ memInfo[index].get(8) + memInfo[index].get(7) + "\n\n�Խǽð� : " + memInfo[index].get(4)
								+ "\n\n ��ǿ����ð� : " + memInfo[index].get(5) + "\n\n ����Ƚ�� :" + memInfo[index].get(6));

					} else {
						return;
					}
				}
			}
		}
	}

	public void MoveRight() {// �¼��̵����� �ο� �޼���
		lcc = new LeftCenControl();
		if (usedSeat.get(loginId.getText()).equals(false)) {
			JOptionPane.showMessageDialog(null, "�������� �¼��� ������ �¼��� �����ϼ���.");
			// LeftPanClear();// �α׾ƿ�
		} else if (usedSeat.get(loginId.getText()).equals(true)) {
			// �̵��� �� �ִ� ���Ѹ� �ο��ϰ� �̵��� CenPan���� �����ϵ���
			JOptionPane.showMessageDialog(null, "�̵��� �¼��� �������ּ���.");
			setCheck(true);
			lcc.setCheck(true, true); // �α���,�¼����� �Ǿ��ִ»���
			lcc.setMoveCheck(et, true, extensionNum);// �̵����� true

		}
	}

	public void setCheck(boolean loginCheck, boolean usedSeat) {
		lcc = new LeftCenControl();
		lcc.setCheck(loginCheck, usedSeat);
		// ���� �α����� ������ ���°���, �¼��� ����������ƴѵ� HashMap���κ���
		// ��� ���θ� �����Ͽ� �¼�Ŭ������ �ѱ��.
	}

	public void setCheck(boolean check) {// �ް����ϱ� �����ε�
		usedSeat.replace(loginId.getText(), check);
		// �¼�Ŭ�����κ��� ȸ���� �¼��� ����ϴ��� ���ϴ��� ���� �޾ƿ� ������Ʈ �Ѵ�.
	}

	boolean getCheck() {
		return ok;
	}

	public void LeftPanClear() {
		jta.setVisible(false);
		Out.setVisible(false);
		extension.setVisible(false);
		logout.setVisible(false);
		move.setVisible(false);
		loginId.setVisible(true);
		loginId.setText("");
		lid.setVisible(true);
		jpsw.setVisible(true);
		jpsw.setText("");
		lpsw.setVisible(true);
		login.setVisible(true);
		join.setVisible(true);
		setCheck(false);
		lcc.setCheck(false, false);
		lcc.setMoveCheck("", false, 0);

	}

	public void getSeatInfo(String nt, String et, String readingRoom, String seatLocation, int ExtensionNum) {
		// nowTime �̶�,endTime �� �����´�
		this.nt = nt;
		this.et = et;
		this.seatLocation = seatLocation;
		this.ExtensionNum = ExtensionNum;
		this.readingRoom = readingRoom;
		memInfo[index].add(nt); // 4 �Խǽð�
		memInfo[index].add(et); // 5 ��ǽð�
		memInfo[index].add(ExtensionNum); // 6 ����Ƚ��
		memInfo[index].add(seatLocation); // 7 �¼���ġ
		memInfo[index].add(readingRoom); // 8������
		// memInfo[index].set(4, nt); // 4 �Խǽð�
		// memInfo[index].set(5, et); // 5 ��ǽð�
		// memInfo[index].set(6, ExtensionNum); // 6 ����Ƚ��
		// memInfo[index].set(7, seatLocation); // 7 �¼���ġ
		// memInfo[index].set(8, readingRoom); // 8������
		jta.setText("\n\n " + memInfo[index].get(0) + " ȸ���� �湮�� ȯ���մϴ�.\n\n �¼� : " + memInfo[index].get(8)
				+ memInfo[index].get(7) + "\n\n�Խǽð� : " + memInfo[index].get(4) + "\n\n ��ǿ����ð� : "
				+ memInfo[index].get(5) + "\n\n ����Ƚ�� :" + memInfo[index].get(6));
	}

}

class LeftImage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img = null;

	@Override
	public void paint(Graphics g) {
		try {
			img = ImageIO.read(new File("image/rectangle_blue_purple.jpg"));
		} catch (IOException e) {
			System.out.println("�̹��� �ҷ����� ����");
			System.exit(0);
		}
		g.drawImage(img, -20, 50, this);
	}

}
