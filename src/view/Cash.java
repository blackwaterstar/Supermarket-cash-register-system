package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.AchieveOperation;
import entitise.Commodity;
import entitise.Single;
import model.CommodityModel;


public class Cash extends JDialog {
	private JButton modify_Button; // "修改"按钮。
	private JButton cancel_Button; // "取消"按钮。
	private JTextField typeText; // "专业"选项。
	private JTextField shelvesText; // "所属院系"选项。
	private JLabel student_ID; // "学号"标签。
	private JLabel student_Name; // "姓名"标签。
	private JLabel price_Label; // "班级"标签。
	private JLabel number_Label_Label; // "年级标签"。
	private JLabel typeLabel; // "专业"标签。
	private JLabel shelvesLabel; // "所属院系"标签。
	private JTextField student_IDText; // "学号"文本域。
	private JTextField student_NameText; // "姓名"文本域。
	private JTextField priceText; // "班级"选项。
	private JTextField number_Text; // "年级"选项。
	private JDialog jd; // 当前窗口。
	private AchieveOperation achieveOperation; // 数据库业务处理对象
	private CommodityModel cm; // 学生数据模型对象

	public Cash(JDialog owner, String title, boolean modal, int rowNum, CommodityModel sm) {
		super(owner, title, modal);
		achieveOperation = new AchieveOperation(); // 创建数据库业务处理对象

		// shelves = achieveOperation.getAllShelves(); //获得所有院系
		// type = achieveOperation.getAllType(); //获得所有的专业
		this.jd = this;
		this.cm = sm;
		this.setSize(350, 429); // 设置窗体大小。
		this.setLayout(null); // 设置空布局。
		// 获取信息
		String commodity_id = sm.getValueAt(rowNum, 0).toString();
		String commodity_name = sm.getValueAt(rowNum, 1).toString();
		String shelves = sm.getValueAt(rowNum, 2).toString();
		String type = sm.getValueAt(rowNum, 3).toString();
		String price = sm.getValueAt(rowNum, 4).toString();
		String commodity_number = sm.getValueAt(rowNum, 5).toString();
		
		student_ID = new JLabel("商品号:");
		student_ID.setBounds(68, 48, 60, 20);
		this.add(student_ID);

		student_IDText = new JTextField();
		student_IDText.setBounds(116, 48, 150, 20);
		student_IDText.setText(commodity_id); 
		student_IDText.setEditable(false);
		this.add(student_IDText);

		student_Name = new JLabel("商品名:");
		student_Name.setBounds(68, 88, 60, 20);
		this.add(student_Name);

		student_NameText = new JTextField();
		student_NameText.setBounds(116, 88, 150, 20);
		student_NameText.setText(commodity_name); // 
		student_NameText.setEditable(false);
		this.add(student_NameText);

		shelvesLabel = new JLabel("货架号:");
		shelvesLabel.setBounds(68, 128, 60, 20);
		this.add(shelvesLabel);

		shelvesText = new JTextField();
		shelvesText.setText(shelves);
		shelvesText.setBounds(116, 128, 150, 20);
		shelvesText.setEditable(false);
		this.add(shelvesText);

		typeLabel = new JLabel("类别:");
		typeLabel.setBounds(78, 168, 60, 20);
		this.add(typeLabel);

		typeText = new JTextField();
		typeText.setText(type);
		typeText.setBounds(116, 168, 150, 20);
		typeText.setEditable(false);
		this.add(typeText);

		price_Label = new JLabel("单价:");
		price_Label.setBounds(78, 208, 60, 20);
		this.add(price_Label);

		priceText = new JTextField();
		priceText.setBounds(116, 208, 150, 20);
		priceText.setText(price);
		priceText.setEditable(false);
		this.add(priceText);

		number_Label_Label = new JLabel("购买的数量");
		number_Label_Label.setBounds(38, 248, 100, 20);
		this.add(number_Label_Label);

		number_Text = new JTextField();
		number_Text.setBounds(116, 248, 150, 20);
		this.add(number_Text);

		modify_Button = new JButton("添加购物车");
		modify_Button.setBounds(40, 330, 100, 25);
		
		// 按钮事件监听
		modify_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String number = number_Text.getText().trim();
				Single singel = new Single();
				singel.setCommodity_Name(commodity_name);
				singel.setPrice(price);
				singel.setNumber(number);
				if (Integer.parseInt(commodity_number)<Integer.parseInt(number)) {
					JOptionPane.showMessageDialog(jd, "库存不足！无法购买", "", JOptionPane.WARNING_MESSAGE);
					return;
				} 
				if (achieveOperation.addSingle(singel)) {
					JOptionPane.showMessageDialog(jd, "添加成功！");
					jd.dispose(); // 关闭当前窗口
					return;
				}
				else {
					JOptionPane.showMessageDialog(jd, "添加失败！", "", JOptionPane.WARNING_MESSAGE);
					jd.dispose(); // 关闭当前窗口
					return;
				}
			}

		});
		this.add(modify_Button);

		cancel_Button = new JButton("取消");
		cancel_Button.setBounds(230, 330, 60, 25);
		// 注册"取消"按钮事件监听
		cancel_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jd.dispose();

			}
		});
		this.add(cancel_Button);

		this.setLocationRelativeTo(null);// 设置窗口居中
		this.setResizable(false);
		this.setVisible(true);
	}

}
