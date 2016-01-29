package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class TopPan {
	JLayeredPane TopLayeredPane = new JLayeredPane();
	JLabel Title = new JLabel("00도서관");

	TopImage topImage = new TopImage();

	public JLayeredPane SetTopPan() {
		TopLayeredPane.setBounds(0, 0, 1400, 50);
		TopLayeredPane.setLayout(null);
		topImage.setBounds(0, 0, 1400, 50);
		Title.setBounds(650, 15, 80, 20);
		
		Title.setForeground(Color.white);
		//Title.setFont(bold);
		// TopLayeredPane.add(readingRoom1); // 열람실 버튼
		//TopLayeredPane.add(Title);
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