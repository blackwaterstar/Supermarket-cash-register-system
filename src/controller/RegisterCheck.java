package controller;

import entitise.User;

public class RegisterCheck {
	User user;

	public RegisterCheck(User user ) {
		this.user = user;
		// TODO 自动生成的构造函数存根
	}

	public boolean checkusername() {
		boolean b = false;
		String regex="^[A-Za-z]{1,10}$";//用户名必须是纯字母的1-10位，区分大小写
		if (user.getUsername().matches(regex)) {
			b = true;
		}
		return b;
	}
	
	public boolean checkpassword() {
		
		boolean b = false;
		String regex="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$";//密码必须是数字和字母混合的6-15位，区分大小写
		if (user.getPassword().matches(regex)) {
			b = true;
		}
		return b;
	}

}
