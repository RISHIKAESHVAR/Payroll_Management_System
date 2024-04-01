package com.service;

import java.util.List;

import com.dao.FinancialRecordServiceDao;
import com.interfaces.IFinancialRecord;
import com.model.FinancialRecord;

public class FinancialRecordService implements IFinancialRecord {

    private final FinancialRecordServiceDao financialRecordDao;

    public FinancialRecordService(FinancialRecordServiceDao financialRecordServiceDao) {
        this.financialRecordDao = financialRecordServiceDao;
    }

	@Override
    public void addFinancialRecord(FinancialRecord financialRecord) {
        financialRecordDao.addFinancialRecord(financialRecord);
    }
    
    @Override
    public FinancialRecord getFinancialRecordById(int financialRecordId) {
        return financialRecordDao.getFinancialRecordById(financialRecordId);
    }

    @Override
    public List<FinancialRecord> getAllFinancialRecords() {
        return financialRecordDao.getAllFinancialRecords();
    }

    @Override
    public void removeFinancialRecord(int financialRecordId) {
        financialRecordDao.removeFinancialRecord(financialRecordId);
    }
}

