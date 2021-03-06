package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.LeftCenControl;

public class ManagerView {
	// LeftCenControl lcc = new LeftCenControl();
	JFrame jFrame1 = new JFrame("회원목록");
	JFrame jFrame2 = new JFrame("이용자목록");
	static String columnNames1[] = { "아이디", "패스워드", "이름", "생일" };
	String columnNames2[] = { "아이디", "패스워드", "이름", "생일", "입실시간", "퇴실시간", "연장횟수", "좌석위치" };
	Vector vinfo = new Vector<>();

	// DefaultTableModel을 선언하고 데이터 담기
	static DefaultTableModel defaultTableModel1 = new DefaultTableModel(null, columnNames1);
	DefaultTableModel defaultTableModel2 = new DefaultTableModel(null, columnNames2);

	// JTable에 DefaultTableModel을 담기
	JTable jTable1 = new JTable(defaultTableModel1);
	JTable jTable2 = new JTable(defaultTableModel2);

	// JScrollPane에 JTable을 담기
	JScrollPane jScollPane1 = new JScrollPane(jTable1);
	JScrollPane jScollPane2 = new JScrollPane(jTable2);
	JButton buttonRemove = new JButton("강퇴");
	int delRow = 0;
	String delId = "";
	static Vector[] memList;
	static int mindex; // 추가 순서 지키기
	static HashMap usedSeat;
	static boolean plug = true;
	LeftCenControl lcc;
	private Vector vc;

	public void _MemberList() { // 생성자 아님
		jFrame1.getContentPane().add(jScollPane1);

		// for (int i = 0; i < mindex; i++) {
		// defaultTableModel1.addRow(memList[i]);
		//
		// }

		// 행과 열 갯수 구하기
		System.out.println(defaultTableModel1.getRowCount() + "몇줄?");
		// System.out.println(defaultTableModel1.getColumnCount());

		// 컬럼(열)의 index는 0부터 시작한다!!
		// System.out.println(defaultTableModel1.getColumnName(0));

		// 0행을 삭제하면 제목행을 제외하고 첫째행을 삭제한다!!
		// efaultTableModel.removeRow(0);

		// 값을 얻어올 때도 0부터 index가 시작된다는 것에 주의한다!!
		// System.out.println(defaultTableModel.getValueAt(2, 2));

		// 특정 좌표의 값을 바꾸는 것은 setValueAt()
		// defaultTableModel.setValueAt("5000", 2, 2);

		// 테이블에 Row를 미리 선택한 상태로 만들기!
		// jTable.setRowSelectionInterval(1, 1);

		jFrame1.setSize(500, 300);
		jFrame1.setVisible(true);

	}

	public void _UsedMemberList() {
		// 이용자 목록은 클릭시마다 재업데이트
		jFrame2.add(jScollPane2, BorderLayout.CENTER);
		jFrame2.add(buttonRemove, BorderLayout.SOUTH);
		if (defaultTableModel2.getRowCount() > 0) {
			for (int i = defaultTableModel2.getRowCount() - 1; i >= 0; i--) {
				defaultTableModel2.removeRow(i);
			}
		}

		for (int i = 0; i <= mindex; i++) {
			vc = new Vector<>();
			if (usedSeat.get(memList[i].get(0)).equals(true)) {
				for (int j = 0; j < memList[i].size(); j++) {
					if (j != 4) {
						vc.add(memList[i].get(j));
					}

				}
				defaultTableModel2.addRow(vc);
			}
		}
		jTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				delRow = jTable2.getSelectedRow();
				delId = (String) jTable2.getValueAt(delRow, 0);

			}
		});
		buttonRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					defaultTableModel2.removeRow(delRow);
					lcc = new LeftCenControl();
					lcc.delMemberList(delId);

				} catch (ArrayIndexOutOfBoundsException e2) {
					System.out.println("남은 데이터가 읎데용");
				}

			}
		});
		jTable2.setRowSelectionInterval(0, 0);

		System.out.println(defaultTableModel2.getRowCount() + "로우카운트");
		jFrame2.setSize(500, 300);
		jFrame2.pack();
		jFrame2.setVisible(true);

	}

	public static void main(String[] args) {
		new ManagerView();
	}

	public void memListup(Vector[] memInfo, int index, HashMap usedSeat) {
		this.memList = memInfo;
		this.mindex = index;
		this.usedSeat = usedSeat;
		defaultTableModel1.addRow(memList[mindex]);
		// 기본 회원 리스트는 회원가입시 업데이트
	}

}
