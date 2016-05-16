package com.example.tutorial;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Tutorial {
	private static final transient Logger log = LoggerFactory
			.getLogger(Tutorial.class);

	@PostConstruct
	public void init() {

		log.info("My First Apache Shiro Application");

		// 1.获取security manager factory
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro.ini");

		// 2.获取security manager
		SecurityManager securityManager = factory.getInstance();

		// 3.banding security manager
		SecurityUtils.setSecurityManager(securityManager);

		// 4.obtain current subject --> current user --> anonymous user util
		// login at least once.
		Subject subject = SecurityUtils.getSubject();

		// 5.obtain session
		Session session = subject.getSession();
		session.setAttribute("somekey", "value");

		// 6.login
		if (!subject.isAuthenticated()) {
			// collect user principals and credentials in a gui specific manner
			// such as username/password html form, X509 certificate, OpenID,
			// etc.
			// We'll use the username/password example here sice it is the most
			// common.
			UsernamePasswordToken token = new UsernamePasswordToken(
					"lonestarr", "vespa");

			// remmenber me.
			token.setRememberMe(true);

			// login
			subject.login(token);

		}
	}
}
