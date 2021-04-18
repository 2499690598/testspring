package com.yc.dao;

import com.yc.tx.AppConfig;
import com.yc.tx.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.dao.OpRecordDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestOpRecordDao {

    @Autowired
    private OpRecordDao accountsDao;

    @Test
    public void testSave() {
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(1);
        opRecord.setOpmoney(1.0);
        opRecord.setOptype(OpTypes.deposite.getName());     //用枚举作这个值（约束值），不容易出错
        opRecord.setOptime(new Timestamp(new Date().getTime()));    //这里是一个Timestamp  new Date().getTime() 取得一个Long
        opRecord.setTransferid(" ");    //因为只是一个简单的存钱操作，没有transferid
        accountsDao.saveOpRecord(opRecord);
    }

    @Test
    public void testAll() {
        List<OpRecord> list = accountsDao.findAll();
        //断言查出来的日志长度不会为0
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public void testByAccountId() {
        List<OpRecord> list = accountsDao.findByAccountid(1);
        Assert.assertNotEquals(0, list.size());
    }

}
