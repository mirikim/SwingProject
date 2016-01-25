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
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LeftPan {
	JLayeredPane LeftLayeredPane = new JLayeredPane();
	LeftImage LeftImage = new LeftImage();
	JTextArea jta = new JTextArea("�α����� �Ǽ̽��ϴ�.");
	JTextField jid = new JTextField(20);
	JTextField jpsw = new JTextField(20);
	JButton login = new JButton("�α���");
	JButton join = new JButton("ȸ������");
	static boolean ok = false;
	String id = "asdf";
	String pwsd = "1111";
	JoinFrame jf;

	public JLayeredPane SetLeftPan() {
		LeftLayeredPane.setBounds(0, 0, 350, 800);
		LeftLayeredPane.setLayout(null);
		LeftImage.setBounds(0, 0, 350, 800);
		jta.setBounds(0, 100, 350, 250);
		jta.setBackground(Color.white);
		jid.setBounds(60, 250, 200, 20);
		jpsw.setBounds(60, 300, 200, 20);
		login.setBounds(55, 350, 100, 30);
		join.setBounds(160, 350, 100, 30);
		login.addActionListener(new EventHandler());
		join.addActionListener(new EventHandler());
		LeftLayeredPane.add(jid);
		LeftLayeredPane.add(jpsw);
		LeftLayeredPane.add(login);
		LeftLayeredPane.add(join);
		LeftLayeredPane.add(jta);
		LeftLayeredPane.add(LeftImage);
		jta.setVisible(false);

		return LeftLayeredPane;
	}

	class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();

			if (command.equals("�α���")) {
				jf = new JoinFrame();
				int index = 0;
				if (jf.hsmem.containsKey(jid.getText())) {
					index = (int) jf.hsmem.get(jid.getText());
					if (jpsw.getText().equals(jf.memInfo[index].get(1))) {
						JOptionPane.showMessageDialog(null, "�α��� ����");
						jid.setVisible(false);
						jpsw.setVisible(false);
						login.setVisible(false);
						join.setVisible(false);
						jta.setText("" + jf.memInfo[index].get(0) + " ȸ���� �湮�� ȯ���մϴ�.");
						jta.setVisible(true);
						setCheck(true);
					}
					// ���̵� �����ϸ�

				} else if (!jf.hsmem.containsKey(jid.getText())) {
					JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
				System.out.println(jf.hsmem.get(jid.getText()));
				System.out.println(jf.memInfo[0].get(0));
				System.out.println(jf.memInfo[0].get(1));
				System.out.println(jf.memInfo[0].get(2));
				System.out.println(jf.memInfo[0].get(3));

			} else if (command.equals("ȸ������")) {
				jf = new JoinFrame();
				jf._JoinFrame();
			}
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
			System.out.println("�̹��� �ҷ����� ����");
			System.exit(0);
		}
		g.drawImage(img, -20, 50, this);
	}

}

class JoinFrame extends JFrame {
	JTextField id = new JTextField();
	JTextField password = new JTextField();
	JTextField name = new JTextField();
	JTextField birth = new JTextField();

	JButton join = new JButton("����");
	JButton cancel = new JButton("���");
	JLayeredPane joinPanel = new JLayeredPane();
	JPanel joinImage = new JPanel();
	static HashMap hsmem = new HashMap<>();
	static ArrayList[] memInfo = new ArrayList[100];
	static ArrayList memcheck = new ArrayList();
	static int vi = 0;
	LeftPan lp = new LeftPan();;

	public void _JoinFrame() {
		setTitle("ȸ������â");
		setLayout(null);

		joinImage.setSize(300, 500);
		joinImage.setOpaque(true);
		joinPanel.setSize(300, 500);

		id.setBounds(40, 100, 200, 30);
		password.setBounds(40, 150, 200, 30);
		name.setBounds(40, 200, 200, 30);
		birth.setBounds(40, 250, 200, 30);

		join.setBounds(40, 380, 100, 30);
		cancel.setBounds(145, 380, 100, 30);

		joinPanel.add(id);
		joinPanel.add(password);
		joinPanel.add(name);
		joinPanel.add(birth);
		joinPanel.add(join);
		joinPanel.add(cancel);
		add(joinPanel);
		add(joinImage);
		setSize(300, 500);
		setVisible(true);
		join.addActionListener(new EventHandler());
		cancel.addActionListener(new EventHandler());

	}

	class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String eventcheck = e.getActionCommand();

			if (eventcheck.equals("����") && id.getText() != null & id.getText() != "\t" && id.getText() != "\n"
					&& id.getText() != "") {
				if (!memcheck.contains(id.getText())) {

					System.out.println(memcheck.contains(id.getText()));
					memcheck.add(id.getText());
					memInfo[vi] = new ArrayList<>();
					memInfo[vi].add(id.getText());
					memInfo[vi].add(password.getText());
					memInfo[vi].add(name.getText());
					memInfo[vi].add(birth.getText());

					hsmem.put(id.getText(), vi);

					vi++;
					JOptionPane.showMessageDialog(null, "������ ���ϵ帳�ϴ�.");
					setVisible(false);

					int index = (int) hsmem.get(id.getText());
					for (int i = 0; i < memInfo[index].size(); i++) {
						System.out.print(memInfo[index].get(i));
					}
					System.out.println();

				}

			}

		}

	}
}
