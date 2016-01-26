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

	class EventHandler implements MouseListener {
		int i = 0;
		int j = 0;

		public EventHandler(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			String nt = (nowTime.get(Calendar.HOUR) + 12) + "시" + nowTime.get(Calendar.MINUTE) + "분"
					+ nowTime.get(Calendar.SECOND) + "초";
			String et = (nowTime.get(Calendar.HOUR) + 15) + "시" + nowTime.get(Calendar.MINUTE) + "분"
					+ nowTime.get(Calendar.SECOND) + "초";
			String[] str = { "입실", "취소" };
			LeftPan leftPan = new LeftPan();

			System.out.println(leftPan.getCheck());
			if (leftPan.getCheck() == true) {

				int choice = JOptionPane.showOptionDialog(null,
						"입실을 하시겠습니까?\n좌석:" + label[i][j].getText() + "\n입실시간:" + nt + "\n퇴실예정시간:" + et
								+ "\n*퇴실 연장은 퇴실시간 1시간 전부터 가능\n",
						"선택", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
				if (choice == JOptionPane.YES_OPTION) {
					inTime = nt;
					outTime = et;
					leftPan.jf.memInfo[0].set(4, inTime);
					leftPan.jf.memInfo[0].set(5, outTime);
					leftPan.jf.memInfo[0].set(6, ExtensionNum);
					leftPan.jta.setText("   " + leftPan.jf.memInfo[0].get(0) + " 회원님 방문을 환영합니다.\n\n 입실시간 : "
							+ leftPan.jf.memInfo[0].get(4) + "\n\n 퇴실예정시간 : " + leftPan.jf.memInfo[0].get(5) + "\n\n 연장횟수 :"
							+leftPan.jf.memInfo[0].get(6));
					
					
					//안바뀐다.
					label[i][j].setText("좌석 사용중..");
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