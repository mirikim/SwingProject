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

public class LeftPan {
	// asdfasdf
	JLayeredPane LeftLayeredPane = new JLayeredPane();
	LeftImage LeftImage = new LeftImage();
	static JTextArea jta = new JTextArea();
	JLabel lid = new JLabel("ID :");
	static JTextField jid = new JTextField(20);
	JLabel lpsw = new JLabel("PW :");
	JTextField jpsw = new JTextField(20);
	JButton login = new JButton("로그인");
	JButton join = new JButton("회원가입");
	JButton logout = new JButton("로그아웃");
	JButton extension = new JButton("연장");
	JButton Out = new JButton("퇴실");
	JButton move = new JButton("이동");
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
		jid.setBounds(60, 250, 200, 20);
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
		LeftLayeredPane.add(jid);
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
			case "로그인": {
				login();
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
				CheckOut();
				break;
			case "연장":
				ExtendOutTime();
				break;
			case "이동":
				Move();
				break;
			}
		}
	}

	public void login() {
		lcc = new LeftCenControl();
		jf = new JoinFrame();
		String logintext = "";
		if (jf.memcheck.contains(jid.getText())) {
			index = (int) jf.hsmem.get(jid.getText());
			if (jpsw.getText().equals(jf.memInfo[index].get(1))) {
				if (jf.memcheck.contains(jf.id.getText())) {
					index = jf.memcheck.indexOf(jf.id.getText());
				}
				jf.id.setText("");
				JOptionPane.showMessageDialog(null, "로그인 성공");
				jid.setVisible(false);
				lid.setVisible(false);
				jpsw.setVisible(false);
				lpsw.setVisible(false);
				login.setVisible(false);
				join.setVisible(false);
				jta.setVisible(true);

				if (jf.memInfo[index].size() != 4) {
					logintext += "좌석 위치 : " +jf.memInfo[index].get(8)+ jf.memInfo[index].get(7) + "\n입실 시간 : " + jf.memInfo[index].get(4)
							+ "\n\n 퇴실예정시간 : " + jf.memInfo[index].get(5) + "\n\n 연장횟수 :" + jf.memInfo[index].get(6);

				}
				jta.setText("\n\n  " + jid.getText() + " 회원님 환영합니다.\n\n" + logintext);
			//	jta.setOpaque(false);
				logout.setVisible(true);
				extension.setVisible(true);
				Out.setVisible(true);
				move.setVisible(true);
				setCheck(true, (boolean) jf.usedSeat.get(jid.getText()));
				// 로그인 체크, 좌석 사용체크 좌석클래스에 좌석을 사용중인 아이디인지 아닌지 초기값을 보냄

			}
			// 아이디가 존재하면

		}else if(jid.getText().length()==0||jpsw.getText().length()==0){
			JOptionPane.showMessageDialog(null, "ID와  PW를 입력해주세요.");
		}
		else if (!jf.hsmem.containsKey(jid.getText())) {
			JOptionPane.showMessageDialog(null, "ID 또는 PW가 일치하지 않습니다.");
		}
	}

	public void CheckOut() {

		if (jf.usedSeat.get(jid.getText()).equals(false)) {
			JOptionPane.showMessageDialog(null, "배정받은 좌석이 없으므로 로그아웃합니다.");
			LeftPanClear();// 로그아웃
		} else if (jf.usedSeat.get(jid.getText()).equals(true)) {
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

			for (int i = 8; i > 3; i--) {
				jf.memInfo[index].remove(i);
				// 입실시간 퇴실시간 연장횟수,좌석 삭제

			}
			JOptionPane.showMessageDialog(null, "퇴실합니다.");
			setCheck(false);
			CenPan.label[rowNum][col - 1].setText(row + "열" + col + "석");
			CenPan.label[rowNum][col - 1].setBounds(1, 0, 60, 15);
			LeftPanClear();// 좌석 기록 삭제후 로그아웃

		}
	}

	public void ExtendOutTime() {
		{
			Calendar nowTime = Calendar.getInstance();

			if (jf.memInfo[index].size() < 5) {
				JOptionPane.showMessageDialog(null, "좌석정보가 없습니다. 좌석배정을 한 후 연장이 가능합니다.");
				return;
			} else {
				String outTime = (String) jf.memInfo[index].get(5);
				int remainHour = Integer.parseInt(outTime.substring(0, 1)) - nowTime.get(Calendar.HOUR);// 남아있는
				// 시간만출력
				System.out.println(remainHour + "시간남음");
				if (remainHour > 1) {
					JOptionPane.showMessageDialog(null, "연장은 퇴실예정시간 1시간전부터 가능합니다.");

				} else if (remainHour < 1) {

					String[] str = { "연장", "취소" };
					String extensionHour = (nowTime.get(Calendar.HOUR) + 4) + "시" + nowTime.get(Calendar.MINUTE) + "분"
							+ nowTime.get(Calendar.SECOND) + "초";
					extensionNum = (int) jf.memInfo[index].get(6) + 1;
					// String seatlocation = (String) jf.memInfo[index].get(7);
					// String inHour = (String) jf.memInfo[index].get(4);

					int choice = JOptionPane.showOptionDialog(null,
							"연장 하시겠습니까??\n좌석:"+jf.memInfo[index].get(8) + jf.memInfo[index].get(7) + "\n입실시간:" + jf.memInfo[index].get(4)
									+ "\n\n 퇴실예정시간 : " + extensionHour + "\n\n 연장횟수 :" + extensionNum
									+ "\n*퇴실 연장은 퇴실시간 1시간 전부터 가능\n",

							"선택", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
					if ((int) jf.memInfo[index].get(6) > 2) {
						JOptionPane.showMessageDialog(null, "연장은 3회까지만 가능합니다.(" + jf.memInfo[index].get(6) + "/3)");

					} else if (choice == JOptionPane.YES_OPTION) {
						this.et = extensionHour;
						jf.memInfo[index].set(5, extensionHour);// 퇴실시간
						// 수정
						jf.memInfo[index].set(6, extensionNum);// 연장횟수
						// 수정
						JOptionPane.showMessageDialog(null, "연장" + extensionNum + "회 하셨습니다. (" + extensionNum + "/3)");
						// this.extensionHour =
						// (String)jf.memInfo[index].get(6);

						jta.setText("\n\n " + jf.memInfo[index].get(0) + " 회원님 방문을 환영합니다.\n\n 좌석 : "
								+jf.memInfo[index].get(8)+ jf.memInfo[index].get(7) + "\n\n입실시간 : " + jf.memInfo[index].get(4) + "\n\n 퇴실예정시간 : "
								+ jf.memInfo[index].get(5) + "\n\n 연장횟수 :" + jf.memInfo[index].get(6));

					} else {
						return;
					}
				}
			}
		}
	}

	public void Move() {
		lcc = new LeftCenControl();
		if (jf.usedSeat.get(jid.getText()).equals(false)) {
			JOptionPane.showMessageDialog(null, "배정받은 좌석이 없으니 좌석을 선택하세요.");
			// LeftPanClear();// 로그아웃
		} else if (jf.usedSeat.get(jid.getText()).equals(true)) {
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

			for (int i = 8; i > 3; i--) {
				jf.memInfo[index].remove(i);
				// 입실시간 퇴실시간 연장횟수,좌석 삭제
			}
			JOptionPane.showMessageDialog(null, "이동 합니다.");

			setCheck(false);
			lcc.setCheck(true, false);

			lcc.setMoveCheck(et, true, extensionNum);
			CenPan.label[rowNum][col - 1].setText(row + "열" + col + "석");
			CenPan.label[rowNum][col - 1].setBounds(1, 0, 60, 15);
			// LeftPanClear();// 좌석 기록 삭제후 로그아웃

		}
	}

	public void setCheck(boolean loginCheck, boolean usedSeat) {
		lcc = new LeftCenControl();
		lcc.setCheck(loginCheck, usedSeat);
		// 현재 로그인한 유저의 상태값과, 좌석을 사용중인지아닌데 HashMap으로부터
		// 사용 여부를 추출하여 좌석클래스로 넘긴다.
	}

	public void setCheck(boolean check) {// 햇갈리니까 오버로딩
		jf.usedSeat.replace(jid.getText(), check);
		// 좌석클래스로부터 회원이 좌석을 사용하는지 안하는지 값을 받아와 업데이트 한다.
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
		jid.setVisible(true);
		jid.setText("");
		lid.setVisible(true);
		jpsw.setVisible(true);
		jpsw.setText("");
		lpsw.setVisible(true);
		login.setVisible(true);
		join.setVisible(true);

	}

	public void getSeatInfo(String nt, String et,String readingRoom, String seatLocation, int ExtensionNum) {
		// nowTime 이랑,endTime 을 가져온다
		this.nt = nt;
		this.et = et;
		this.seatLocation = seatLocation;
		this.ExtensionNum = ExtensionNum;
		this.readingRoom=readingRoom;
		jf.memInfo[index].add(nt);
		jf.memInfo[index].add(et);
		jf.memInfo[index].add(ExtensionNum);
		jf.memInfo[index].add(seatLocation);
		jf.memInfo[index].add(readingRoom);
		jta.setText("\n\n " + jf.memInfo[index].get(0) + " 회원님 방문을 환영합니다.\n\n 좌석 : "+jf.memInfo[index].get(8) + jf.memInfo[index].get(7)
				+ "\n\n입실시간 : " + jf.memInfo[index].get(4) + "\n\n 퇴실예정시간 : " + jf.memInfo[index].get(5) + "\n\n 연장횟수 :"
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
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}
		g.drawImage(img, -20, 50, this);
	}

}
