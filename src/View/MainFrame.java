package View;

import View.CenPan;
import View.LeftPan;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CenPan CenterPan = new CenPan();
	TopPan TopPan = new TopPan();
	LeftPan LeftPan = new LeftPan();
	boolean ok = false;
	// 로그인여부 값 받아올때 쓸것.

	public static void main(String[] args) {
		new MainFrame();
	}

	public MainFrame() {
		setTitle("도서관 좌석 관리 프로그램");
		setSize(1345, 750);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(TopPan.SetTopPan(), BorderLayout.NORTH);
		this.add(LeftPan.SetLeftPan(), BorderLayout.WEST);
		this.add(CenterPan.SetCenPan(), BorderLayout.CENTER);

		setResizable(false);
		setVisible(true);
	}

}


