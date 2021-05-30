package view;

import model.CommodityModel;
import tools.CreateSql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

//组合查询界面
public class ConditionsQuery extends JDialog{
    private JLabel commodity_ID;    
    private JLabel commodity_Name;	
    private JLabel shelves_Label_Label;	
    private JLabel type_Label;	
    private JLabel number_Label;	
    private JLabel price_Label;	
    private JTextField commodity_IDText;	
    private JTextField commodity_NameText;	
    private JTextField shelves_Text;	
    private JTextField type_Text;	
    private JTextField price_Text;	
    private JTextField number_Text;	
    private JButton conditions_button;	
    private JDialog jd;	
   
    public ConditionsQuery(JDialog owner, String title, boolean modal,JTable jt){
    	super(owner, title, modal);
    	this.jd = this;
    	this.setLayout(null);
    	
    	commodity_ID = new JLabel("商品号:");
    	commodity_ID.setBounds(20, 19, 60, 20);
    	this.add(commodity_ID);
    	
    	commodity_IDText = new JTextField();
    	commodity_IDText.setBounds(65, 19, 100, 20);
    	this.add(commodity_IDText);
    	
    	commodity_Name = new JLabel("商品名:");
    	commodity_Name.setBounds(200, 19, 60, 20);
    	this.add(commodity_Name);
    	
    	commodity_NameText = new JTextField();
    	commodity_NameText.setBounds(240, 19, 100, 20);
    	this.add(commodity_NameText);
    	
    	
    	shelves_Label_Label = new JLabel("货架号:");
    	shelves_Label_Label.setBounds(20, 50, 60, 20);
    	this.add(shelves_Label_Label);
    	
    	shelves_Text = new JTextField();
    	shelves_Text.setBounds(65, 50, 100, 20);
    	this.add(shelves_Text);
    	
    	type_Label = new JLabel("类别:");
    	type_Label.setBounds(200, 50, 60, 20);
    	this.add(type_Label);
    	
    	type_Text = new JTextField();
    	type_Text.setBounds(240, 50, 100, 20);
    	this.add(type_Text);
    	
    	price_Label = new JLabel("单价:");
    	price_Label.setBounds(20, 83, 60, 20);
    	this.add(price_Label);
    	
    	price_Text = new JTextField();
    	price_Text.setBounds(65, 83, 100, 20);
    	this.add(price_Text);
    	
    	number_Label = new JLabel("数量:");
    	number_Label.setBounds(200, 83, 60, 20);
    	this.add(number_Label);
    	
    	number_Text = new JTextField();
    	number_Text.setBounds(240, 83, 100, 20);
    	this.add(number_Text);
    	
    	conditions_button = new JButton("查询");
    	conditions_button.setBounds(230, 130, 100, 30);
    	//注册"多条件查询"按钮事件监听
    	conditions_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = commodity_IDText.getText().trim();
				String name = commodity_NameText.getText().trim();
				String shelves = shelves_Text.getText().trim();
				String type = type_Text.getText().trim();
				String price = price_Text.getText().trim();
				String number = number_Text.getText().trim();
				if(id.equals("")&&name.equals("")&&shelves.equals("")&&type.equals("")&&price.equals("")&&number.equals("")){
					JOptionPane.showMessageDialog(jd, "条件不能为空！", "", JOptionPane.WARNING_MESSAGE);
					return ;
				}else{
					String sql = CreateSql.getConditions_Sql(id, name, shelves, type, price, number);
					CommodityModel cModel = new CommodityModel(sql,jd);
					jt.setModel(cModel);
					jd.dispose();
				}
				
			}
		});
    	this.add(conditions_button);
    	
    	
    	this.setSize(411, 222);
    	this.setResizable(false);
    	this.setLocationRelativeTo(null);//设置窗口居中
    	this.setVisible(true);
    }
}
