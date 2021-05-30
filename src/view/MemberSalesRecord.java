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

import model.MemberSalesModel;
import model.SingleModel;
import tools.CreateSql;

public class MemberSalesRecord extends JDialog{
	private JPanel jp1, jp2, jp3; // 面板。
	private JLabel query_Label; // 标签。
	private JLabel allprice_Label;
	private JButton query_Button; // "查询"按钮。
	private JComboBox query_List; // "查询"选项。
//	private JButton preciseQuery_Button; // "组合查询"按钮。
	private JButton buy_Button; // "详细信息"按钮。
	private JButton look_Button;
	private JTextField query_Text; // "查询"文本域。
	private JTable jt; // 表格。
	private JScrollPane jsp; // 滚动条。
	private JDialog jd; // 当前窗口。
	private MemberSalesModel memberSalesModel; // 学生数据模型
//	private SingleModel singleModel; // 数据模型
	private static Vector<String> query_Option;

	static {
		query_Option = new Vector<String>();
		query_Option.add("全部");
		query_Option.add("会员号");
		query_Option.add("商品名");
		query_Option.add("单价");
		query_Option.add("数量");
		query_Option.add("总价");
	}

	public MemberSalesRecord(JFrame jf2, String title, boolean modal) {
		super(jf2, title, modal);
		this.jd = this;
		JFrame jf = new JFrame();
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
				String sql = CreateSql.getMemberSales_Sql(str, option); // 获得sql语句
				memberSalesModel = new MemberSalesModel(sql, jd);// 构建新的数据模型类，并更新
				jt.setModel(memberSalesModel);// 更新Jtable
			}
		});
		jp1.add(query_Button);
//
//		preciseQuery_Button = new JButton("组合查询");
//		// 注册"多条件查询"按钮事件监听
//		preciseQuery_Button.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ConditionsQuery conditionsQuery = new ConditionsQuery(jd, "组合查询", true, jt);
//
//			}
//		});
//
//		jp1.add(preciseQuery_Button);
		c.add(jp1, BorderLayout.NORTH); // 添加面板

		jp2 = new JPanel();
		jt = new JTable();
		String sql = CreateSql.getMemberSales_Sql(null, "全部");// 查询全部内容
		memberSalesModel = new MemberSalesModel(sql, jd);// 构建新的数据模型类，并更新
		jt.setModel(memberSalesModel);

		jsp = new JScrollPane(jt);
		jp2.add(jsp);
		
		
//		AchieveOperation achieveOperation = new AchieveOperation();
//		allprice_Label = new JLabel();
//		allprice_Label.setFont(new Font("宋体", 1, 20));//“宋体”代表字体，baidu1代表样式(1是粗体，0是平常的）15是字号
//		allprice_Label.setForeground(Color.BLUE);
//		allprice_Label.setText("总费用："+achieveOperation.getAllmunber_price());
//		jp2.add(allprice_Label);
		
		c.add(jp2, BorderLayout.CENTER); // 添加面板

		jp3 = new JPanel();
		
		JLabel id = new JLabel("输入要统计会员号");
		JTextField idText = new JTextField(15);
		
		
		buy_Button = new JButton("统计");
		// 注册按钮事件监听
		buy_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = idText.getText().trim(); // 查询内容
				String sql = CreateSql.getMemberStatistics_Sql(str); // 获得sql语句
				memberSalesModel = new MemberSalesModel(sql, jd);// 构建新的数据模型类，并更新
				jt.setModel(memberSalesModel);// 更新Jtable
				idText.setText("");
			}
		});
		jp3.add(id);
		jp3.add(idText);
		jp3.add(buy_Button);
		c.add(jp3, BorderLayout.SOUTH);

		this.setSize(600, 540);
		this.setResizable(false);
		this.setLocationRelativeTo(null);// 设置窗口居中
		this.setVisible(true);
	}
}
