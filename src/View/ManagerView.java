package View;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.LeftCenControl;

public class ManagerView {
	// LeftCenControl lcc = new LeftCenControl();
	JFrame jFrame1 = new JFrame("ȸ�����");
	JFrame jFrame2 = new JFrame("�̿��ڸ��");
	static String columnNames1[] = { "���̵�", "�н�����", "�̸�", "����" };
	String columnNames2[] = { "���̵�", "�н�����", "�̸�", "����", "�Խǽð�", "��ǽð�", "����Ƚ��", "�¼���ġ" };
	Vector vinfo = new Vector<>();

	// DefaultTableModel�� �����ϰ� ������ ���
	static DefaultTableModel defaultTableModel1 = new DefaultTableModel(null, columnNames1);
	DefaultTableModel defaultTableModel2 = new DefaultTableModel(null, columnNames2);

	// JTable�� DefaultTableModel�� ���
	JTable jTable1 = new JTable(defaultTableModel1);
	JTable jTable2 = new JTable(defaultTableModel2);

	// JScrollPane�� JTable�� ���
	JScrollPane jScollPane1 = new JScrollPane(jTable1);
	JScrollPane jScollPane2 = new JScrollPane(jTable2);
	static Vector[] memList;
	static int mindex; // �߰� ���� ��Ű��
	static HashMap usedSeat;
	static boolean plug = true;

	public void _MemberList() { // ������ �ƴ�
		jFrame1.add(jScollPane1);

//		 for (int i = 0; i < mindex; i++) {
//		 defaultTableModel1.addRow(memList[i]);
//
//		 }

		// ��� �� ���� ���ϱ�
		System.out.println(defaultTableModel1.getRowCount() + "����?");
		// System.out.println(defaultTableModel1.getColumnCount());

		// �÷�(��)�� index�� 0���� �����Ѵ�!!
		// System.out.println(defaultTableModel1.getColumnName(0));

		// 0���� �����ϸ� �������� �����ϰ� ù°���� �����Ѵ�!!
		// efaultTableModel.removeRow(0);

		// ���� ���� ���� 0���� index�� ���۵ȴٴ� �Ϳ� �����Ѵ�!!
		// System.out.println(defaultTableModel.getValueAt(2, 2));

		// Ư�� ��ǥ�� ���� �ٲٴ� ���� setValueAt()
		// defaultTableModel.setValueAt("5000", 2, 2);

		// ���̺� Row�� �̸� ������ ���·� �����!
		// jTable.setRowSelectionInterval(1, 1);

		jFrame1.setSize(500, 300);
		jFrame1.setVisible(true);

	}

	public void _UsedMemberList() {
		//�̿��� ����� Ŭ���ø���  �������Ʈ
		jFrame2.add(jScollPane2);

		if (defaultTableModel2.getRowCount() > 0) {
			for (int i = defaultTableModel2.getRowCount()-1; i >= 0; i--) {
				defaultTableModel2.removeRow(i);
			}
		}

		for (int i = 0; i <= mindex; i++) {
			if(usedSeat.get(memList[i].get(0)).equals(false))
			{
			defaultTableModel2.addRow(memList[i]);
			}
		}
		
		System.out.println(defaultTableModel2.getRowCount());
		jFrame2.setSize(500, 300);
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
		//�⺻ ȸ�� ����Ʈ�� ȸ�����Խ� ������Ʈ
	}

}
