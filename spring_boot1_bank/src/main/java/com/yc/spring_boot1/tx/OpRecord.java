package com.yc.spring_boot1.tx;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OpRecord {
    private Integer id;
    private Integer accountid;
    private Double opmoney;
    private Timestamp optime;
    private String optype;
    private String transferid;
}
