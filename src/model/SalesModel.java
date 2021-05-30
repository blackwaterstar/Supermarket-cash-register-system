package model;


import java.util.Vector;

import javax.swing.JDialog;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import controller.AchieveOperation;
import entitise.Sales;
import entitise.Single;



public class SalesModel extends AbstractTableModel{
	private AchieveOperation achieveOperation;
	private Vector<Sales> saleses;
	private  Vector<String> columnNames = null;	//列名
	private Vector<Vector<String>> rowData = null;	//行数据
	
		
	 public SalesModel(String sql,JDialog jd) {
		achieveOperation = new AchieveOperation();
		saleses = achieveOperation.getsaleses(sql);
		int number = 0;
		double all = 0;
		columnNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		columnNames.add("商品名");
		columnNames.add("单价/￥");
		columnNames.add("数量");
		columnNames.add("总价/￥");
		columnNames.add("销售日期");
		for(Sales sales : saleses){
			Vector<String> hang = new Vector<String>();
			hang.add(sales.getCommodity_Name());
			hang.add(sales.getPrice());
			hang.add(sales.getNumber());
			hang.add(sales.getAllprice());
			hang.add(sales.getDate());
			number = number+Integer.parseInt(sales.getNumber());
			all = all+Double.parseDouble(sales.getAllprice());
			rowData.add(hang);
		}
		
		AchieveOperation achieveOperation = new AchieveOperation();
		Vector<String> hang = new Vector<String>();
		hang.add("");
		hang.add("");
		hang.add("总数："+number+"");
		String  str = String.format("%.2f",all);
		all = Double.parseDouble(str);
		hang.add("总价："+all+"");
		hang.add("");
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
