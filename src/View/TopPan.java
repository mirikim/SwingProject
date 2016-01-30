package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TopPan {
	JLayeredPane TopLayeredPane = new JLayeredPane();
	JLabel Title = new JLabel("00도서관");
	JButton MButton = new JButton("관리자");
	TopImage topImage = new TopImage();
	ManagerView mv = new ManagerView();
	public JLayeredPane SetTopPan() {
		TopLayeredPane.setBounds(0, 0, 1400, 50);
		TopLayeredPane.setLayout(null);
		topImage.setBounds(0, 0, 1400, 50);
		Title.setBounds(650, 15, 80, 20);
		Title.setForeground(Color.white);
		MButton.setBounds(1200, 15, 80, 20);
		MButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("관리자가 등장");
				String[] str = { "회원목록", "이용자목록" };
				String password = JOptionPane.showInputDialog(null, "관리자 비밀번호를 입력하세요.");
		
				if (password.equals("1234")) {
					int choice = JOptionPane.showOptionDialog(null, "모드를 선택하세요", "선택", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
					if (choice == JOptionPane.YES_OPTION) {
						mv._MemberList();
					} else if(choice == JOptionPane.NO_OPTION) {
						mv._UsedMemberList();
					}

				}

			}
		});
		//Title.setFont(bold);
		// TopLayeredPane.add(readingRoom1); // 열람실 버튼
		//TopLayeredPane.add(Title);
		TopLayeredPane.add(MButton);
		TopLayeredPane.add(topImage);
		return TopLayeredPane;

	}
}

class TopImage extends JPanel {
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
		// g.drawImage(img, 300, 0, this);
		g.drawImage(img, 0, 0, 1400, 50, null, this);
	}
}
