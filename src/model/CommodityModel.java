package model;

import controller.AchieveOperation;
import entitise.Commodity;

import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
//rowData用来存放行数据
//columnNames存放列名
public class CommodityModel extends AbstractTableModel {
	private AchieveOperation achieveOperation;
	private Vector<Commodity> commodities;
	private  Vector<String> columnNames = null;	//列名
	private Vector<Vector<String>> rowData = null;	//行数据
	
		
	 public CommodityModel(String sql,JDialog jd) {
		achieveOperation = new AchieveOperation();
		commodities = achieveOperation.getCommodities(sql);
		columnNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		columnNames.add("商品号");
		columnNames.add("商品名");
		columnNames.add("货架号");
		columnNames.add("类别");
		columnNames.add("单价/￥");
		columnNames.add("数量");
		for(Commodity commodity : commodities){
			Vector<String> hang = new Vector<String>();
			hang.add(String.valueOf(commodity.getCommodity_Id()));
			hang.add(commodity.getCommodity_Name());
			hang.add(commodity.getShelves());
			hang.add(commodity.getType());
			hang.add(String.valueOf(commodity.getPrice()));
			hang.add(String.valueOf(commodity.getNumber()));
		
			rowData.add(hang);
		}
		if(getRowCount()!=0){
		//	JOptionPane.showMessageDialog(jd, "一共有"+getRowCount()+"条记录！");
			return ;
		}else{
			JOptionPane.showMessageDialog(jd, "没有任何记录！");
			return ;
		}
	}
	
	//得到共有多少行
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return this.rowData.size();
		}
		//得到共有多少列
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return this.columnNames.size();
		}
		//得到某行某列的数据
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
		}
		
		//重写方法 getColumnName
		@Override  
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			return (String)this.columnNames.get(column);
		}

}
