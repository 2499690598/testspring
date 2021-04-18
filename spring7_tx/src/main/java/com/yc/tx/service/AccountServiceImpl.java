package com.yc.tx.service;

import com.yc.tx.Accounts;
import com.yc.tx.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.dao.AccountsDao;
import com.yc.tx.dao.OpRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = false,
        timeout = 100,
        rollbackForClassName = {"RuntimeException"})
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountsDao accountsDao;

    @Autowired
    private OpRecordDao opRecordDao;

    @Override
    public Integer openAccount(Accounts account, double money) {
        account.setBalance(money);
        int accountid = accountsDao.saveAccount(account);
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(1);
        opRecord.setOpmoney(1.0);
        opRecord.setOptype(OpTypes.deposite.getName());
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));
        opRecord.setTransferid(" ");
        opRecordDao.saveOpRecord(opRecord);
        return accountid;
    }

    @Override
    public Accounts deposite(Accounts account, double money, String optype, String transferid) {
        Accounts a = this.showBalance(account);

        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(a.getAccountId());
        opRecord.setOpmoney(money);
        opRecord.setOptype(optype);     //用枚举做这个值（约束值），不容易出错
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));  //这里是一个Timestamp
        if (transferid == null) {
            opRecord.setTransferid(" ");
        } else {
            opRecord.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(opRecord);

        a.setBalance(a.getBalance() + money);
        accountsDao.updateAccount(a);
        return a;
    }

    @Override
    public Accounts withdraw(Accounts account, double money, String optype, String transferid) {
        Accounts a = this.showBalance(account);

        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(a.getAccountId());
        opRecord.setOpmoney(money);
        opRecord.setOptype(optype);     //用枚举做这个值（约束值），不容易出错
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));  //这里是一个Timestamp
        if (transferid == null) {
            opRecord.setTransferid(" ");
        } else {
            opRecord.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(opRecord);

        a.setBalance(a.getBalance() - money);
        accountsDao.updateAccount(a);
        return a;
    }

    @Override
    public Accounts transfer(Accounts inAccount, Accounts outAccount, double money) {
        String uid = UUID.randomUUID().toString();  //转账流水
        this.deposite(inAccount, money, OpTypes.transfer.getName(), uid);
        Accounts newAccounts = this.withdraw(outAccount, money, OpTypes.transfer.getName(), uid);
        return newAccounts;
    }

    @Override
    @Transactional(readOnly = true)
    public Accounts showBalance(Accounts account) {
        return accountsDao.findAccount(account.getAccountId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpRecord> findById(Accounts account) {
        return opRecordDao.findByAccountid(account.getAccountId());
    }
}
