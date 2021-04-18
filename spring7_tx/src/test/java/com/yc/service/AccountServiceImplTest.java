package com.yc.service;

import com.yc.tx.Accounts;
import com.yc.tx.AppConfig;
import com.yc.tx.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.service.AccountService;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AccountServiceImplTest extends TestCase {

    @Autowired
    private AccountService accountService;

    @Test
    public void testOpenAccount() {
        Integer accountid = accountService.openAccount(new Accounts(),1);
        System.out.println(accountid);
        Assert.assertNotNull(accountid);
    }

    @Test
    public void testDeposite() {
        Accounts a = new Accounts();
        a.setAccountId(6);
        Accounts aa = accountService.deposite(a,100, OpTypes.deposite.getName(),null);
        System.out.println(aa);
    }

    @Test
    public void testWithdraw() {
        Accounts a = new Accounts();
        a.setAccountId(6);
        Accounts aa = accountService.withdraw(a,999, OpTypes.withdraw.getName(),null);
        System.out.println(aa);
    }

    @Test
    public void testTransfer() {
        Accounts out = new Accounts();
        out.setAccountId(6);

        Accounts inA = new Accounts();
        inA.setAccountId(7);

        this.accountService.transfer(inA,out,5);
    }

    @Test
    public void testShowBalance() {
        Accounts a = new Accounts();
        a.setAccountId(6);
        a = this.accountService.showBalance(a);
        System.out.println(a);
    }

    @Test
    public void testFindById() {
        Accounts a = new Accounts();
        a.setAccountId(6);
        List<OpRecord>  list = this.accountService.findById(a);
        System.out.println(list);
        System.out.println(list.size());
    }
}