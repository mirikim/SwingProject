package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.LeftCenControl;

public class JoinFrame extends JFrame {
	JLabel jlid = new JLabel("ID");
	JLabel jlpw = new JLabel("PW");
	JLabel jlname = new JLabel("Name");
	JLabel jlbirth = new JLabel("Birth");
	static JTextField joinId = new JTextField();
	JTextField password = new JTextField();
	JTextField name = new JTextField();
	JTextField birth = new JTextField();

	JButton join = new JButton("가입");
	JButton cancel = new JButton("취소");
	JLayeredPane joinPanel = new JLayeredPane();
	JPanel joinImage = new JPanel();
	static HashMap hsmem = new HashMap(); // id중복처리
	static HashMap usedSeat = new HashMap(); // 좌석 사용 미사용 체크
	static Vector[] memInfo = new Vector[100];// 회원가입정보
	static ArrayList memcheck = new ArrayList();
	static int vi = 0;
	LeftCenControl lcc;

	public void _JoinFrame() {
		setTitle("회원가입");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		joinImage.setSize(300, 500);
		joinImage.setOpaque(true);
		joinPanel.setSize(300, 500);
		jlid.setBounds(20, 100, 30, 30);
		joinId.setBounds(60, 100, 200, 30);
		jlpw.setBounds(15, 150, 30, 30);
		password.setBounds(60, 150, 200, 30);
		jlname.setBounds(15, 200, 40, 30);
		name.setBounds(60, 200, 200, 30);
		jlbirth.setBounds(15, 250, 30, 30);
		birth.setBounds(60, 250, 200, 30);

		join.setBounds(40, 380, 100, 30);
		cancel.setBounds(145, 380, 100, 30);
		joinPanel.add(jlid);
		joinPanel.add(joinId);
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

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();

		int w = 300;
		int h = 500;
		int x = (screenSize.width - w) / 2;
		int y = (screenSize.height - h) / 2;
		setVisible(true);
		setResizable(false);
		setBounds(x, y, w, h);
		join.addActionListener(new EventHandler());
		cancel.addActionListener(new EventHandler());

	}

	class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String eventcheck = e.getActionCommand();
			lcc = new LeftCenControl();
			if (eventcheck.equals("가입") && joinId.getText() != null & joinId.getText() != "\t"
					&& joinId.getText() != "\n" && joinId.getText() != "") {
				if (!memcheck.contains(joinId.getText())) {

					memcheck.add(joinId.getText());
					memInfo[vi] = new Vector();
					memInfo[vi].add(joinId.getText()); // 0 아이디
					memInfo[vi].add(password.getText()); // 1 비밀번호
					memInfo[vi].add(name.getText()); // 2 이름
					memInfo[vi].add(birth.getText()); // 3 생일
//					 memInfo[vi].add("미사용 중입니다.");
//					 memInfo[vi].add("미사용");
//					 memInfo[vi].add("미사용");
//					 memInfo[vi].add("미사용");
					usedSeat.put(joinId.getText(), false);
					hsmem.put(joinId.getText(), vi);

					vi++;
					JOptionPane.showMessageDialog(null, "가입을 축하드립니다.");

					setVisible(false);

					
					lcc.setMemberList(memInfo,(vi -1),usedSeat);
					// 회원가입한후 회원 리스트를 업데이트한다.
				} else if (memcheck.contains(joinId.getText())) {
					JOptionPane.showMessageDialog(null, "사용중인 ID입니다.");
				}

			} else {
				setVisible(false);
				return;
			}

		}

	}
}
