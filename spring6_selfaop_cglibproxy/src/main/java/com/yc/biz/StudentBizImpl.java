package com.yc.biz;

public class StudentBizImpl implements StudentBiz {

    public int add(String name) {
        System.out.println("调用了studentBizImpl中的add" + name);
        return 100;
    }

    public void update(String name) {
        System.out.println("调用了studentBizImpl中的update" + name);
    }

    public void find(String name) {
        System.out.println("调用了studentBizImpl中的find" + name);
    }
}
