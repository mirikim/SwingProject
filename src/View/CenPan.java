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
	static boolean ok;// 중복 선택 방지용?

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
				seatpan2[i][j].setBackground(Color.CYAN); // 실제 좌석판
				if (j == 0) {
					seatpan2[i][j].setBounds(row, col, 70, 70);
				} else {
					row += 80;
					seatpan2[i][j].setBounds(row, col, 70, 70);
				}
				label[i][j] = new JLabel(rr + "열" + (j + 1) + "석");
				label[i][j].setFont(new Font(null, 0, 10));// 글씨크기
				label[i][j].setBounds(1, 0, 60, 15); // 글씨 텍스트 넣기
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
		int hour = 0;
		// Calendar.HOUR_OF_DAY
		nt = (nowTime.get(Calendar.HOUR_OF_DAY)) + "시" + nowTime.get(Calendar.MINUTE) + "분"
				+ nowTime.get(Calendar.SECOND) + "초";
		if (nowTime.get(Calendar.HOUR_OF_DAY) + 1 >= 24) {
			hour = nowTime.get(Calendar.HOUR_OF_DAY) + 1 - 24;
		} else {
			hour = nowTime.get(Calendar.HOUR_OF_DAY) + 1;
		}
		et = hour + "시" + nowTime.get(Calendar.MINUTE) + "분" + nowTime.get(Calendar.SECOND) + "초";

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
			String[] str = { "입실", "취소" };
			String[] str_move = { "이동", "취소" };
			String seatLocation = label[i][j].getText();
			LeftPan leftPan = new LeftPan();

			if (ok == true) {// ok값은 LeftPan에서 가져옴
				if (label[i][j].getText().equals("좌석 사용중..")) {
					JOptionPane.showMessageDialog(null, "사용중인 좌석입니다.");
				} else {
					timeCheck(); // 현재 시간을 nt,et에 저장
					int choice = JOptionPane.showOptionDialog(null,
							"입실을 하시겠습니까?\n좌석:" + label[i][j].getText() + "\n입실시간:" + nt + "\n퇴실예정시간:" + et
									+ "\n*퇴실 연장은 퇴실시간 1시간 전부터 가능\n",
							"선택", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
					if (choice == JOptionPane.YES_OPTION) {

						label[i][j].setText("좌석 사용중..");
						label[i][j].setLocation(1, 26);

						lcc.setCheck(false);// 좌석 중복선택 방지
						lcc.setTime(nt, et, seatLocation, ExtensionNum);
					} else {
						return;
					}
					leftPan.setCheck(false);
				}
			}
			////// 자리이동/////////////
			else if (ok == false) {
				if (label[i][j].getText().equals("좌석 사용중..")) {
					JOptionPane.showMessageDialog(null, "사용중인 좌석입니다.");
				} else {

					int choice = JOptionPane.showOptionDialog(null,
							"좌석 이동을 하시겠습니까?\n현재좌석:" + leftPan.jf.memInfo[leftPan.index].get(7) + "\n이동좌석:"
									+ label[i][j].getText() + "\n입실시간:" + leftPan.jf.memInfo[leftPan.index].get(4)
									+ "\n퇴실예정시간:" + leftPan.jf.memInfo[leftPan.index].get(5)
									+ "\n*퇴실 연장은 퇴실시간 1시간 전부터 가능\n",
							"선택", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str_move,
							str_move[0]);
					if (choice == JOptionPane.YES_OPTION) {
						////////////////////////////////
						String seat = (String) leftPan.jf.memInfo[leftPan.index].get(7);
						char row = seat.charAt(0);// A,B,C,D....
						int col = Integer.parseInt(seat.charAt(2) + "");// 1열,2열....
						if ('0' <= seat.charAt(3) && seat.charAt(3) <= '9') {

							String resultCol = col + "" + seat.charAt(3) + "";
							// System.out.println(resultCol + "테스트ㅔ틋테스테스테스");
							col = Integer.parseInt(resultCol);
						}

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

						leftPan.jf.memInfo[leftPan.index].set(7, seatLocation);
						// 좌석변경

						JOptionPane.showMessageDialog(null, "좌석을 이동합니다.");
						CenPan.label[rowNum][col - 1].setText(row + "열" + col + "석");
						CenPan.label[rowNum][col - 1].setBounds(1, 0, 60, 15);

						/////////////////////////////////
						label[i][j].setText("좌석 사용중..");
						label[i][j].setLocation(1, 26);
						lcc.setCheck(false);// 좌석 중복선택 방지
						nt = (String) leftPan.jf.memInfo[leftPan.index].get(4);
						et = (String) leftPan.jf.memInfo[leftPan.index].get(5);
						lcc.setTime(nt, et, seatLocation, ExtensionNum);
					} else {
						return;
					}
					leftPan.setCheck(false);
				}
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