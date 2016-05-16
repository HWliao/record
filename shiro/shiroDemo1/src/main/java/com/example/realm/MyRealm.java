package com.example.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm implements Realm {

	protected AuthenticationInfo authentication(AuthenticationToken token,
			String usernameStr, String passwordStr)
			throws AuthenticationException {
		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码
		if (!usernameStr.equals(username)) {
			throw new UnknownAccountException(); // 如果用户名错误
		}
		if (!passwordStr.equals(password)) {
			throw new IncorrectCredentialsException(); // 如果密码错误
		}
		// 如果身份认证验证成功，返回一个 AuthenticationInfo 实现；
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		return authentication(token, "zhang", "123");
	}

	@Override
	public String getName() {
		return "myRealm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

}
