package com.yc.spring_boot1.tx.dao;



import com.yc.spring_boot1.tx.Accounts;

import java.util.List;

public interface AccountsDao {

    public int saveAccount(Accounts account);

    public Accounts updateAccount(Accounts account);

    public Accounts findAccount(int accountid);

    public List<Accounts> findAll();

    public void delete(int accountid);
}
