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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import model.SalesModel;
import tools.CreateSql;
public class SalesRecord extends JDialog{
	private JPanel jp1, jp2, jp3; // 面板。
	private JLabel query_Label; // 标签。
	private JButton query_Button; // "查询"按钮。
	private JComboBox query_List; // "查询"选项。
	private JButton buy_Button; // "详细信息"按钮。
	private JTextField query_Text; // "查询"文本域。
	private JTable jt; // 表格。
	private JScrollPane jsp; // 滚动条。
	private JDialog jd; // 当前窗口。
	private SalesModel salesModel; // 学生数据模型
	private static Vector<String> query_Option;

	static {
		query_Option = new Vector<String>();
		query_Option.add("全部");
		query_Option.add("商品名");
		query_Option.add("单价/￥");
		query_Option.add("数量");
		query_Option.add("总价/￥");
		query_Option.add("销售日期yyyy-mm-dd");
	}

	public SalesRecord(JFrame owner, String title, boolean modal) {
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
				String sql = CreateSql.getSaleses_Sql(str, option); // 获得sql语句
				salesModel = new SalesModel(sql, jd);// 构建新的数据模型类，并更新
				jt.setModel(salesModel);// 更新Jtable
			}
		});
		jp1.add(query_Button);
		c.add(jp1, BorderLayout.NORTH); // 添加面板

		jp2 = new JPanel();
		jt = new JTable();
		String sql = CreateSql.getSaleses_Sql(null, "全部");// 查询全部内容
		salesModel = new SalesModel(sql, jd);// 构建新的数据模型类，并更新
		jt.setModel(salesModel);

		jsp = new JScrollPane(jt);
		jp2.add(jsp);
		c.add(jp2, BorderLayout.CENTER); // 添加面板

		jp3 = new JPanel();
		JLabel date = new JLabel("输入要统计日期/yyyy-mm-dd");
		JTextField dateText = new JTextField(15);
		
		buy_Button = new JButton("统计");
		// 注册按钮事件监听
		buy_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = dateText.getText().trim(); // 查询内容
				String sql = CreateSql.getSalesStatistics_Sql(str); // 获得sql语句
				salesModel = new SalesModel(sql, jd);// 构建新的数据模型类，并更新
				jt.setModel(salesModel);// 更新Jtable
				//dateText.setText("");
			}
		});
		jp3.add(date);
		jp3.add(dateText);
		jp3.add(buy_Button);
		c.add(jp3, BorderLayout.SOUTH);

		this.setSize(600, 540);
		this.setResizable(false);
		this.setLocationRelativeTo(null);// 设置窗口居中
		this.setVisible(true);
	}
}
