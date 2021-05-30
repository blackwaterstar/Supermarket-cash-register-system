package model;


import java.util.Vector;

import javax.swing.JDialog;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import controller.AchieveOperation;
import entitise.Single;



public class SingleModel extends AbstractTableModel{
	private AchieveOperation achieveOperation;
	private Vector<Single> singles;
	private  Vector<String> columnNames = null;	//列名
	private Vector<Vector<String>> rowData = null;	//行数据
	
		
	 public SingleModel(String sql,JDialog jd) {
		achieveOperation = new AchieveOperation();
		singles = achieveOperation.getSingles(sql);
		
		columnNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		columnNames.add("商品名");
		columnNames.add("单价/￥");
		columnNames.add("数量");
		columnNames.add("总价/￥");
		for(Single single : singles){
			Vector<String> hang = new Vector<String>();
			hang.add(single.getCommodity_Name());
			hang.add(single.getPrice());
			hang.add(String.valueOf(single.getNumber()));
			hang.add(String.valueOf(single.getAllprice()));
			rowData.add(hang);
		}
		
		AchieveOperation achieveOperation = new AchieveOperation();
		Vector<String> hang = new Vector<String>();
		hang.add("");
		hang.add("");
		hang.add("总数："+achieveOperation.getAllnumber());
		hang.add("总费用："+achieveOperation.getAllprice());
		rowData.add(hang);
		if(getRowCount()!=0){
			//JOptionPane.showMessageDialog(jd, "一共有"+getRowCount()+"条记录！");
			return ;
		}else{
			JOptionPane.showMessageDialog(jd, "没有任何记录！");
			return ;
		}
	}
	
	//得到共有多少行
		public int getRowCount() {
			// TODO Auto-generated method stub
			return this.rowData.size();
		}
		//得到共有多少列
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return this.columnNames.size();
		}
		//得到某行某列的数据
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
		}
		
		//重写方法 getColumnName
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			return (String)this.columnNames.get(column);
		}

}
