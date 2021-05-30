package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Pay extends JDialog {

	private JDialog jd; // 当前窗口。
	private JTextField number_labelText;
	private double allprice=0;

	public Pay(JDialog owner, String title, boolean modal) {
		super(owner, title, modal);
		this.jd = this;
		AchieveOperation achieveOperation = new AchieveOperation(); // 创建数据库业务处理对象
		this.setSize(350, 400); // 设置窗体大小。
		this.setLayout(null); // 设置空布局。

		JLabel menber_Id_label = new JLabel("会员号:");
		menber_Id_label.setBounds(48, 48, 80, 20);
		this.add(menber_Id_label);

		JTextField member_Id_Text = new JTextField();
		member_Id_Text.setBounds(116, 48, 150, 20);
		this.add(member_Id_Text);

		JButton add_Button = new JButton("支付");
		add_Button.setBounds(70, 168, 60, 25);
		String string = achieveOperation.getAllprice();
		if (!string.equals(null)) {
			allprice = Double.parseDouble(string);
		}
		else {
			allprice = 0;
		}
		// 按钮事件监听
		add_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (member_Id_Text.getText().trim().equals("") || member_Id_Text.getText().trim().equals(null)) {
					if (achieveOperation.addSales()) {
						if (achieveOperation.deleteSingles()) {
							JOptionPane.showMessageDialog(jd, "支付成功，支付金额为：" + allprice + "", "普通买家", JOptionPane.WARNING_MESSAGE);
							jd.dispose();
							CashExhibition ce = new CashExhibition(null, "修改商品信息", true);
							return;
						}
					}
					
				}
				if (achieveOperation.getMember(member_Id_Text.getText().trim())) {
					if (achieveOperation.addSales1()) {
						if (achieveOperation.getmember_sales(member_Id_Text.getText().trim())) {
							if (achieveOperation.deleteSingles()) {
								JOptionPane.showMessageDialog(jd, "支付成功，支付金额为：" + (allprice * 0.95) + "", "会员打95折",
										JOptionPane.WARNING_MESSAGE);
								jd.dispose();
								CashExhibition ce = new CashExhibition(null, "收银模块", true);
								return;
							}	
						}					
					}				
				}
			}
		});
		this.add(add_Button);

		JButton cancel_Button = new JButton("取消");
		cancel_Button.setBounds(220, 168, 60, 25);
		// 注册"取消"按钮事件监听
		cancel_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jd.dispose();
				ShoppingCart shoppingCart = new ShoppingCart(jd, "购物车", true);
			}
		});
		this.add(cancel_Button);

		JButton newmember_Button = new JButton("添加新会员");
		newmember_Button.setBounds(120, 108, 100, 25);
		// 注册"取消"按钮事件监听
		newmember_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = null;
				if (allprice < 200) {
					JOptionPane.showMessageDialog(jd, "金额不足200，不能申请会员", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (allprice >= 200) {
					name = JOptionPane.showInputDialog(jd, "请输入会员名:", "会员申请", JOptionPane.PLAIN_MESSAGE);
					if(name==null||name.equals("")) {
						return;
					}
				}
				if (achieveOperation.addMember(name)) {
					JOptionPane.showMessageDialog(jd, "会员添加成功" + achieveOperation.getMember_id(name), "",
							JOptionPane.WARNING_MESSAGE);
					// JOptionPane.showMessageDialog(jd,
					// "会员号为："+achieveOperation.getMember_id(name), "",
					// JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(jd, "会员添加失败", "", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		this.add(newmember_Button);

		this.setLocationRelativeTo(null);// 设置窗口居中
		this.setResizable(false);
		this.setVisible(true);
	}

}
