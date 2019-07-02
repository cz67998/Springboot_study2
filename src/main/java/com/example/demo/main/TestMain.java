package com.example.demo.main;

import com.example.demo.Configuration.TestConfiguration;
import com.example.demo.method.TestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/6/27
 * Time:10:51
 **/
public class TestMain {
    public static void main(String[] args) {

        // @Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        // 如果加载spring-context.xml文件：
        // ApplicationContext context = new
        // ClassPathXmlApplicationContext("spring-context.xml");
        /**
         * (1)、@Bean注解在返回实例的方法上，如果未通过@Bean指定bean的名称，则默认与标注的方法名相同；
         (2)、@Bean注解默认作用域为单例singleton作用域，可通过@Scope(“prototype”)设置为原型作用域；
         (3)、既然@Bean的作用是注册bean对象，那么完全可以使用@Component、@Controller、@Service、@Ripository等注解注册bean，当然需要配置@ComponentScan注解进行自动扫描。
         */
        TestBean tb=(TestBean) context.getBean("testBean");//testBean与使用@Bean注解的方法名一致
        tb.sayHello();
        //System.out.println(tb);
        //TestBean tb2 = (TestBean) context.getBean("testBean");
       // tb2.sayHello();
       // System.out.println(tb2);
    }
}
