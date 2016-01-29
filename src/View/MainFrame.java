package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CenPan CenterPan = new CenPan();
	TopPan TopPan = new TopPan();
	LeftPan LeftPan = new LeftPan();
	CenTabPan CenterTab = new CenTabPan();
	boolean ok = false;
	// 로그인여부 값 받아올때 쓸것.

	public static void main(String[] args) {
		new MainFrame();
	}

	public MainFrame() {
		setTitle("도서관 좌석 관리 프로그램");
		//setSize(1345, 750);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(TopPan.SetTopPan(), BorderLayout.NORTH);
		this.add(LeftPan.SetLeftPan(), BorderLayout.WEST);
		this.add(CenterTab.SetCenPansTab(), BorderLayout.CENTER);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
	
		int w = 1345;
		int h = 750;
		int x = (screenSize.width - w) / 2;
		int y = (screenSize.height - h) / 2;

		setBounds(x, y, w, h);
		

		setResizable(false);
		setVisible(true);
	}

}


