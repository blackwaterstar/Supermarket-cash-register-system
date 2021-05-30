package dao;

import entitise.Commodity;
import entitise.Member;
import entitise.Single;
import entitise.User;
import entitise.Member_sales;
import entitise.Sales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import com.mysql.cj.jdbc.NClob;

public class JdbcOperation implements JdbcConfig {
	// 定义连接数据库所需要的对象
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection ct = null;

	// 获得数据库的连接
	private void conn() {
		try {
			Class.forName(DRIVER);
			ct = DriverManager.getConnection(URL, USERNAME, PASSWORD);// 获得数据库连接
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 无参构造函数
	public JdbcOperation() {
		this.conn();
	}

	/**
	 * 获取用户对象
	 */
	public User getUser(User user) {
		User newUser = new User();
		try {
			ps = ct.prepareStatement("select * from tb_User where User_name=?");
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			if (rs.next()) {
				newUser.setUsername(rs.getString(1)); // 设置用户名
				newUser.setPassword(rs.getString(2)); // 设置密码
				newUser.setIsLogin(rs.getInt(3)); // 设置是否登陆
				newUser.setLevel(rs.getInt(4)); // 设置用户级别
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return newUser;
	}

	/**
	 * 根据sql语句返回特定的用户集合
	 */
	public Vector<User> getUsers(String sql) {
		Vector<User> users = new Vector<User>();
		try {
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString(1)); // 设置用户名
				user.setPassword(rs.getString(2)); // 设置密码
				user.setIsLogin(rs.getInt(3)); // 设置是否登陆
				user.setLevel(rs.getInt(4)); // 设置用户级别
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	/**
	 * 根据用户名从数据库移除该用户
	 */
	public boolean deleteUser(String user_name) {
		boolean b = true;
		try {
			ps = ct.prepareStatement("delete from tb_User where User_Name=?");
			ps.setString(1, user_name);
			if (ps.executeUpdate() != 1) {
				b = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 根据用户名重置用户密码
	 */
	public boolean reset_User_Password(String user_name) {
		boolean b = true;
		try {
			ps = ct.prepareStatement("update tb_User set password=111111 where User_Name=?");
			ps.setString(1, user_name);
			if (ps.executeUpdate() != 1) {
				b = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 获取用户级别
	 */
	public int getLevel(User user) {
		int level = 0;
		try {
			ps = ct.prepareStatement("select level from tb_User where User_name=?");
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			if (rs.next()) {
				level = rs.getInt(1); // 设置用户级别
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return level;
	}

	/**
	 * 注册处理
	 */
	public boolean register(User user) {
		boolean b = true;
		try {
			ps = ct.prepareStatement("insert into tb_User(User_name,Password,level) values(?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getLevel());
			if (ps.executeUpdate() != 1) { // 执行sql语句
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 修改用户"是否"登陆状态 user
	 * 
	 */
	public boolean update_IsLogin(User user) {
		boolean b = true;
		try {
			ps = ct.prepareStatement("update tb_User set IsLogin=? where User_name=?");
			ps.setInt(1, user.getIsLogin());
			ps.setString(2, user.getUsername());
			if (ps.executeUpdate() != 1) { // 数据库操作语句executeUpdate()成功返回1，否则0;
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 修改密码
	 */
	public boolean update_Password(User user, String new_Password) {
		boolean b = true;
		try {
			ps = ct.prepareStatement("update tb_User set Password=? where User_name=?");
			ps.setString(1, new_Password);
			ps.setString(2, user.getUsername());
			if (ps.executeUpdate() != 1) { // 执行sql语句
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	// /**
	// * 获得所有院系
	// */
	// public HashMap<String, String> getAllDepartment(){
	// HashMap<String, String> map = new LinkedHashMap<String, String>();
	// map.put("", "");//添加一个空的元素
	// try {
	// ps = ct.prepareStatement("select * from tb_Department order by
	// Department_ID");
	// rs = ps.executeQuery();
	// while(rs.next()){
	// map.put(rs.getString(2),rs.getString(1));
	// }
	// } catch (SQLException e) {
	//
	// e.printStackTrace();
	// }
	// return map;
	// }

	/**
	 * 获得对应院系的专业
	 */
	public Vector<String> getType(String shelves_id) {
		Vector<String> vector = new Vector<String>();
		vector.add("");
		try {
			ps = ct.prepareStatement("select * from type where shelves_id=? order by type_id");
			ps.setString(1, shelves_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				vector.add(rs.getString(2)); // 获得专业名称
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vector;
	}

	/**
	 * 获得所有shelves
	 */
	public HashMap<String, String> getAllShelves() {
		HashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");
		try {
			ps = ct.prepareStatement("select * from shelves order by shelves_id");
			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(2), rs.getString(1));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获得所有type
	 */
	public HashMap<String, String> getAllType() {
		HashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");
		try {
			ps = ct.prepareStatement("select * from type order by type_id");
			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(2), rs.getString(1));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return map;
	}

	public Commodity getCommodity(String id) {
		Commodity commodity = new Commodity();
		try {
			ps = ct.prepareStatement("select * from commodity where Commodity_id=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				commodity.setCommodity_Id(rs.getString(1));
				commodity.setCommodity_Name(rs.getString(2));
				commodity.setShelves(rs.getString(3));
				commodity.setType(rs.getString(4));
				commodity.setPrice(rs.getString(5));
				commodity.setNumber(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commodity;
	}

	public boolean addCommodity(Commodity commodity) {
		boolean b = true;
		try {
			ps = ct.prepareStatement(
					"insert into commodity(commodity_id,commodity_name,shelves,type,price,number) values(?,?,?,?,?,?)");
			ps.setString(1, commodity.getCommodity_Id());
			ps.setString(2, commodity.getCommodity_Name());
			ps.setString(3, commodity.getShelves());
			ps.setString(4, commodity.getType());
			ps.setString(5, commodity.getPrice());
			ps.setString(6, commodity.getNumber());
			if (ps.executeUpdate() != 1) { // 执行sql语句
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public boolean updateCommodity(Commodity commodity, String commodity_id) {
		boolean b = true;
		try {
			ps = ct.prepareStatement(
					"update commodity set commodity_id=?, commodity_name=?, shelves=? ,type=? ,price=?  ,number=? where commodity_id=?");
			ps.setString(1, commodity.getCommodity_Id());
			ps.setString(2, commodity.getCommodity_Name());
			ps.setString(3, commodity.getShelves());
			ps.setString(4, commodity.getType());
			ps.setString(5, commodity.getPrice());
			ps.setString(6, commodity.getNumber());
			ps.setString(7, commodity_id);
			if (ps.executeUpdate() != 1) {
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 根据sql语句返回特定的学生集合
	 */
	public Vector<Commodity> getCommodities(String sql) {
		Vector<Commodity> commodities = new Vector<Commodity>();
		try {
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Commodity commodity = new Commodity();
				commodity.setCommodity_Id(rs.getString("commodity_id"));
				commodity.setCommodity_Name(rs.getString("commodity_name"));
				commodity.setShelves(rs.getString("shelves"));
				commodity.setType(rs.getString("type"));
				commodity.setNumber(rs.getString("number"));
				commodity.setPrice(rs.getString("price"));
				commodities.add(commodity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commodities;
	}

	// 关闭数据库资源
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (ct != null)
				ct.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Commodity checkLife(String name) {
		Commodity commodity = new Commodity();
		try {
			ps = ct.prepareStatement("select * from commodity where Commodity_name=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				commodity.setCommodity_Id(rs.getString(1));
				commodity.setCommodity_Name(rs.getString(2));
				commodity.setShelves(rs.getString(3));
				commodity.setType(rs.getString(4));
				commodity.setPrice(rs.getString(5));
				commodity.setNumber(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commodity;
	}

	public boolean addSingle(Single single) {
		boolean b = true;
		try {
			ps = ct.prepareStatement("insert into single(commodity_name,price,number,allprice) values(?,?,?,?)");
			ps.setString(1, single.getCommodity_Name());
			ps.setString(2, single.getPrice());
			ps.setString(3, single.getNumber());
			ps.setString(4, (Double.parseDouble(single.getPrice()) * Double.parseDouble(single.getNumber())) + "");
			if (ps.executeUpdate() != 1) { // 执行sql语句
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public Vector<Single> getSingles(String sql) {
		Vector<Single> singles = new Vector<Single>();
		try {
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Single single = new Single();
				single.setCommodity_Name(rs.getString(1)); 
				single.setPrice(rs.getString(2)); 
				single.setNumber(rs.getString(3)); 
				single.setAllprice(rs.getString(4)); 
				singles.add(single);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return singles;
	}

	public boolean deleteSingles() {
		boolean b = true;
		try {
			ps = ct.prepareStatement("delete from single");
			if (ps.executeUpdate() == 0) { // 执行sql语句
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public String getAllnumber() {
		String all = null;
		try {
			ps = ct.prepareStatement("select sum(number) from single");
			rs = ps.executeQuery();
			if (rs.next()) {
				all = rs.getString(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return all;
	}

	public String getAllprice() {
		String all = null;
		try {
			ps = ct.prepareStatement("select sum(allprice) from single");
			rs = ps.executeQuery();
			if (rs.next()) {
				all = rs.getString(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return all;
	}

	public boolean addMember(String name) {
		boolean b = true;
		try {
			ps = ct.prepareStatement("INSERT INTO `supermark`.`member`(`member_name`) VALUES (?);");
			ps.setString(1, name);
			if (ps.executeUpdate() != 1) { // 执行sql语句
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public String getMember_id(String name) {
		String id = null;
		try {
			ps = ct.prepareStatement("select member_id from `member` where member_name=?;");
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;

	}

	public Member getMember(String id) {
		Member newMember = new Member();
		try {
			ps = ct.prepareStatement("select * from `member` where member_id=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				newMember.setMember_id(rs.getString(1));
				newMember.setMember_name(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newMember;
	}

	public boolean addSales() {
		boolean b = true;
		try {
			ps = ct.prepareStatement(
					"INSERT INTO sales (commodity_name,number,price,allprice,date) SELECT commodity_name,number,price,allprice,curdate() FROM single");
			if (ps.executeUpdate() == 0) { // 执行sql语句
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public boolean addSales1() {
		boolean b = true;
		try {
			ps = ct.prepareStatement(
					"INSERT INTO sales (commodity_name,number,price,allprice,date) SELECT commodity_name,number,price,allprice*0.95,curdate() FROM single");
			if (ps.executeUpdate() == 0) { // 执行sql语句
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public boolean getmember_sales(String member_id) {
		boolean b = true;
		Vector<Single> Single = getSingles("select * from single");
		for (Single single2 : Single) {
			try {
				ps = ct.prepareStatement("insert into member_sales(member_id,commodity_name,price,number,allprice) values(?,?,?,?,?)");
				ps.setString(1, member_id);
				ps.setString(2, single2.getCommodity_Name());
				ps.setString(3, single2.getPrice());
				ps.setString(4, single2.getNumber());
				ps.setString(5, (Double.parseDouble(single2.getAllprice())*0.95)+"");
				if (ps.executeUpdate() != 1) { // 执行sql语句
					b = false;
				}
			} catch (SQLException e) {
				b = false;
				e.printStackTrace();
			}
			
		}
		return b;
	}

	public Vector<Member_sales> getMemberSales_Sql(String sql) {
		Vector<Member_sales> member_sales = new Vector<Member_sales>();
		try {
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Member_sales ms = new Member_sales();
				ms.setMember_id(rs.getString(1));
				ms.setCommodity_Name(rs.getString(2));
				ms.setPrice(rs.getString(3));
				ms.setNumber(rs.getString(4));
				ms.setAllprice(rs.getString(5));
				member_sales.add(ms);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member_sales;
	}

	public boolean deleteSingle(String commodity_name) {
		boolean b = true;
		try {
			ps = ct.prepareStatement("delete from single where commodity_name=?");
			ps.setString(1, commodity_name);
			if (ps.executeUpdate() != 1) {
				b = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public Vector<Sales> getsaleses(String sql) {
		Vector<Sales> sales = new Vector<Sales>();
		try {
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Sales s = new Sales();
				s.setCommodity_Name(rs.getString(1));
				s.setPrice(rs.getString(2));
				s.setNumber(rs.getString(3));
				s.setAllprice(rs.getString(4));
				s.setDate(rs.getString(5));
				//System.err.println(s.toString());
				sales.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}

	public boolean deleteCommodity(String commodity_id) {
		boolean b = true;
		try {
			ps = ct.prepareStatement("delete from commodity where commodity_id=?");
			ps.setString(1, commodity_id);
			if (ps.executeUpdate() != 1) {
				b = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public boolean updateCommodity(Commodity newComodify) {
		boolean b = true;
		try {
			//update
			ps = ct.prepareStatement("update commodity set commodity_name=?, shelves=? ,type=? ,price=?  ,number=? where commodity_Id=?");
			ps.setString(1, newComodify.getCommodity_Name());
			ps.setString(2, newComodify.getShelves());
			ps.setString(3, newComodify.getType());
			ps.setString(4, newComodify.getPrice());
			ps.setString(5, newComodify.getNumber());
			ps.setString(6, newComodify.getCommodity_Id());
			if(ps.executeUpdate()!=1){
				b = false;
			}
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

}
