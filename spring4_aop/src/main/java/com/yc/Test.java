package com.yc;

import com.yc.biz.StudentBizImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
//        MyApplicationContext ac = new MyAnnotationConfigApplicationContext(MyAppConfig.class);
//        HelloWorld hw = (HelloWorld) ac.getBean("hw");
//        hw.show();

        ApplicationContext ac = new AnnotationConfigApplicationContext(MyAppConfig.class);
        StudentBizImpl hw = (StudentBizImpl) ac.getBean("studentBizImpl");
        hw.add("abc");
    }
}

