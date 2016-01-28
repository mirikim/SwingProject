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

import Control.LeftCenControl;

public class CenPan extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CenImage cenImage = new CenImage(); // 이미지를 불러오는 외부 클래스
	JPanel[][] seatpan2 = new JPanel[6][12];// 좌석의 백그라운드 이미지가 들어가는 부분
	public static JLabel[][] label = new JLabel[6][12];// 각 좌석의 표시줄
	JLayeredPane CenPanLayered = new JLayeredPane();// 버튼,텍스트 등 배치 판낼
	Calendar nowTime;// 캘린더 선언 신규 타입받아올경우 다시 초기화해야함 그래서 선언
	static String inTime = "";// 입실시간
	static String outTime = "";// 퇴실시간
	static int ExtensionNum = 0;// 연장횟수
	LeftCenControl lcc;// Control 클래스 선언
	static String nt; // nowTime
	static String et; // endTime
	static boolean LoginCheck;// 로그인 체크
	static boolean SeatCheck; // 유저의 좌성사용여부 가져온다.
	static int SeatCount1 = 0; // 총좌석
	static int SeatCount2 = 72; // 남은좌석
	JLabel SeatInfo = new JLabel();

	public JLayeredPane SetCenPan() {
		CenPanLayered.setBounds(350, 50, 1050, 750);// 사이즈 조정
		CenPanLayered.setLayout(null);// SetBounds로 직접 위치를 지정하므로 레이아웃은 null
		CenPanLayered.setBackground(Color.black); // 테스트용 컬러지정,,,, 의미없음
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
				seatpan2[i][j].setBackground(Color.CYAN); // 실제 좌석판
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
				label[i][j].setBounds(1, 0, 60, 15); // 텍스트 넣기
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
		 * Control 클래스로부터 클릭을해도되는지 안되는지 값을 받아옴
		 */
		this.LoginCheck = LoginCheck;
		this.SeatCheck = seatCheck;
	}

	public void timeCheck() {

		nowTime = Calendar.getInstance();
		nt = (nowTime.get(Calendar.HOUR)) + "시" + nowTime.get(Calendar.MINUTE) + "분" + nowTime.get(Calendar.SECOND)
				+ "초";
		et = (nowTime.get(Calendar.HOUR) + 1) + "시" + nowTime.get(Calendar.MINUTE) + "분" + nowTime.get(Calendar.SECOND)
				+ "초";

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

			// 레퍼런스의 초기화를 다시한다.
			String[] str = { "입실", "취소" };
			String seatLocation = label[i][j].getText();

			if (LoginCheck == true && SeatCheck == false) {
				// 로그인이 되었고, 좌석 미사용중이면 좌석 배정 처리
				timeCheck(); // 현재 시간, 종료시간값을 받아온다.
				int choice = JOptionPane.showOptionDialog(null,
						"입실을 하시겠습니까?\n좌석:" + label[i][j].getText() + "\n입실시간:" + nt + "\n퇴실예정시간:" + et
								+ "\n*퇴실 연장은 퇴실시간 1시간 전부터 가능\n",
						"선택", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
				if (choice == JOptionPane.YES_OPTION) {

					label[i][j].setText("좌석 사용중..");
					label[i][j].setLocation(1, 26);
					SeatCount2--;
					lcc.setCheck(true);
					// 회원클래스의 좌석사용여부에 대한 HashMap 업데이트용
					SeatCheck = true; // 중복 설정방지!
					lcc.setTime(nt, et, seatLocation, ExtensionNum);
					// 좌석 선택시 초기값을 Control클래스를 통해 회원클래스로 넘긴다.
					System.out.println("얘는 얘스");
				} else if (choice == JOptionPane.NO_OPTION) {
					System.out.println("얘는 노");
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
			SeatInfo.setText("[ 남은 좌석:" + cp.SeatCount2 + "  /  총 좌석 :" + cp.SeatCount1 + " ]");
		}
	}
}

class CenImage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img = null; // 이미지아직 안넣음
	//
	// @Override
	// public void paint(Graphics g) {
	// try {
	// img = ImageIO.read(new File("image/rectangle_blue_purple.jpg"));
	// } catch (IOException e) {
	// System.out.println("이미지 불러오기 실패");
	// System.exit(0);
	// }
	//
	// g.drawImage(img, 0, 0, 1050, 1500, null, this);
	// }
}