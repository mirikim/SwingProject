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
	CenImage cenImage = new CenImage(); // 이미지를 불러오는 외부 클래스

	JPanel[][] seatpan2 = new JPanel[6][12];// 좌석의 백그라운드 이미지가 들어가는 부분
	public static JLabel[][] label = new JLabel[6][12];// 각 좌석의 표시줄
	// SeatImage SeatImage[][] = new SeatImage[6][12];
	SeatImage SeatImage = new SeatImage();
	JLayeredPane CenPanLayered = new JLayeredPane();// 버튼,텍스트 등 배치 판낼
	Calendar nowTime;// 캘린더 선언 신규 타입받아올경우 다시 초기화해야함 그래서 선언
	// static String inTime = "";// 입실시간
	// static String outTime = "";// 퇴실시간
	static int ExtensionNum = 0;// 연장횟수
	LeftCenControl lcc;// Control 클래스 선언
	static String nt; // nowTime
	static String et; // endTime
	static boolean LoginCheck;// 로그인 체크
	static boolean SeatCheck; // 유저의 좌성사용여부 가져온다.
	static int SeatCount1 = 0; // 총좌석
	static int SeatCount2 = 72; // 남은좌석
	JLabel SeatInfo = new JLabel();
	static String extensionHour = "";
	static boolean moveCheck = false;
	static int LExtensionNum = 0;

	public JLayeredPane SetCenPan() {
		CenPanLayered.setSize(1000, 750);
		// CenPanLayered.setBounds(350, 50, 1050, 750);// 사이즈 조정
		CenPanLayered.setLayout(null);// SetBounds로 직접 위치를 지정하므로 레이아웃은 null
		// CenPanLayered.setBackground(Color.black); // 테스트용 컬러지정,,,, 의미없음
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
				// seatpan2[i][j].setBackground(cenImage2); // 실제 좌석판
				if (j == 0) {
					seatpan2[i][j].setBounds(row, col, 70, 70);

				} else {
					row += 80;
					seatpan2[i][j].setBounds(row, col, 70, 70);
				}
				label[i][j] = new JLabel(rr + "열" + (j + 1) + "석");
				// 라벨에 좌석의 위치를 지정한다.
				label[i][j].setFont(new Font(null, 0, 10));// 글씨크기
				// 폰트 나 글시크기 변경시 사용
				label[i][j].setBounds(10, 2, 60, 15); // 텍스트 넣기
				label[i][j].setForeground(Color.red);

				// SeatImage[i][j].setBounds(0, 0, 70, 70);
				// SeatImage[i][j].setOpaque(true);// 다하고 true로바꾸기

				seatpan2[i][j].add(label[i][j]);
				// seatpan2[i][j].add(SeatImage[i][j]);
				seatpan2[i][j].addMouseListener(new EventHandler(i, j));
				seatpan2[i][j].setOpaque(true);
				seatpan2[i][j].setVisible(true);
				seatpan2[i][j].add(SeatImage);
				SeatImage.setBounds(0, 0, 70, 70);
				SeatImage.setOpaque(false);// 다하고 true로바꾸기
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
		 * Control 클래스로부터 클릭을해도되는지 안되는지 값을 받아옴
		 */
		this.LoginCheck = LoginCheck;
		this.SeatCheck = seatCheck;
	}

	public void timeCheck() {

		nowTime = Calendar.getInstance();
		nt = (nowTime.get(Calendar.HOUR)) + "시" + nowTime.get(Calendar.MINUTE) + "분" + nowTime.get(Calendar.SECOND)
				+ "초";
		et = (nowTime.get(Calendar.HOUR)) + "시" + (nowTime.get(Calendar.MINUTE) + 1) + "분"
				+ nowTime.get(Calendar.SECOND) + "초";

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
			if (!label[i][j].getText().equals("좌석 사용중..")) {

				if (LoginCheck == true && SeatCheck == false && moveCheck == false) {
					// 로그인이 되었고, 좌석 미사용중이면 좌석 배정 처리
					SeatAssign();
				} else if (LoginCheck == true && SeatCheck == true && moveCheck == true) {

					SeatMove();
				}
			} else {
				// 좌석사용중일경우 이벤트발생x
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

			// 레퍼런스의 초기화를 다시한다.
			String[] str = { "입실", "취소" };
			String seatLocation = label[i][j].getText();
			String readingRoom = CenTabPan.curPaneTitle;

			// 로그인이 되었고, 좌석 미사용중이면 좌석 배정 처리
			timeCheck(); // 현재 시간, 종료시간값을 받아온다.

			int choice = JOptionPane.showOptionDialog(null,
					"입실을 하시겠습니까?\n 좌석:" + CenTabPan.curPaneTitle + seatLocation + "\n 입실시간:" + nt + "\n 퇴실예정시간:" + et
							+ "\n *퇴실 연장은 퇴실시간 1시간 전부터 가능\n",
					"선택", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
			if (choice == JOptionPane.YES_OPTION) {
				label[i][j].addMouseListener(null);
				label[i][j].setText("좌석 사용중..");
				label[i][j].setLocation(1, 26);
				SeatCount2--;
				lcc.setCheck(true);
				// 회원클래스의 좌석사용여부에 대한 HashMap 업데이트용
				SeatCheck = true; // 중복 설정방지!
				lcc.setTime(nt, et, readingRoom, seatLocation, ExtensionNum);
				// 좌석 선택시 초기값을 Control클래스를 통해 회원클래스로 넘긴다.
			} else if (choice == JOptionPane.NO_OPTION) {
				lcc.setCheck(false);
			}
		}

		public void SeatMove() {
			lcc = new LeftCenControl();

			String[] str1 = { "이동", "취소" };
			String moveSeatLocation = label[i][j].getText();

			String readingRoom = CenTabPan.curPaneTitle;

			String original_readingRoomd = (String) LeftPan.memInfo[LeftPan.index].get(9);
			String seat = (String) LeftPan.memInfo[LeftPan.index].get(8);

			int choice = JOptionPane.showOptionDialog(null,
					" 이동을 하시겠습니까?\n 현재좌석: " + original_readingRoomd + seat + "\n 이동좌석: " + CenTabPan.curPaneTitle
							+ moveSeatLocation + "\n 입실시간: " + nt + "\n 퇴실예정시간: " + et + "\n *퇴실 연장은 퇴실시간 1시간 전부터 가능\n",
					"선택", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str1, str1[0]);
			if (choice == JOptionPane.YES_OPTION) {

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

				for (int i = 9; i > 4; i--) {
					LeftPan.memInfo[LeftPan.index].remove(i);
					// 입실시간 퇴실시간 연장횟수,좌석 삭제
				}
				SeatCount2++;
				label[i][j].setText("좌석 사용중..");
				label[i][j].setLocation(1, 26);
				if (original_readingRoomd.equals("1열람실")) {
					CenPan.label[rowNum][col - 1].setText(row + "열" + col + "석");
					CenPan.label[rowNum][col - 1].setBounds(1, 0, 60, 15);
					// LeftPanClear();// 좌석 기록 삭제후 로그아웃
				} else if (original_readingRoomd.equals("2열람실")) {
					CenPan2.label[rowNum][col - 1].setText(row + "열" + col + "석");
					CenPan2.label[rowNum][col - 1].setBounds(1, 0, 60, 15);
				}

				SeatCount2--;
				lcc.setCheck(true);
				// 회원클래스의 좌석사용여부에 대한 HashMap 업데이트용
				SeatCheck = true; // 중복 설정방지!
				lcc.setTime(nt, extensionHour, readingRoom, moveSeatLocation, LExtensionNum);

				lcc.setMoveCheck(et, false, LExtensionNum);// 좌석이동후 이동권한 초기화
				// 좌석 선택시 초기값을 Control클래스를 통해 회원클래스로 넘긴다.
			} else if (choice == JOptionPane.NO_OPTION) { // 취소버튼눌를경우
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
			String ThreadTimeCheck = (ThreadTime.get(Calendar.HOUR)) + "시" + ThreadTime.get(Calendar.MINUTE) + "분"
					+ ThreadTime.get(Calendar.SECOND) + "초";
			try {

				Thread.sleep(100);

			} catch (InterruptedException e) {
				return;
			}
			i++;
			SeatInfo.setText("[ 남은 좌석:" + cp.SeatCount2 + "  /  총 좌석 :" + cp.SeatCount1 + " ]");

			for (int t = 0; t < lp.memInfo.length; t++) {
				if (lp.memInfo[t] == null) {
					// System.out.println(ThreadTimeCheck);
				} else if ((lp.memInfo[t] != null)) {
					if (lp.memInfo[t].size() > 6) {
						if (lp.memInfo[t].get(6).equals(ThreadTimeCheck)) {

							System.out.println("현재시간 : " + ThreadTimeCheck + "퇴실예정시간:" + lp.memInfo[t].get(6));

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
	Image img = null; // 이미지아직 안넣음

	@Override
	public void paint(Graphics g) {
		try {
			img = ImageIO.read(new File("image/white.jpg"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
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
	Image img = null; // 이미지아직 안넣음

	@Override
	public void paint(Graphics g) {
		try {
			img = ImageIO.read(new File("image/seat.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		g.drawImage(img, 0, 0, 70, 70, null, this);
	}
}