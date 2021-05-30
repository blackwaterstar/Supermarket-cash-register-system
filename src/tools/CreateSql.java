package tools;

//生成sql语句的工具类
public class CreateSql {

	// 根据查询内容、选项从学生表里返回特定的sql语句
	public static String getCommodity_Sql(String str, String option) {
		String sql = null;
		if ("全部".equals(option)) {
			sql = "select * from commodity";
		} else if ("商品号".equals(option)) {
			sql = "select * from commodity where Commodity_Id like '%" + str + "%'";
		} else if ("商品名".equals(option)) {
			sql = "select * from commodity where Commodity_Name like '%" + str + "%'";
		} else if ("货架号".equals(option)) {
			sql = "select * from commodity where Shelves like '%" + str + "%'";
		} else if ("类别".equals(option)) {
			sql = "select * from commodity where type like '%" + str + "%'";
		} else if ("单价".equals(option)) {
			sql = "select * from commodity where Price like '%" + str + "%'";
		} else if ("数量".equals(option)) {
			sql = "select * from commodity where number  like '%" + str + "%'";
		}
		return sql;
	}

	public static String getSingle_Sql(String str, String option) {
		String sql = null;
		if ("全部".equals(option)) {
			sql = "select * from single";
		} else if ("商品名".equals(option)) {
			sql = "select * from single where Commodity_Name like '%" + str + "%'";
		} else if ("单价".equals(option)) {
			sql = "select * from single where Price like '%" + str + "%'";
		} else if ("数量".equals(option)) {
			sql = "select * from single where number  like '%" + str + "%'";
		} else if ("总价".equals(option)) {
			sql = "select * from single where allprice like '%" + str + "%'";
		}
		return sql;
	}

	public static String getMemberSales_Sql(String str, String option) {
		String sql = null;
		if ("全部".equals(option)) {
			sql = "select * from member_sales";
		} else if ("会员号".equals(option)) {
			sql = "select * from member_sales where member_id like '%" + str + "%'";
		} else if ("商品名".equals(option)) {
			sql = "select * from member_sales where commodity_name like '%" + str + "%'";
		} else if ("单价".equals(option)) {
			sql = "select * from member_sales where Price like '%" + str + "%'";
		} else if ("数量".equals(option)) {
			sql = "select * from member_sales where number  like '%" + str + "%'";
		} else if ("总价".equals(option)) {
			sql = "select * from member_sales where allprice like '%" + str + "%'";
		}
		return sql;
	}

	public static String getSaleses_Sql(String str, String option) {
		String sql = null;
		if ("全部".equals(option)) {
			sql = "select * from sales";
		} else if ("商品名".equals(option)) {
			sql = "select * from sales where Commodity_Name like '%" + str + "%'";
		} else if ("单价".equals(option)) {
			sql = "select * from sales where Price like '%" + str + "%'";
		} else if ("数量".equals(option)) {
			sql = "select * from sales where number  like '%" + str + "%'";
		} else if ("总价".equals(option)) {
			sql = "select * from sales where allprice like '%" + str + "%'";
		} else if ("销售日期yyyy-mm-dd".equals(option)) {
			sql = "select * from sales where date like '%" + str + "%'";
		}
		return sql;
	}

	// 根据查询内容、选项从用户表里返回特定的sql语句
	public static String getUser_Sql(String str, String option) {
		String sql = null;
		if ("全部".equals(option)) {
			sql = "select * from tb_User where Level!=1";
		} else if ("用户名".equals(option)) {
			sql = "select * from tb_User where User_Name like '%" + str + "%' and Level!=1";
		} else if ("密码".equals(option)) {
			sql = "select * from tb_User where Password like '%" + str + "%' and Level!=1";
		} else if ("登录状况".equals(option)) {
			sql = "select * from tb_User where isLogin like '%" + str + "%' and Level!=1";
		} else if ("级别".equals(option)) {
			sql = "select * from tb_User where Level like '%" + str + "%' and Level!=1";
		}
		return sql;
	}

	// 多条件查询的sql语句创建
	public static String getConditions_Sql(String id, String name, String shelves, String type, String price,
			String number) {
		StringBuilder sql = new StringBuilder("select * from commodity where 1=1");
		if (!id.equals("")) {
			sql.append(" and Commodity_Id  like '%" + id + "%'  ");
		}
		if (!name.equals("")) {
			sql.append(" and Commodity_Name like '%" + name + "%'  ");
		}
		if (!shelves.equals("")) {
			sql.append(" and shelves like '%" + shelves + "%'  ");
		}
		if (!type.equals("")) {
			sql.append(" and type like '%" + type + "%'  ");
		}
		if (!price.equals("")) {
			sql.append(" and price  like '%" + price + "%'  ");
		}
		if (!number.equals("")) {
			sql.append(" and number like '%" + number + "%'  ");
		}

		return sql.toString();
	}

	public static String getMemberStatistics_Sql(String str) {
		// TODO 自动生成的方法存根
		String sql = "SELECT member_id,commodity_name,price,sum(number),sum(allprice) FROM member_sales WHERE member_id="
				+ str + " GROUP BY commodity_name";
		return sql;
	}

	public static String getSalesStatistics_Sql(String str) {
		String sql = null;
		if (str.equals(null) || str.equals("")) {
			sql = "SELECT commodity_name,price,sum(number),sum(allprice),date FROM sales GROUP BY commodity_name,date";
		} else {
			sql = "SELECT commodity_name,price,sum(number),sum(allprice),date FROM sales WHERE date=\""+str+"\" GROUP BY commodity_name,date";
		}
		// TODO 自动生成的方法存根

		return sql;
	}

}
