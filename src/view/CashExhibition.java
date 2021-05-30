package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import entitise.Single;
import model.CommodityModel;
import model.SingleModel;
import tools.CreateSql;

public class CashExhibition extends JDialog {
	private JPanel jp1, jp2, jp3; // 面板。
	private JLabel query_Label; // 标签。
	private JButton query_Button; // "查询"按钮。
	private JComboBox query_List; // "查询"选项。
	private JButton preciseQuery_Button; // "组合查询"按钮。
	private JButton buy_Button; // "详细信息"按钮。
	private JButton look_Button;
	private JTextField query_Text; // "查询"文本域。
	private JTable jt; // 表格。
	private JScrollPane jsp; // 滚动条。
	private JDialog jd; // 当前窗口。
	private CommodityModel commodityModel; // 学生数据模型
	private SingleModel singleModel; // 学生数据模型
	private static Vector<String> query_Option;

	static {
		query_Option = new Vector<String>();
		query_Option.add("全部");
		query_Option.add("商品号");
		query_Option.add("商品名");
		query_Option.add("货架号");
		query_Option.add("类别");
		query_Option.add("单价/￥");
		query_Option.add("数量");
	}

	public CashExhibition(JFrame owner, String title, boolean modal) {
		super(owner, title, modal);
		this.jd = this;
		Container c = this.getContentPane();

		jp1 = new JPanel();
		query_Label = new JLabel("请输入查询信息:");
		jp1.add(query_Label);

		query_Text = new JTextField(10);
		jp1.add(query_Text);

		query_List = new JComboBox<String>(query_Option);
		jp1.add(query_List);

		query_Button = new JButton("查询");
		// 注册"查询"按钮事件监听
		query_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String str = query_Text.getText().trim(); // 查询内容
				String option = query_List.getSelectedItem().toString(); // 查询选项
				String sql = CreateSql.getCommodity_Sql(str, option); // 获得sql语句
				commodityModel = new CommodityModel(sql, jd);// 构建新的数据模型类，并更新
				jt.setModel(commodityModel);// 更新Jtable
			}
		});
		jp1.add(query_Button);

		preciseQuery_Button = new JButton("组合查询");
		// 注册"多条件查询"按钮事件监听
		preciseQuery_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ConditionsQuery conditionsQuery = new ConditionsQuery(jd, "组合查询", true, jt);

			}
		});

		jp1.add(preciseQuery_Button);
		c.add(jp1, BorderLayout.NORTH); // 添加面板

		jp2 = new JPanel();
		jt = new JTable();
		String sql = CreateSql.getCommodity_Sql(null, "全部");// 查询全部内容
		commodityModel = new CommodityModel(sql, jd);// 构建新的数据模型类，并更新
		jt.setModel(commodityModel);

		jsp = new JScrollPane(jt);
		jp2.add(jsp);
		c.add(jp2, BorderLayout.CENTER); // 添加面板

		jp3 = new JPanel();
		buy_Button = new JButton("购买");
		// 注册"修改信息"按钮事件监听
		buy_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowNum = jt.getSelectedRow();
				if (rowNum == -1) {
					JOptionPane.showMessageDialog(jd, "请选择一行！", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Cash modify = new Cash(jd, "添加购买", true, rowNum, commodityModel);
				// 更新
				// 构建新的数据模型类，并更新
				String sql = CreateSql.getCommodity_Sql(null, "全部");// 查询全部内容
				commodityModel = new CommodityModel(sql, jd);// 构建新的数据模型类，并更新
				jt.setModel(commodityModel);
			}
		});
		jp3.add(buy_Button);

		look_Button = new JButton("查看购物车");
		look_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jd.dispose();
				ShoppingCart shoppingCart = new ShoppingCart(jd, "购物车", true);
				
				// 更新
				// 构建新的数据模型类，并更新
//				String sql = CreateSql.getSingle_Sql(null, "全部");// 查询全部内容
//				singleModel = new SingleModel(sql, jd);// 构建新的数据模型类，并更新
//				jt.setModel(singleModel);
			}
		});
		jp3.add(look_Button);
		c.add(jp3, BorderLayout.SOUTH);

		this.setSize(600, 540);
		this.setResizable(false);
		this.setLocationRelativeTo(null);// 设置窗口居中
		this.setVisible(true);
	}
}
