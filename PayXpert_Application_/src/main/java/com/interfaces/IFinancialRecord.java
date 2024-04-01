package com.interfaces;

import java.util.List;

import com.model.FinancialRecord;

public interface IFinancialRecord {
    void addFinancialRecord(FinancialRecord financialRecord);
    FinancialRecord getFinancialRecordById(int financialRecordId);
    List<FinancialRecord> getAllFinancialRecords();
    void removeFinancialRecord(int financialRecordId);
}
