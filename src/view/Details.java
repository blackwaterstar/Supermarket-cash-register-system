package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.CommodityModel;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

//详细信息界面
public class Details extends JDialog{
	
    private JLabel commodity_Id_Label;  
    private JLabel commodity_Name_Label;	
    private JLabel shelves_Label;	
    private JLabel type_Label;	
    private JLabel price_Label;	
    private JLabel number_Label;	
    private JButton confirm_Button;	
    private JTextField shelves_Text;	
    private JTextField number_Text;
    private JTextField commodity_IdText;	
    private JTextField commodity_NameText;	
    private JTextField type_Text;
    private JTextField price_Text;	
    private CommodityModel cModel;	//传入的数据模型
    private JDialog jd;	//当前窗口。

public Details(JDialog owner, String title, boolean modal, int rowNum,CommodityModel sm){
	super(owner, title, modal);
	this.cModel = sm;	//传入数据模型
	this.jd = this;
	this.setSize(350,429);	//设置窗体大小。
	this.setLayout(null);	//设置空布局。
	
	commodity_Id_Label = new JLabel("商品号");	
	commodity_Id_Label.setBounds(70, 48, 60, 20);	
	this.add(commodity_Id_Label);	
	
	commodity_IdText = new JTextField();
	commodity_IdText.setEditable(false);	//不可编辑
	commodity_IdText.setText(sm.getValueAt(rowNum, 0).toString());	
	commodity_IdText.setBounds(116, 48, 150, 20);
	this.add(commodity_IdText);
	
	commodity_Name_Label = new JLabel("商品名");
	commodity_Name_Label.setBounds(70, 88, 60, 20);
	this.add(commodity_Name_Label);
	
	
	commodity_NameText = new JTextField();
	commodity_NameText.setBounds(116, 88, 150, 20);
	commodity_NameText.setEditable(false);//不可编辑
	commodity_NameText.setText(sm.getValueAt(rowNum, 1).toString());	
	this.add(commodity_NameText);
	
	shelves_Label = new JLabel("货架号");
	shelves_Label.setBounds(70, 128, 60, 20);
	this.add(shelves_Label);
	
	shelves_Text = new JTextField();
	shelves_Text.setBounds(116, 128, 150, 20);
	shelves_Text.setEditable(false);//不可编辑
	shelves_Text.setText(sm.getValueAt(rowNum, 2).toString());	
	this.add(shelves_Text);
	
	price_Label = new JLabel("类别");
	price_Label.setBounds(78, 168, 30, 20);
	this.add(price_Label);
	
	price_Text = new JTextField();
	price_Text.setBounds(116, 168, 150, 20);
	price_Text.setEditable(false);
	price_Text.setText(sm.getValueAt(rowNum, 3).toString());	//设置年级并显示
	this.add(price_Text);
	
	type_Label = new JLabel("单价");
	type_Label.setBounds(78, 208, 30, 20);
	this.add(type_Label);
	
	type_Text = new JTextField();
	type_Text.setBounds(116, 208, 150, 20);
	type_Text.setEditable(false);
	type_Text.setText(sm.getValueAt(rowNum, 4).toString());
	this.add(type_Text);

	number_Label = new JLabel("数量");
	number_Label.setBounds(78, 248, 30, 20);
	this.add(number_Label);
	
	number_Text = new JTextField();
	number_Text.setEditable(false);
	number_Text.setBounds(116, 248, 150, 20);
	number_Text.setText(sm.getValueAt(rowNum, 5).toString());
	this.add(number_Text);
	
	confirm_Button = new JButton("确认");
	//注册"确定"按钮事件监听
	confirm_Button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jd.dispose();//关闭当前窗口
		}
	});
	confirm_Button.setBounds(150, 330, 60, 25);
	this.add(confirm_Button);
	this.setLocationRelativeTo(null);//设置窗口居中
	this.setResizable(false);
	this.setVisible(true);
	}
}
