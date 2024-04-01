package com.service;

import java.util.List;

import com.dao.TaxServiceDao;
import com.interfaces.ITax;
import com.model.Tax;

public class TaxService implements ITax {
    private TaxServiceDao taxServiceDao;

    public TaxService(TaxServiceDao taxServiceDao) {
        this.taxServiceDao = taxServiceDao;
    }

	@Override
    public void addTax(Tax tax) {
        taxServiceDao.addTax(tax);
    }

    @Override
    public void updateTax(Tax tax) {
        taxServiceDao.updateTax(tax);
    }

    @Override
    public Tax getTaxById(int taxId) {
        return taxServiceDao.getTaxById(taxId);
    }

    @Override
    public List<Tax> getAllTaxes() {
        return taxServiceDao.getAllTaxes();
    }

    @Override
    public void removeTax(int taxId) {
        taxServiceDao.removeTax(taxId);
    }
}
