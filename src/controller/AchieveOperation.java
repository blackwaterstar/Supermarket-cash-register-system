package controller;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Vector;

import dao.JdbcOperation;
import entitise.Commodity;
import entitise.Member;
import entitise.Single;
import entitise.User;
import entitise.Member_sales;
import entitise.Sales;



//数据库业务处理类
public class AchieveOperation {
	private JdbcOperation jdbcOperation;	//与数据库通信的对象
	
	
	
	/**
	 * 登陆业务处理
	 */
	public boolean Login(User user){
		boolean b = true;
		jdbcOperation = new JdbcOperation();	//创建与数据库通信的对象
		User newUser = jdbcOperation.getUser(user);	//获得用户数据
		if(!user.getPassword().equals(newUser.getPassword())){	//比对密码与数据库中的对应密码是否一致
			b = false;
		}
		jdbcOperation.close();//关闭资源
		return b;
	}
	
	/**
	 *返回级别
	 */
	public int Level(User user){
		int b = 0;
		jdbcOperation = new JdbcOperation();	
		b = jdbcOperation.getLevel(user);
		jdbcOperation.close();
		return b;
	}
	
	/**
	 * 根据sql语句返回用户对象集合
	 */
	public Vector<User> getUsers(String sql){
		Vector<User> users;
		jdbcOperation = new JdbcOperation();
		users = jdbcOperation.getUsers(sql);
		jdbcOperation.close();
		return users;
	}
	
	/**
	 * 删除用户业务
	 */
	public boolean deleteUser(String user_name){
		boolean b = true;
		jdbcOperation = new JdbcOperation();
		b = jdbcOperation.deleteUser(user_name);
		jdbcOperation.close();
		return b;
	}
	
	
	/**
	 * 检查是否重复登陆的方法
	 */
	public boolean reset_User_Password(String user_name){
		boolean b = true;
		jdbcOperation = new JdbcOperation();
		b = jdbcOperation.reset_User_Password(user_name);
		jdbcOperation.close();
		return b;
	}
	
	/**
	 * 注册业务处理
	 */
	public boolean Register(User user){
		jdbcOperation = new JdbcOperation();//创建与数据库通信的对象
		boolean b = jdbcOperation.register(user);
		jdbcOperation.close();
		return b;
	}
	
	
	
	/**
	 * 检查是否重复登陆的方法
	 */
	public boolean Check_IsLogin(User user){
		boolean b = true;
		jdbcOperation = new JdbcOperation();
		User newUser = jdbcOperation.getUser(user);
		if(newUser.getIsLogin()==0){
			b = false;
		}
		jdbcOperation.close();
		return b;
	}
		
	/**
	 * 返回成功修改登陆情况
	 */
	public boolean  Update_IsLogin(User user){
		jdbcOperation = new JdbcOperation();//创建与数据库通信的对象
		boolean b = jdbcOperation.update_IsLogin(user);
		jdbcOperation.close();
		return b;
	}
	
	
	
	/**
	 * 修改密码业务处理
	 */
	public boolean update_Password(User user,String new_Password){
		boolean b;
		jdbcOperation = new JdbcOperation();
		b = jdbcOperation.update_Password(user, new_Password);
		jdbcOperation.close();
		return b;
	}
	
	
	
	/**
	 * 获得所有院系
	 */	
	public HashMap<String, String>getAllType(){
		jdbcOperation = new JdbcOperation();
		HashMap<String, String> map;
		map = jdbcOperation.getAllType();
		jdbcOperation.close();
		return map;
	}
	
	
	
	/**
	 * 获得所有专业
	 */
	public HashMap<String, String>getAllShelves(){
		jdbcOperation = new JdbcOperation();
		HashMap<String, String> map;
		map = jdbcOperation.getAllShelves();
		jdbcOperation.close();//关闭资源
		return map;
	}
	
	
	public Vector<String> getType(String type_id){
		jdbcOperation = new JdbcOperation();
		Vector<String> vector;
		vector = jdbcOperation.getType(type_id);
		jdbcOperation.close();
		return vector;
	}
	
	public boolean addCommodity(Commodity commodity){
		jdbcOperation = new JdbcOperation();	//创建与数据库通信的对象
		boolean b = jdbcOperation.addCommodity(commodity);	//获得数据
		jdbcOperation.close();//关闭资源
		return b;
	}
	
	public Commodity getCommodity(String id){
		Commodity commoddity = new Commodity();
		jdbcOperation = new JdbcOperation();
		commoddity = jdbcOperation.getCommodity(id);
		jdbcOperation.close();
		return commoddity;
	}
	
	/**
	 * 修改密码业务处理
	 */
	public boolean updateCommodity(Commodity commodity,String commodity_id){
		boolean b;
		jdbcOperation = new JdbcOperation();
		b = jdbcOperation.updateCommodity(commodity,commodity_id);
		jdbcOperation.close();
		return b;
	}
	
	/**
	 * 根据sql语句返回对象集合
	 */
	public Vector<Commodity> getCommodities(String sql){
		Vector<Commodity> commoddity;
		jdbcOperation = new JdbcOperation();
		commoddity = jdbcOperation.getCommodities(sql);
		jdbcOperation.close();
		return commoddity;
	}

	public Commodity checkLife(String name) {
		Commodity commoddity = new Commodity();
		jdbcOperation = new JdbcOperation();
		commoddity = jdbcOperation.checkLife(name);
		jdbcOperation.close();
		return commoddity;
	}
	
	public boolean addSingle(Single single){
		jdbcOperation = new JdbcOperation();	//创建与数据库通信的对象
		boolean b = jdbcOperation.addSingle(single);	//获得数据
		jdbcOperation.close();//关闭资源
		return b;
	}

	public Vector<Single> getSingles(String sql) {
		Vector<Single> Single;
		jdbcOperation = new JdbcOperation();
		Single = jdbcOperation.getSingles(sql);
		jdbcOperation.close();
		return Single;
	}

	public boolean deleteSingles(){
		jdbcOperation = new JdbcOperation();	//创建与数据库通信的对象
		boolean b = jdbcOperation.deleteSingles();	//获得数据
		jdbcOperation.close();//关闭资源
		return b;
	}
	
	public String getAllnumber() {
		String all = null;
		jdbcOperation = new JdbcOperation();
		all = jdbcOperation.getAllnumber();
		return all;
	}
	
	public String getAllprice() {
		String all = null;
		jdbcOperation = new JdbcOperation();
		all = jdbcOperation.getAllprice();
		return all;
	}

	public boolean addMember(String name) {
		jdbcOperation = new JdbcOperation();	//创建与数据库通信的对象
		boolean b = jdbcOperation.addMember(name);	//获得数据
		jdbcOperation.close();//关闭资源
		return b;
	}

	public String getMember_id(String name) {
		String all = null;
		jdbcOperation = new JdbcOperation();
		all = jdbcOperation.getMember_id(name);
		return all;
	}

	public boolean getMember(String id) {
		boolean b = true;
		jdbcOperation = new JdbcOperation();
		Member newMember = new Member();
		newMember = jdbcOperation.getMember(id);
		if(!newMember.getMember_id().equals(id)){	//比对数据库中是否一致
			b = false;
		}
		return b;
	}

	public boolean addSales() {
		jdbcOperation = new JdbcOperation();	//创建与数据库通信的对象
		boolean b = jdbcOperation.addSales();	//获得数据
		jdbcOperation.close();//关闭资源
		return b;
	}

	public boolean addSales1() {
		jdbcOperation = new JdbcOperation();	//创建与数据库通信的对象
		boolean b = jdbcOperation.addSales1();	//获得数据
		jdbcOperation.close();//关闭资源
		return b;
	}
	//"SELECT commodity_name,price,sum(number),allprice,date FROM sales WHERE date=\"2020-12-27\" GROUP BY commodity_name"

	public boolean getmember_sales(String member_id) {
		jdbcOperation = new JdbcOperation();	//创建与数据库通信的对象
		boolean b = jdbcOperation.getmember_sales(member_id);	//获得数据
		jdbcOperation.close();//关闭资源
		return b;
	}

	public Vector<Member_sales> getMemberSales_Sql(String sql) {
		Vector<Member_sales> member_sales;
		jdbcOperation = new JdbcOperation();
		member_sales = jdbcOperation.getMemberSales_Sql(sql);
		jdbcOperation.close();
		return member_sales;
	}

	public boolean deleteSingle(String commodity_name) {
		boolean b = true;
		jdbcOperation = new JdbcOperation();
		b = jdbcOperation.deleteSingle(commodity_name);
		jdbcOperation.close();
		return b;
	}

	public Vector<Sales> getsaleses(String sql) {
		Vector<Sales> sales;
		jdbcOperation = new JdbcOperation();
		sales = jdbcOperation.getsaleses(sql);
		jdbcOperation.close();
		return sales;
	}

	public boolean deleteCommodity(String commodity_id) {
		boolean b = true;
		jdbcOperation = new JdbcOperation();
		b = jdbcOperation.deleteCommodity(commodity_id);
		jdbcOperation.close();
		return b;
	}

	public boolean updateCommodity(Commodity newComodify) {
		boolean b = true;
		jdbcOperation = new JdbcOperation();
		b = jdbcOperation.updateCommodity(newComodify);
		jdbcOperation.close();
		return b;
	}
	
}
