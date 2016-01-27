package View;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
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
	JTextField jid = new JTextField(20);
	JLabel lpsw = new JLabel("PW :");
	JTextField jpsw = new JTextField(20);
	JButton login = new JButton("로그인");
	JButton join = new JButton("회원가입");
	JButton logout = new JButton("로그아웃");
	JButton extension = new JButton("연장");
	JButton Out = new JButton("퇴실");
	static boolean ok = false;
	String id = "asdf";
	String pwsd = "1111";
	JoinFrame jf;
	LeftCenControl lcc;
	static int index = 0;

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
			case "로그인": {

				jf = new JoinFrame();

				if (jf.memcheck.contains(jid.getText())) {
					index = (int) jf.hsmem.get(jid.getText());
					if (jpsw.getText().equals(jf.memInfo[index].get(1))) {
						if (jf.memcheck.contains(jf.id.getText())) {
							index = jf.memcheck.indexOf(jf.id.getText());
						} else {
							JOptionPane.showMessageDialog(null, "꺼져");
						}
						JOptionPane.showMessageDialog(null, "로그인 성공");
						jid.setVisible(false);
						jpsw.setVisible(false);
						login.setVisible(false);
						join.setVisible(false);
						jta.setVisible(true);
						String logintext = "";
						System.out.println(jf.memInfo[index].size());
						if (jf.memInfo[index].size() != 4) {
							logintext += jf.memInfo[index].get(4) + "\n\n 퇴실예정시간 : " + jf.memInfo[index].get(5)
									+ "\n\n 연장횟수 :" + jf.memInfo[index].get(6);

						}
						jta.setText("\n\n " + jid.getText() + " 회원님 환영합니다.\n\n" + logintext);
						logout.setVisible(true);
						extension.setVisible(true);
						Out.setVisible(true);
						setCheck(true);
					}
					// 아이디가 존재하면

				} else if (!jf.hsmem.containsKey(jid.getText())) {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 일치하지 않습니다.");
				}
			}
				break;
			case "회원가입":
				jf = new JoinFrame();
				jf._JoinFrame();

				break;
			case "로그아웃":

				JOptionPane.showMessageDialog(null, "로그아웃합니다.");
				LeftPanClear();

				break;
			case "퇴실":
				// 배정받은 좌석없으면 바로 로그아웃
				if (jf.memInfo[index].size() < 5) {
					JOptionPane.showMessageDialog(null, "배정받은 좌석이 없으므로 로그아웃합니다.");
					LeftPanClear();// 로그아웃
				}
				if (jf.memInfo[index].size() > 4) {
					String seat = (String) jf.memInfo[index].get(7);
					char row = seat.charAt(0);// A,B,C,D....
					int col = Integer.parseInt(seat.charAt(2) + "");// 1열,2열....
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
						// 입실시간 퇴실시간 연장횟수,좌석 삭제

					}
					JOptionPane.showMessageDialog(null, "퇴실합니다.");

					System.out.println(jf.memInfo[index]);
					CenPan.label[rowNum][col - 1].setText(row + "열" + col + "석");
					CenPan.label[rowNum][col - 1].setBounds(1, 0, 60, 15);
					LeftPanClear();// 좌석 기록 삭제후 로그아웃

				}
				break;
			case "연장": {
				if ((int) jf.memInfo[index].get(6) < 3) {
					if (jf.memInfo[index].size() < 5) {
						JOptionPane.showMessageDialog(null, "좌석정보가 없습니다. 좌석배정을 한 후 연장이 가능합니다.");
						return;
					} else {
						String outTime = (String) jf.memInfo[index].get(5);
						int remainHour = Integer.parseInt(outTime.substring(0, 1));// 남아있는
																					// 시간만출력
						// System.out.println(remainHour+"시간남음");
						if (remainHour > 1) {
							JOptionPane.showMessageDialog(null, "연장은 퇴실예정시간 1시간전부터 가능합니다.");

						} else {
							Calendar nowTime = Calendar.getInstance();
							String[] str = { "연장", "취소" };
							String extensionHour = (nowTime.get(Calendar.HOUR) + 1) + "시" + nowTime.get(Calendar.MINUTE)
									+ "분" + nowTime.get(Calendar.SECOND) + "초";
							int extensionNum = (int) jf.memInfo[index].get(6) + 1;
							String seatlocation = (String) jf.memInfo[index].get(7);
							String inHour = (String) jf.memInfo[index].get(4);

							int choice = JOptionPane.showOptionDialog(null,
									"연장 하시겠습니까?\n좌석:" + jf.memInfo[index].get(7) + "\n입실시간:" + jf.memInfo[index].get(4)
											+ "\n\n 퇴실예정시간 : " + extensionHour + "\n\n 연장횟수 :" + extensionNum
											+ "\n*퇴실 연장은 퇴실시간 1시간 전부터 가능\n",

									"선택", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str,
									str[0]);
							if (choice == JOptionPane.YES_OPTION) {

								jf.memInfo[index].set(5, extensionHour);// 퇴실시간
																		// 수정
								jf.memInfo[index].set(6, extensionNum);// 연장횟수
																		// 수정
								JOptionPane.showMessageDialog(null,
										"연장" + extensionNum + "회 하셨습니다. (" + extensionNum + "/3)");

								jta.setText("\n\n " + jf.memInfo[index].get(0) + " 회원님 방문을 환영합니다.\n\n 좌석 : "
										+ jf.memInfo[index].get(7) + "\n\n입실시간 : " + jf.memInfo[index].get(4)
										+ "\n\n 퇴실예정시간 : " + jf.memInfo[index].get(5) + "\n\n 연장횟수 :"
										+ jf.memInfo[index].get(6));

								Frame confirmFrame = new Frame();
								confirmFrame.setSize(200, 200);
								Panel confirmPanel = new Panel();
								Label testest = new Label("Test");
								confirmPanel.add(testest);
								confirmFrame.add(confirmPanel);

							} else {
								return;
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "연장은 3회까지만 가능합니다.(" + jf.memInfo[index].get(6) + "/3)");

				}
			}
				break;
			}
		}
	}

	public void setIndex(int index) {
		this.index = index;

	}

	void setCheck(boolean b) {
		this.ok = b;
	}

	boolean getCheck() {
		return ok;
	}

	public void LeftPanClear() {
		System.out.println("호출됫다 LeftPanClear");

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
			System.out.println("이미지 불러오기 실패");
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

	JButton join = new JButton("가입");
	JButton cancel = new JButton("취소");
	JLayeredPane joinPanel = new JLayeredPane();
	JPanel joinImage = new JPanel();
	static HashMap hsmem = new HashMap<>(); // id중복처리
	static ArrayList[] memInfo = new ArrayList[100];// 회원가입정보
	static ArrayList memcheck = new ArrayList();
	static int vi = 0;
	LeftPan lp = new LeftPan();

	public void _JoinFrame() {
		setTitle("회원가입창");
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

			if (eventcheck.equals("가입") && id.getText() != null & id.getText() != "\t" && id.getText() != "\n"
					&& id.getText() != "") {
				if (!memcheck.contains(id.getText())) {

					memcheck.add(id.getText());
					memInfo[vi] = new ArrayList<>();
					memInfo[vi].add(id.getText());
					memInfo[vi].add(password.getText());
					memInfo[vi].add(name.getText());
					memInfo[vi].add(birth.getText());

					hsmem.put(id.getText(), vi);

					vi++;
					JOptionPane.showMessageDialog(null, "가입을 축하드립니다.");
					setVisible(false);

					int index = (int) hsmem.get(id.getText());
					for (int i = 0; i < memInfo[index].size(); i++) {
						System.out.print(" memInfo[index] : " + memInfo[index].get(i));
					}
					System.out.println();

				}

			}

		}

	}
}
