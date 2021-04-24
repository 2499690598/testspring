package com.yc.spring_boot1.controller;

import com.yc.spring_boot1.tx.Accounts;
import com.yc.spring_boot1.tx.OpRecord;
import com.yc.spring_boot1.tx.bean.OpTypes;
import com.yc.spring_boot1.tx.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.List;

@RestController
@RequestMapping
public class Controller {

    @Autowired
    private AccountService accountService;
    
    @RequestMapping("/test")
    public void getArticleById(){
        System.out.println("tzzssb");
    }

    @RequestMapping("/open")
    public String OpenAccount() {
        Integer accountid = accountService.openAccount(new Accounts(), 1);
        System.out.println(accountid);
        return new Gson().toJson(accountid);
    }

    @RequestMapping("/depo")
    public void Deposite(@RequestBody Accounts account) {
        Accounts aa = accountService.deposite(account, account.getBalance(), OpTypes.deposite.getName(), null);
    }

    @RequestMapping("/with")
    public void Withdraw(@RequestBody Accounts account) {
        Accounts aa = accountService.withdraw(account, account.getBalance(), OpTypes.withdraw.getName(), null);
    }

    @RequestMapping("/tran")
    public void Transfer(@RequestParam("inAccount")int inAccountId,@RequestParam("outAccount")int outAccountId,@RequestParam("money")Double money) {
        Accounts out = new Accounts();
        out.setAccountId(outAccountId);

        Accounts inA = new Accounts();
        inA.setAccountId(inAccountId);

        this.accountService.transfer(inA, out, money);
    }

    @RequestMapping("/show")
    public String ShowBalance(@RequestParam("checkAccount")int checkAccountId) {
        Accounts a = new Accounts();
        a.setAccountId(checkAccountId);
        a = this.accountService.showBalance(a);
        return new Gson().toJson(a);
    }

    @RequestMapping("/find")
    public String FindById(@RequestParam("checkAccount")int checkAccountId) {
        Accounts a = new Accounts();
        a.setAccountId(checkAccountId);
        List<OpRecord> list = this.accountService.findById(a);
        System.out.println(list);
        System.out.println(list.size());
        return new Gson().toJson(list);
    }
}
