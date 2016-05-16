package com.example.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.Realm;

public class MyRealm1 extends MyRealm implements Realm {

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		authentication(token, "zhang", "123");
		return new SimpleAuthenticationInfo("myrealm1", "123", getName());
	}

	@Override
	public String getName() {
		return "myRealm1";
	}

}
