package com.yc.spring_boot1.tx.service;



import com.yc.spring_boot1.tx.Accounts;
import com.yc.spring_boot1.tx.OpRecord;

import java.util.List;

public interface AccountService {

    //开户
    public Integer openAccount(Accounts account, double money);

    //存钱
    public Accounts deposite(Accounts account, double money, String optype, String transferid);

    //取钱
    public Accounts withdraw(Accounts account, double money, String optype, String transferid);

    //转账
    public Accounts transfer(Accounts inAccount, Accounts outAccount, double money);

    //查看余额
    public Accounts showBalance(Accounts account);

    //查看日志
    public List<OpRecord> findById(Accounts account);


    //Accounts deposite(Accounts a, int i, String name, Object o);
}
