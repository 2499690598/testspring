package com.yc;

import com.yc.biz.StudentBiz;
import com.yc.biz.StudentBizImpl;

public class Test {
    public static void main(String[] args) {
        StudentBiz target = new StudentBizImpl();  //spring 进行ioc

        LogAspect la = new LogAspect(target);

        Object obj = la.createProxy();

        if (obj instanceof StudentBiz) {
            StudentBiz sb = (StudentBiz) obj;
            sb.find("张三");

            sb.add("李四");

            sb.update("王五");
        }
    }

}
