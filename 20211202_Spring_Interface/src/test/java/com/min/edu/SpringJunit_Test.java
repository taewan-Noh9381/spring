package com.min.edu;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.model.IDao;
import com.min.edu.vo.EduVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class SpringJunit_Test {

	/* ApplicationContext
	 * DispatherServlet에 의해서 Bean으로 등록되는 과정이 아닌
	 * @ContextConfiguration의 Annotation을 통해서 생성된 Bean을 사용하기 위한 객체
	 */
	@Autowired
	private ApplicationContext context;
	
	/*
	 * bean중에서 root-context.xml에 의해서 생성된 SqlSessionTemplate bean을 DI
	 */
	@Autowired
	private SqlSessionTemplate session;
	
	/*
	 * servlet-context.xml의 <context:component-scan의 basae-package="com.min.edu">에 의해서
	 * base-package 하위에 있는 org.springframwork.stereotype인 @Component @Controller @Service @Repository를 통해서
	 * 생성된 Bean
	 * OCP 구조에 의해서 
	 * DaoImpl dao = new DaoImpl(); 이어야 하지만 implements 구조의 다형성 발생에 의해서 부모의 이름으로 자식을 호출할 수 있는 형태의
	 * IDao dao = new DaoImpl();로 기능을 동작시킨다
	 */
	@Autowired
	private IDao dao;
	
	@Test
	public void test() {
//		assertNotNull(context);
//		assertNotNull(session);
		
//		assertNotEquals(0, dao.selectBoard().size());
		
		/*
		 * 시나리오
		 * selectkey에 의해서 자동으로 seq가 입력 Parameter인 setSeq
		 */
		EduVo vo = new EduVo();
		vo.setId("USER0B");
		vo.setTitle("TEST jUnit");
		assertEquals(1, dao.insertBoard(vo));
	}

}
