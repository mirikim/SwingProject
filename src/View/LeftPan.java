package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

			if (command.equals("로그인")) {
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
						jta.setText(jid.getText() + " 님 환영합니다.\n\n" + logintext);
						logout.setVisible(true);
						extension.setVisible(true);
						Out.setVisible(true);
						setCheck(true);
					}
					// 아이디가 존재하면

				} else if (!jf.hsmem.containsKey(jid.getText())) {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 일치하지 않습니다.");
				}
			} else if (command.equals("회원가입")) {
				jf = new JoinFrame();
				jf._JoinFrame();
			}

			if (command.equals("로그아웃")) {

				LeftPanClear();

			}
			// if (command.equals("퇴실")) {
			// if (jf.memInfo[index].size() < 5) {
			//
			// }
			// if (jf.memInfo[index].size() > 4) {
			// for (int i = 4; i < 7; i++) {
			// jf.memInfo[index].remove(i);
			//
			// // 자리..설정해야됨
			// // 입실시간 퇴실시간 연장횟수 삭제
			//
			// }
			// LeftPanClear();// 퇴실후 로그아웃
			// }
			// }
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
