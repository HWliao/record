package com.example.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;

public class MyRealm2 extends MyRealm implements Realm {

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		return authentication(token, "zhang", "123");
	}

	@Override
	public String getName() {
		return "myRealm2";
	}

}
