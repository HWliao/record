package com.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShiroDemo1Application.class)
public class ShiroDemo1ApplicationTests {

	private void login(String configFile) {
		// 1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				configFile);
		// 2、得到 SecurityManager 实例 并绑定给 SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory
				.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		subject.login(token);
	}

	@Test
	public void testAllSuccess() {
		login("classpath:data/authenticator/shiro-authenticator-all-success.ini");
		Subject subject = SecurityUtils.getSubject();

		// 得到一个省份验证集合,包含了realm验证成功的省份
		PrincipalCollection principalCollection = subject.getPrincipals();
		Assert.assertEquals(2, principalCollection.asList().size());
	}

	@Test
	public void testShiro1() {
		// 1.获取security manager工厂,此处使用user.ini初始化
		// 2.得到securityManager实例 并绑定给securityUtils
		// 3.得到subject token
		// 4.登入
		// 5.身份验证失败
		// 6.退出
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:data/user/user.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录
		subject.logout();
	}


}
