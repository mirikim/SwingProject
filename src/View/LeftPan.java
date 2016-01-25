package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LeftPan {

	LeftImage LeftImage = new LeftImage();
	JTextArea jta = new JTextArea("로그인이 되셨습니다.");
	JTextField jid = new JTextField(20);
	JTextField jpsw = new JTextField(20);
	JButton login = new JButton("로그인");
	JButton join = new JButton("회원가입");
	Label str = new Label();
	static boolean ok = false;
	int oknum=0;
	String id = "asdf";
	String pwsd = "1111";

	public JLayeredPane SetLeftPan() {
		JLayeredPane LeftLayeredPane = new JLayeredPane();
		System.out.println(str.getText());
		// System.out.println(str.getText()+"!!!!!!!!!!!!!!!!!!!!!!");
		LeftLayeredPane.setBounds(0, 0, 350, 800);
		LeftLayeredPane.setLayout(null);
		LeftImage.setBounds(0, 0, 350, 800);
		jta.setBounds(0, 100, 350, 250);
		jta.setBackground(Color.white);
		jid.setBounds(60, 250, 200, 20);
		jpsw.setBounds(60, 300, 200, 20);
		login.setBounds(55, 350, 100, 30);
		join.setBounds(160, 350, 100, 30);

		str.addPropertyChangeListener(new RenewEventHandler());

		jpsw.addActionListener(new EventHandler());
		jid.addActionListener(new EventHandler());
		join.addActionListener(new EventHandler());
		login.addActionListener(new EventHandler());
		LeftLayeredPane.add(jid);
		LeftLayeredPane.add(jpsw);
		LeftLayeredPane.add(login);
		LeftLayeredPane.add(join);
		LeftLayeredPane.add(jta);
		LeftLayeredPane.add(LeftImage);
		LeftLayeredPane.add(str);
		str.setVisible(false);
		jta.setVisible(false);
		System.out.println(str.getText());
		return LeftLayeredPane;
	}

	class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (jid.getText().equals(id) && jpsw.getText().equals(pwsd)) {
				System.out.println(jid.getText());
				System.out.println(jpsw.getText());
				jid.setVisible(false);
				jpsw.setVisible(false);
				login.setVisible(false);
				join.setVisible(false);
				jta.setVisible(true);
				setCheck(true);
			}

		}

	}

	public class RenewEventHandler implements ActionListener, PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			// TODO Auto-generated method stub
			if (str.getText() == 1 + "") {
				System.out.println(str.getText() + "22222222222222");
				JOptionPane.showConfirmDialog(null, oknum);
				// SetLeftPan().setVisible(false);
				jid.setText("");
				jpsw.setText("");
				jid.setVisible(true);
				jpsw.setVisible(true);
				login.setVisible(true);
				join.setVisible(true);
				jta.setVisible(false);
				setCheck(false);

			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

	void setCheck(boolean b) {
		this.ok = b;
	}

	boolean getCheck() {
		return ok;
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