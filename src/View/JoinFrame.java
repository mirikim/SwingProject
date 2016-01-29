package View;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinFrame extends Frame {
	JLabel jlid = new JLabel("ID");
	JLabel jlpw = new JLabel("PW");
	JLabel jlname = new JLabel("Name");
	JLabel jlbirth = new JLabel("Birth");
	static JTextField id = new JTextField();
	JTextField password = new JTextField();
	JTextField name = new JTextField();
	JTextField birth = new JTextField();

	JButton join = new JButton("����");
	JButton cancel = new JButton("���");
	JLayeredPane joinPanel = new JLayeredPane();
	JPanel joinImage = new JPanel();
	static HashMap hsmem = new HashMap(); // id�ߺ�ó��
	static HashMap usedSeat = new HashMap(); // �¼� ��� �̻�� üũ
	static ArrayList[] memInfo = new ArrayList[100];// ȸ����������
	static ArrayList memcheck = new ArrayList();
	static int vi = 0;
	LeftPan lp = new LeftPan();

	public void _JoinFrame() {
		setTitle("ȸ������");
		setLayout(null);

		joinImage.setSize(300, 500);
		joinImage.setOpaque(true);
		joinPanel.setSize(300, 500);
		jlid.setBounds(20, 100, 30, 30);
		id.setBounds(60, 100, 200, 30);
		jlpw.setBounds(15, 150, 30, 30);
		password.setBounds(60, 150, 200, 30);
		jlname.setBounds(15, 200, 40, 30);
		name.setBounds(60, 200, 200, 30);
		jlbirth.setBounds(15, 250, 30, 30);
		birth.setBounds(60, 250, 200, 30);

		join.setBounds(40, 380, 100, 30);
		cancel.setBounds(145, 380, 100, 30);
		joinPanel.add(jlid);
		joinPanel.add(id);
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

			if (eventcheck.equals("����") && id.getText() != null & id.getText() != "\t" && id.getText() != "\n"
					&& id.getText() != "") {
				if (!memcheck.contains(id.getText())) {

					memcheck.add(id.getText());
					memInfo[vi] = new ArrayList<>();
					memInfo[vi].add(id.getText());
					memInfo[vi].add(password.getText());
					memInfo[vi].add(name.getText());
					memInfo[vi].add(birth.getText());

					usedSeat.put(id.getText(), false);
					// System.out.println(usedSeat.get(id.getText()) +
					// "asdfasdf")
					hsmem.put(id.getText(), vi);

					vi++;
					JOptionPane.showMessageDialog(null, "������ ���ϵ帳�ϴ�.");

					setVisible(false);
				} else if (memcheck.contains(id.getText())) {
					JOptionPane.showMessageDialog(null, "������� ID�Դϴ�.");
				}

			} else {
				setVisible(false);
				return;
			}

		}

	}
}
