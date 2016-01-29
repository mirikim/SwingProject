package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CenTabPan implements ActionListener, ChangeListener {
	static JTabbedPane readingRoom = new JTabbedPane();
	CenPan cen1 = new CenPan();
	CenPan cen2 = new CenPan();
	static String curPaneTitle;

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		int curSelIndex = readingRoom.getSelectedIndex();
		curPaneTitle = readingRoom.getTitleAt(curSelIndex);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public JTabbedPane SetCenPansTab() {

		readingRoom = new JTabbedPane();
		readingRoom.setBounds(347, 47, 1000, 685);
		readingRoom.setVisible(true);
		readingRoom.setOpaque(false);
		// readingRoom.setBackground(Color.CYAN);
		readingRoom.addChangeListener(this);

		readingRoom.addTab("1������", cen1.SetCenPan());
		readingRoom.addTab("2������", cen2.SetCenPan());

		// readingRoom.setIconAt(0, new ImageIcon("image/�̸� ����.png"));
		// readingRoom.setIconAt(1, new ImageIcon("image/�̸� ����.png"));

		return readingRoom;

	}
}