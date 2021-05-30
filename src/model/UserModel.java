package model;

import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import controller.AchieveOperation;
import entitise.User;


public class UserModel extends AbstractTableModel{
	private AchieveOperation achieveOperation;
	private Vector<User> users;
	private  Vector<String> columnNames = null;	//列名
	private Vector<Vector<String>> rowData = null;	//行数据
	
		
	 public UserModel(String sql,JDialog jd) {
		achieveOperation = new AchieveOperation();
		users = achieveOperation.getUsers(sql);
		
		columnNames = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		columnNames.add("用户名");
		columnNames.add("密码");
		columnNames.add("登录状况");
		columnNames.add("级别");
		for(User user : users){
			Vector<String> hang = new Vector<String>();
			hang.add(user.getUsername());
			hang.add(user.getPassword());
			hang.add(String.valueOf(user.getIsLogin()));
			hang.add(String.valueOf(user.getLevel()));
			
			rowData.add(hang);
		}
		if(getRowCount()!=0){
			JOptionPane.showMessageDialog(jd, "一共有"+getRowCount()+"条记录！");
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
