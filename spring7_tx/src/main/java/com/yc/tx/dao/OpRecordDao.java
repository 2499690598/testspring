package com.yc.tx.dao;

import com.yc.tx.OpRecord;

import java.util.List;

public interface OpRecordDao {

    public void saveOpRecord(OpRecord opRecord);

    public List<OpRecord> findAll();

    public List<OpRecord> findByAccountid(int accountid);
}
