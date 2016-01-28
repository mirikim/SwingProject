package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Control.LeftCenControl;

public class LeftPan {
	JLayeredPane LeftLayeredPane = new JLayeredPane();
	LeftImage LeftImage = new LeftImage();
	static JTextArea jta = new JTextArea();
	JLabel lid = new JLabel("ID :");
	static JTextField jid = new JTextField(20);
	JLabel lpsw = new JLabel("PW :");
	JTextField jpsw = new JTextField(20);
	JButton login = new JButton("�α���");
	JButton join = new JButton("ȸ������");
	JButton logout = new JButton("�α׾ƿ�");
	JButton extension = new JButton("����");
	JButton Out = new JButton("���");
	static boolean ok = false;
	String id = "asdf";
	String pwsd = "1111";
	JoinFrame jf;
	LeftCenControl lcc;
	static int index = 0;
	static String nt;
	static String et;
	static String seatLocation;
	static int ExtensionNum;

	public JLayeredPane SetLeftPan() {
		// JLayeredPane LeftLayeredPane = new JLayeredPane();
		LeftLayeredPane.setBounds(0, 0, 350, 800);
		LeftLayeredPane.setLayout(null);
		LeftImage.setBounds(0, 0, 350, 800);
		jta.setBounds(0, 100, 350, 250);
		jta.setBackground(Color.white);
		lid.setForeground(Color.white);
		lpsw.setForeground(Color.white);
		lid.setBounds(35, 250, 40, 20);
		jid.setBounds(60, 250, 200, 20);
		lpsw.setBounds(30, 300, 40, 20);
		jpsw.setBounds(60, 300, 200, 20);
		login.setBounds(55, 350, 100, 30);
		join.setBounds(160, 350, 100, 30);
		logout.setBounds(15, 400, 100, 30);
		extension.setBounds(125, 400, 100, 30);
		Out.setBounds(235, 400, 100, 30);
		login.addActionListener(new EventHandler());
		join.addActionListener(new EventHandler());
		logout.addActionListener(new EventHandler());
		extension.addActionListener(new EventHandler());
		Out.addActionListener(new EventHandler());
		LeftLayeredPane.add(lid);
		LeftLayeredPane.add(jid);
		LeftLayeredPane.add(lpsw);
		LeftLayeredPane.add(jpsw);
		LeftLayeredPane.add(login);
		LeftLayeredPane.add(join);
		LeftLayeredPane.add(logout);
		LeftLayeredPane.add(extension);
		LeftLayeredPane.add(Out);
		LeftLayeredPane.add(jta);
		LeftLayeredPane.add(LeftImage);
		jta.setVisible(false);
		logout.setVisible(false);
		extension.setVisible(false);
		Out.setVisible(false);

		return LeftLayeredPane;
	}

	class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			switch (command) {
			case "�α���": {
				lcc = new LeftCenControl();
				jf = new JoinFrame();

				if (jf.memcheck.contains(jid.getText())) {
					index = (int) jf.hsmem.get(jid.getText());
					if (jpsw.getText().equals(jf.memInfo[index].get(1))) {
						if (jf.memcheck.contains(jf.id.getText())) {
							index = jf.memcheck.indexOf(jf.id.getText());
						} else {
							JOptionPane.showMessageDialog(null, "����");
						}
						jf.id.setText("");
						JOptionPane.showMessageDialog(null, "�α��� ����");
						jid.setVisible(false);
						jpsw.setVisible(false);
						login.setVisible(false);
						join.setVisible(false);
						jta.setVisible(true);
						String logintext = "";
						System.out.println(jf.memInfo[index].size());
						if (jf.memInfo[index].size() != 4) {
							logintext += jf.memInfo[index].get(4) + "\n\n ��ǿ����ð� : " + jf.memInfo[index].get(5)
									+ "\n\n ����Ƚ�� :" + jf.memInfo[index].get(6);

						}
						jta.setText("\n\n " + jid.getText() + " ȸ���� ȯ���մϴ�.\n\n" + logintext);
						logout.setVisible(true);
						extension.setVisible(true);
						Out.setVisible(true);
						setCheck(true, (boolean) jf.usedSeat.get(jid.getText()));
						// �α��� üũ, �¼� ���üũ �¼�Ŭ������ �¼��� ������� ���̵����� �ƴ��� �ʱⰪ�� ����

					}
					// ���̵� �����ϸ�

				} else if (!jf.hsmem.containsKey(jid.getText())) {
					JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
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
				// �������� �¼������� �ٷ� �α׾ƿ�
				if (jf.memInfo[index].size() < 5) {
					JOptionPane.showMessageDialog(null, "�������� �¼��� �����Ƿ� �α׾ƿ��մϴ�.");
					LeftPanClear();// �α׾ƿ�
				}
				if (jf.memInfo[index].size() > 4) {
					String seat = (String) jf.memInfo[index].get(7);
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

					for (int i = 7; i > 3; i--) {
						jf.memInfo[index].remove(i);
						// �Խǽð� ��ǽð� ����Ƚ��,�¼� ����

					}
					JOptionPane.showMessageDialog(null, "����մϴ�.");

					CenPan.label[rowNum][col - 1].setText(row + "��" + col + "��");
					CenPan.label[rowNum][col - 1].setBounds(1, 0, 60, 15);
					LeftPanClear();// �¼� ��� ������ �α׾ƿ�

				}
				break;
			case "����": {
				Calendar nowTime = Calendar.getInstance();
				if ((int) jf.memInfo[index].get(6) < 3) {
					if (jf.memInfo[index].size() < 5) {
						JOptionPane.showMessageDialog(null, "�¼������� �����ϴ�. �¼������� �� �� ������ �����մϴ�.");
						return;
					} else {
						String outTime = (String) jf.memInfo[index].get(5);
						int remainHour = Integer.parseInt(outTime.substring(0, 1)) - nowTime.get(Calendar.HOUR);// �����ִ�
						// �ð������
						System.out.println(remainHour + "�ð�����");
						if (remainHour > 1) {
							JOptionPane.showMessageDialog(null, "������ ��ǿ����ð� 1�ð������� �����մϴ�.");

						} else {

							String[] str = { "����", "���" };
							String extensionHour = (nowTime.get(Calendar.HOUR) + 4) + "��" + nowTime.get(Calendar.MINUTE)
									+ "��" + nowTime.get(Calendar.SECOND) + "��";
							int extensionNum = (int) jf.memInfo[index].get(6) + 1;
							String seatlocation = (String) jf.memInfo[index].get(7);
							String inHour = (String) jf.memInfo[index].get(4);

							int choice = JOptionPane.showOptionDialog(null,
									"���� �Ͻðڽ��ϱ�?\n�¼�:" + jf.memInfo[index].get(7) + "\n�Խǽð�:" + jf.memInfo[index].get(4)
											+ "\n\n ��ǿ����ð� : " + extensionHour + "\n\n ����Ƚ�� :" + extensionNum
											+ "\n*��� ������ ��ǽð� 1�ð� ������ ����\n",

									"����", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str,
									str[0]);
							if (choice == JOptionPane.YES_OPTION) {
								jf.memInfo[index].set(5, extensionHour);// ��ǽð�
																		// ����
								jf.memInfo[index].set(6, extensionNum);// ����Ƚ��
																		// ����
								JOptionPane.showMessageDialog(null,
										"����" + extensionNum + "ȸ �ϼ̽��ϴ�. (" + extensionNum + "/3)");

								jta.setText("\n\n " + jf.memInfo[index].get(0) + " ȸ���� �湮�� ȯ���մϴ�.\n\n �¼� : "
										+ jf.memInfo[index].get(7) + "\n\n�Խǽð� : " + jf.memInfo[index].get(4)
										+ "\n\n ��ǿ����ð� : " + jf.memInfo[index].get(5) + "\n\n ����Ƚ�� :"
										+ jf.memInfo[index].get(6));

							} else {
								return;
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "������ 3ȸ������ �����մϴ�.(" + jf.memInfo[index].get(6) + "/3)");

				}
			}
				break;
			}
		}
	}

	public void setCheck(boolean loginCheck, boolean usedSeat) {
		lcc = new LeftCenControl();
		lcc.setCheck(loginCheck, usedSeat);
		// ���� �α����� ������ ���°���, �¼��� ����������ƴѵ� HashMap���κ���
		// ��� ���θ� �����Ͽ� �¼�Ŭ������ �ѱ��.
	}

	public void setCheck(boolean check) {// �ް����ϱ� �����ε�
		jf.usedSeat.replace(jid.getText(), check);
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
		jid.setVisible(true);
		jid.setText("");
		jpsw.setVisible(true);
		jpsw.setText("");
		login.setVisible(true);
		join.setVisible(true);

	}

	public void getSeatInfo(String nt, String et, String seatLocation, int ExtensionNum) {
		// nowTime �̶�,endTime �� �����´�
		this.nt = nt;
		this.et = et;
		this.seatLocation = seatLocation;
		this.ExtensionNum = ExtensionNum;

		jf.memInfo[index].add(nt);
		jf.memInfo[index].add(et);
		jf.memInfo[index].add(ExtensionNum);
		jf.memInfo[index].add(seatLocation);
		jta.setText("\n\n " + jf.memInfo[index].get(0) + " ȸ���� �湮�� ȯ���մϴ�.\n\n �¼� : " + jf.memInfo[index].get(7)
				+ "\n\n�Խǽð� : " + jf.memInfo[index].get(4) + "\n\n ��ǿ����ð� : " + jf.memInfo[index].get(5) + "\n\n ����Ƚ�� :"
				+ jf.memInfo[index].get(6));
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

class JoinFrame extends JFrame {
	JLabel jlid = new JLabel("ID");
	JLabel jlpw = new JLabel("PW");
	JLabel jlname = new JLabel("Name");
	JLabel jlbirth = new JLabel("Birth");
	static JTextField id = new JTextField();
	JTextField password = new JTextField();
	JTextField name = new JTextField();
	JTextField birth = new JTextField();

	JButton join = new JButton("����");
	JButton cancel = new JButton("���");
	JLayeredPane joinPanel = new JLayeredPane();
	JPanel joinImage = new JPanel();
	static HashMap hsmem = new HashMap<>(); // id�ߺ�ó��
	static HashMap usedSeat = new HashMap(); // �¼� ��� �̻�� üũ
	static ArrayList[] memInfo = new ArrayList[100];// ȸ����������
	static ArrayList memcheck = new ArrayList();
	static int vi = 0;
	LeftPan lp = new LeftPan();

	public void _JoinFrame() {
		setTitle("ȸ������â");
		setLayout(null);

		joinImage.setSize(300, 500);
		joinImage.setOpaque(true);
		joinPanel.setSize(300, 500);
		jlid.setBounds(20, 100, 30, 30);
		id.setBounds(60, 100, 200, 30);
		jlpw.setBounds(15, 150, 30, 30);
		password.setBounds(60, 150, 200, 30);
		jlname.setBounds(15, 200, 40, 30);
		name.setBounds(60, 200, 200, 30);
		jlbirth.setBounds(15, 250, 30, 30);
		birth.setBounds(60, 250, 200, 30);

		join.setBounds(40, 380, 100, 30);
		cancel.setBounds(145, 380, 100, 30);
		joinPanel.add(jlid);
		joinPanel.add(id);
		joinPanel.add(jlpw);
		joinPanel.add(password);
		joinPanel.add(jlname);
		joinPanel.add(name);
		joinPanel.add(jlbirth);
		joinPanel.add(birth);
		joinPanel.add(join);
		joinPanel.add(cancel);
		add(joinPanel);
		add(joinImage);
		setBounds(100, 60, 300, 500);
		setVisible(true);
		setResizable(false);
		join.addActionListener(new EventHandler());
		cancel.addActionListener(new EventHandler());

	}

	class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String eventcheck = e.getActionCommand();

			if (eventcheck.equals("����") && id.getText() != null & id.getText() != "\t" && id.getText() != "\n"
					&& id.getText() != "") {
				if (!memcheck.contains(id.getText())) {

					memcheck.add(id.getText());
					memInfo[vi] = new ArrayList<>();
					memInfo[vi].add(id.getText());
					memInfo[vi].add(password.getText());
					memInfo[vi].add(name.getText());
					memInfo[vi].add(birth.getText());

					usedSeat.put(id.getText(), false);
					// System.out.println(usedSeat.get(id.getText()) +
					// "asdfasdf")
					hsmem.put(id.getText(), vi);

					vi++;
					JOptionPane.showMessageDialog(null, "������ ���ϵ帳�ϴ�.");
					setVisible(false);

				}

			}

		}

	}
}
