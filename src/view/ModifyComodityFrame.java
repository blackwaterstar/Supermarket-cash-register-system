package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import model.CommodityModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.AchieveOperation;
import entitise.Commodity;

//详细信息界面
public class ModifyComodityFrame extends JDialog {

	private JLabel commodity_Id_Label;
	private JLabel commodity_Name_Label;
	private JLabel shelves_Label;
	private JLabel type_Label;
	private JLabel price_Label;
	private JLabel number_Label;
	private JButton confirm_Button;
	private JTextField number_Text;
	private JTextField commodity_IdText;
	private JTextField commodity_NameText;
	private JComboBox type_Box;
	private JComboBox shelves_Box;
	private JTextField price_Text;
	private CommodityModel cModel; // 传入的数据模型
	private JDialog jd; // 当前窗口。
	private Vector<String> types;
	HashMap<String, String> all_Shelves;
	HashMap<String, String> all_Type;

	public ModifyComodityFrame(JDialog owner, String title, boolean modal, int rowNum, CommodityModel sm) {
		super(owner, title, modal);
		this.cModel = sm; // 传入数据模型
		this.jd = this;
		this.setSize(350, 429); // 设置窗体大小。
		this.setLayout(null); // 设置空布局。

		AchieveOperation achieveOperation = new AchieveOperation(); // 创建数据库业务处理对象
		all_Shelves = achieveOperation.getAllShelves();
		all_Type = achieveOperation.getAllType();

		commodity_Id_Label = new JLabel("商品号");
		commodity_Id_Label.setBounds(70, 48, 60, 20);
		this.add(commodity_Id_Label);

		commodity_IdText = new JTextField();
		commodity_IdText.setEditable(false); // 不可编辑
		commodity_IdText.setText(sm.getValueAt(rowNum, 0).toString());
		commodity_IdText.setBounds(116, 48, 150, 20);
		this.add(commodity_IdText);

		commodity_Name_Label = new JLabel("商品名");
		commodity_Name_Label.setBounds(70, 88, 60, 20);
		this.add(commodity_Name_Label);

		commodity_NameText = new JTextField();
		commodity_NameText.setBounds(116, 88, 150, 20);
		// commodity_NameText.setEditable(false);//不可编辑
		commodity_NameText.setText(sm.getValueAt(rowNum, 1).toString());
		this.add(commodity_NameText);

		shelves_Label = new JLabel("货架:");
		shelves_Label.setBounds(70, 128, 60, 20);
		this.add(shelves_Label);

		shelves_Box = new JComboBox(all_Shelves.keySet().toArray());// 获得键的集合
		// 注册"院系"选项事件监听
		shelves_Box.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// shelves_Box.setSelectedItem(sm.getValueAt(rowNum, 2).toString());
				type_Box.removeAllItems();// 移除"专业选项"的内容
				String option = shelves_Box.getSelectedItem().toString();// 获得选项名称
				String shelves_id = all_Shelves.get(option); // 获得货架编号
				if (!shelves_id.equals("")) {
					types = achieveOperation.getType(shelves_id); // 获得类别
					for (String s : types) {
						type_Box.addItem(s);
					}
				}

			}
		});
		shelves_Box.setBounds(116, 128, 150, 20);
		this.add(shelves_Box);

		type_Label = new JLabel("类别");
		type_Label.setBounds(78, 168, 30, 20);
		this.add(type_Label);

		type_Box = new JComboBox(new String[] { "" });// 防止空指针异常
		// 注册"专业"选项事件监听
		type_Box.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// type_Box.setSelectedItem(sm.getValueAt(rowNum, 3).toString());
				if (type_Box.getSelectedItem() != null) { // 防止空指针
					if (!type_Box.getSelectedItem().toString().equals("")) {
						if (type_Box.getSelectedItem().toString().equals("")) {
							JOptionPane.showMessageDialog(jd, "货架不能为空", "", JOptionPane.WARNING_MESSAGE);
							type_Box.setSelectedIndex(0); // 设置为空选项
							return;
						}
						String option = type_Box.getSelectedItem().toString();
					}
				}
			}
		});
		type_Box.setBounds(116, 168, 150, 20);
		this.add(type_Box);

		price_Label = new JLabel("价格");
		price_Label.setBounds(78, 208, 30, 20);
		this.add(price_Label);

		price_Text = new JTextField();
		price_Text.setBounds(116, 208, 150, 20);
		// price_Text.setEditable(false);
		price_Text.setText(sm.getValueAt(rowNum, 4).toString()); // 设置年级并显示
		this.add(price_Text);

		number_Label = new JLabel("数量");
		number_Label.setBounds(78, 248, 30, 20);
		this.add(number_Label);

		number_Text = new JTextField();
		// number_Text.setEditable(false);
		number_Text.setBounds(116, 248, 150, 20);
		number_Text.setText(sm.getValueAt(rowNum, 5).toString());
		this.add(number_Text);

		confirm_Button = new JButton("确认");
		// 注册"确定"按钮事件监听
		confirm_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Commodity newComodify = new Commodity();
				newComodify.setCommodity_Id(commodity_IdText.getText().trim());
				newComodify.setCommodity_Name(commodity_NameText.getText().trim());
				newComodify.setShelves(shelves_Box.getSelectedItem().toString());
				newComodify.setType(type_Box.getSelectedItem().toString());
				newComodify.setPrice(price_Text.getText().trim());
				newComodify.setNumber(number_Text.getText().trim());
				if (commodity_NameText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(jd, "商品名不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (shelves_Box.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(jd, "货架号不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (type_Box.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(jd, "类别不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (price_Text.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(jd, "价格不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (shelves_Box.getSelectedItem() == null) { // 先检查再用
					JOptionPane.showMessageDialog(jd, "货架号不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (number_Text.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(jd, "数量不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (type_Box.getSelectedItem() == null) { // 先检查再用
					JOptionPane.showMessageDialog(jd, "类别不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (achieveOperation.updateCommodity(newComodify)) {
					JOptionPane.showMessageDialog(jd, "修改成功", "", JOptionPane.WARNING_MESSAGE);
					jd.dispose();// 关闭当前窗口
				} else {
					JOptionPane.showMessageDialog(jd, "修改失败", "", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		confirm_Button.setBounds(150, 330, 60, 25);
		this.add(confirm_Button);
		this.setLocationRelativeTo(null);// 设置窗口居中
		this.setResizable(false);
		this.setVisible(true);
	}
}
