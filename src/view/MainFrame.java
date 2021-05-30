package view;

import controller.AchieveOperation;
import entitise.User;
import tools.GroundPanel;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
//主界面
public class MainFrame extends JFrame {
	private JMenuBar menuBar;	//应用菜单条。
	private JMenu commodity_Management;	//菜单。
	private JMenu sales_Management;	
	private JMenu personal_Management;	
	private JMenu admin_Management;
	private JMenu help;	//"帮助"菜单。
	private JMenuItem delete_User;
	private JMenuItem reset_User_password;
	private JMenuItem add_Commodity;	
	private JMenuItem delete_Commodity;	
	private JMenuItem modify_Commodity;	
	private JMenuItem query_Commodity;	
	private JMenuItem cashier;	
	private JMenuItem member_sales;	
	private JMenuItem sales;
	private JMenuItem change_Password;	//"修改密码"菜单项。
	private JMenuItem logout;	//"退出登录"菜单项。
	private JMenuItem contact_customer_servier;	//"联系客服"菜单项。
	private JFrame jf;	//当前窗口。
	private User user;//当前用户。
	public MainFrame(User user){
		
		//super("学生成绩管理系统,欢迎你"+user.getUsername());
		String mString = null;
		if (user.getLevel() == 1) {
			mString = "超市收银系统        超级管理员：";
		}
		if (user.getLevel() == 3) {
			mString = "超市收银系统        仓库管理员：";
		}
		else 
			mString = "超市收银系统        收银员：";
		this.setTitle(mString+user.getUsername());
		this.user = user;
		this.jf = this;
	//	jf = this;
		menuBar = new JMenuBar();	//创建菜单条。
		this.setJMenuBar(menuBar);	//添加菜单条。
		
		commodity_Management = new JMenu("商品管理");
		if(user.getLevel()==3||user.getLevel()==1) {
			menuBar.add(commodity_Management);	
		}
		
		
		add_Commodity = new JMenuItem("添加商品");	
		//注册"添加学生"菜单项事件监听
		add_Commodity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddCommodity addCommodity = new AddCommodity(jf,"添加商品",true);
			}
		});
		commodity_Management.add(add_Commodity);
		
		delete_Commodity = new JMenuItem("删除商品");	
		//注册"删除学生"按钮事件监听
		delete_Commodity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteCommodity deleteCommodity = new DeleteCommodity(jf, "删除商品", true);
				
			}
		});
		commodity_Management.add(delete_Commodity);	
		
		modify_Commodity = new JMenuItem("修改商品");	
		//注册"删除学生"按钮事件监听
		modify_Commodity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ModifyCommodity modifyCommodity = new ModifyCommodity(jf, "修改商品信息", true);
				
			}
		});
		commodity_Management.add(modify_Commodity);	
		
		
		
		query_Commodity = new JMenuItem("查询商品信息");	
		//注册"查询学生"菜单项事件监听。
		query_Commodity.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				QueryCommodity queryCommodity = new QueryCommodity(jf, "查询商品信息", true);
				
			}
		});
		commodity_Management.add(query_Commodity);	
		
		sales_Management = new JMenu("收银管理");	
		if (user.getLevel()==1||user.getLevel()==2) {
			menuBar.add(sales_Management);	
			
		}
		
		
		
		cashier = new JMenuItem("收银模块");	
		//注册"修改学生信息"菜单项事件监听
		cashier.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CashExhibition ce = new CashExhibition(jf, "收银模块", true);
				
			}
		});
		sales_Management.add(cashier);	
	
		member_sales = new JMenuItem("会员消费情况");	
		//注册"添加学生成绩"菜单项事件监听
		member_sales.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				MemberSalesRecord mss = new MemberSalesRecord(jf, "会员消费情况", true);
			//	Pay pay = new Pay(jf, "收银支付", true);
			}
		});
		sales_Management.add(member_sales);	
		
		sales = new JMenuItem("销售情况");
		//注册"修改学生成绩"菜单项事件监听
		sales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SalesRecord sr = new SalesRecord(jf, "销售情况", true);
			}
		});
		
		sales_Management.add(sales);	
		
//		query_Score = new JMenuItem("会员消费情况");	
//		//注册"成绩查询"菜单项事件监听
//		query_Score.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {	
//				
//			}
//		});
//		sales_Management.add(query_Score);	
//		
//		score_Statistics = new JMenuItem("销售统计");	
//		score_Statistics.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		sales_Management.add(score_Statistics);	
		
		personal_Management = new JMenu("个人管理");	//创建"个人管理"菜单。
		menuBar.add(personal_Management);	//添加"个人管理"菜单。
		
		change_Password = new JMenuItem("修改密码");	//创建"修改密码"菜单项。
		//注册"修改密码"菜单项事件监听
		change_Password.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdatePassword updatePassword = new UpdatePassword(jf, "修改密码", true,user);
			}
		});
		personal_Management.add(change_Password);	//添加"修改密码"菜单项。
		
		logout = new JMenuItem("退出登录");	//创建"退出登录"菜单项。
		//注册"退出登录"菜单项时间监听
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
				jf.dispose();	//关闭当前窗口
				//修改登陆状态
				AchieveOperation achieveOperation = new AchieveOperation();
				user.setIsLogin(0);//设置登陆状态为未登录。
				achieveOperation.Update_IsLogin(user);
				LoginFrame login = new LoginFrame();	//打开登陆界面
				
			}
		});
		personal_Management.add(logout);	//添加"退出登录"菜单项。
		
		if (user.getLevel()==1) {
			admin_Management = new JMenu("用户管理");	//创建"用户管理"菜单，管理员管理用户。
			menuBar.add(admin_Management);	//添加"用户管理"菜单。
			
			reset_User_password = new JMenuItem("重置密码");
			
			reset_User_password.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					
					Resetpassword resetpassword = new Resetpassword(jf, "重置用户密码", true);
				}
			});
			
			delete_User = new JMenuItem("删除用户");	//创建"删除用户"菜单项。
			//注册"删除用户"菜单项事件监听
			delete_User.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					DeleteUser deleteUser = new DeleteUser(jf, "删除用户", true);
				}
			});
			admin_Management.add(delete_User);	//添加"删除用户"菜单项。
			admin_Management.add(reset_User_password);
		}
		
		
			help = new JMenu("帮助");	//创建"帮助"菜单。
			menuBar.add(help);	//添加"帮助"菜单。
			
			contact_customer_servier = new JMenuItem("联系客服");	//创建"联系客服"菜单项。
			
			contact_customer_servier.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(jf, "有问题联系美女,客服电话：1892*******", "帮助", JOptionPane.WARNING_MESSAGE);
					return ;
				}
			});
			help.add(contact_customer_servier);	
		
		
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);//设置窗口居中
		GroundPanel groundPanel = new GroundPanel();
		setContentPane(groundPanel);
	
		try {
			Image img = ImageIO.read(this.getClass().getResource("/images/schoolbadge.jpg"));//页面图标
			this.setIconImage(img);
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}

		this.setVisible(true);//设置窗体可见。
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				//修改登陆状态
				AchieveOperation achieveOperation = new AchieveOperation();
				user.setIsLogin(0);//设置登陆状态为未登录。
				achieveOperation.Update_IsLogin(user);
				
			}
		
		});
	}
	
	
	
}
