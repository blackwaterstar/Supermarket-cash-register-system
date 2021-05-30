package entitise;

public class User {
	private String username;	//用户名
	private String password;	//密码
	private int isLogin = 0;	//用户是否登陆
	private int level = 2;		//用户等级，1级为管理员，2级为老师
	
	public int getIsLogin() {
		return isLogin;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
