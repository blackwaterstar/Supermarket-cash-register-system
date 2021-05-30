package dao;

import java.util.ResourceBundle;

public interface JdbcConfig {
	//localhost是你的本机名，3306是你的MySQL的连接端口。
//    String DRIVER = "com.mysql.cj.jdbc.Driver";
//    String URL = "jdbc:mysql://localhost:3306/stusys?useSSL=false&useUnicode=true&allowPublicKeyRetrieval=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
//    String USERNAME = "root";//数据库用户名
//    String PASSWORD = "123456";//数据库密码
	
	
	static ResourceBundle BUNDLE = ResourceBundle.getBundle("jdbc");
	static String DRIVER = BUNDLE.getString("DRIVER");
	static String URL = BUNDLE.getString("URL");
	static String USERNAME = BUNDLE.getString("USERNAME");
	static String PASSWORD = BUNDLE.getString("PASSWORD");
 
 
    /*
     * 如果是MySQL8.0以下的版本，则使用以下代码并注释上面代码（用户名是指的数据库的用户名，密码也是数据库的密码）。
     */
//	String DRIVER ="com.mysql.jdbc.Driver";   
//  String URL = "jdbc:mysql://localhost:3306/数据库名?allowPublicKeyRetrieval=true&characterEncoding=utf-8" ;     
//  String USERNAME = "用户名" ;    
//  String PASSWORD = "密码" ;   

}
