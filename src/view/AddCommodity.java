package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.AchieveOperation;
import entitise.Commodity;

public class AddCommodity extends JDialog {
	private JDialog jd; // 当前窗口。
	private Vector<String> types;
	private JComboBox type_Box;
	private JComboBox shelves_Box;
	private JTextField price_labelText;
	private JTextField number_labelText;
	HashMap<String, String> all_Shelves;
	HashMap<String, String> all_Type;

	public AddCommodity(JFrame owner, String title, boolean modal) {
		super(owner, title, modal);
		this.jd = this;
		AchieveOperation achieveOperation = new AchieveOperation(); // 创建数据库业务处理对象
		all_Shelves = achieveOperation.getAllShelves();
		all_Type = achieveOperation.getAllType();
		this.setSize(350, 400); // 设置窗体大小。
		this.setLayout(null); // 设置空布局。

		JLabel name_label = new JLabel("商品名:");
		name_label.setBounds(48, 48, 60, 20);
		this.add(name_label);

		JTextField name_labelText = new JTextField();
		name_labelText.setBounds(116, 48, 150, 20);
		this.add(name_labelText);

		name_labelText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// String name = name_labelText.getText().trim();
				// Commodity commodity = new Commodity();
				// commodity = achieveOperation.checkLife(name);
				// shelves_Box.addItem(commodity.getShelves());
				// shelves_Box.getItemAt(0);
				// type_Box.addItem(commodity.getType());
				// type_Box.getItemAt(0);
				// price_labelText.setText(commodity.getPrice()+"");
				//
			}
		});

		JLabel shelves_label = new JLabel("货架:");
		shelves_label.setBounds(48, 88, 60, 20);
		this.add(shelves_label);

		shelves_Box = new JComboBox(all_Shelves.keySet().toArray());// 获得键的集合
		// 注册"院系"选项事件监听
		shelves_Box.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
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
		shelves_Box.setBounds(116, 88, 150, 20);
		this.add(shelves_Box);

		JLabel type_Label = new JLabel("类别:");
		type_Label.setBounds(48, 128, 60, 20);
		this.add(type_Label);

		type_Box = new JComboBox(new String[] { "" });// 防止空指针异常
		// 注册"专业"选项事件监听
		type_Box.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
		type_Box.setBounds(116, 128, 150, 20);
		this.add(type_Box);

		JLabel price_Label = new JLabel("单价:");
		price_Label.setBounds(48, 168, 60, 20);
		this.add(price_Label);

		price_labelText = new JTextField();
		price_labelText.setBounds(116, 168, 150, 20);
		this.add(price_labelText);

		JLabel number_Label = new JLabel("添加数量:");
		number_Label.setBounds(48, 208, 60, 20);
		this.add(number_Label);

		number_labelText = new JTextField();
		number_labelText.setBounds(116, 208, 150, 20);
		this.add(number_labelText);

		JButton add_Button = new JButton("添加");
		add_Button.setBounds(70, 280, 60, 25);

		// 按钮事件监听
		add_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Commodity commodity = new Commodity();
				int commodity_Id;
				String commodity_Name = name_labelText.getText().trim();
				String shelves = shelves_Box.getSelectedItem().toString();
				String goods_type = type_Box.getSelectedItem().toString();
				String price = price_labelText.getText().trim();
				String number = number_labelText.getText().trim();
				// 数据校验部分
				if (commodity_Name.equals("")) {
					JOptionPane.showMessageDialog(jd, "商品名不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (shelves.equals("")) {
					JOptionPane.showMessageDialog(jd, "货架号不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (goods_type.equals("")) {
					JOptionPane.showMessageDialog(jd, "类别不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (price_labelText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(jd, "价格不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (shelves_Box.getSelectedItem() == null) { // 先检查再用
					JOptionPane.showMessageDialog(jd, "货架号不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (number_labelText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(jd, "数量不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (type_Box.getSelectedItem() == null) { // 先检查再用
					JOptionPane.showMessageDialog(jd, "类别不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				commodity.setCommodity_Name(commodity_Name);
				commodity.setShelves(shelves);
				commodity.setType(goods_type);
				commodity.setPrice(price);
				commodity.setNumber(number);
				if (achieveOperation.addCommodity(commodity)) {
					JOptionPane.showMessageDialog(jd, "添加成功！");
					jd.dispose(); // 关闭当前窗口
					return;
				} else {
					JOptionPane.showMessageDialog(jd, "添加失败！", "", JOptionPane.WARNING_MESSAGE);
					jd.dispose(); // 关闭当前窗口
					return;
				}

			}
		});
		this.add(add_Button);

		JButton cancel_Button = new JButton("取消");
		cancel_Button.setBounds(230, 280, 60, 25);
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
