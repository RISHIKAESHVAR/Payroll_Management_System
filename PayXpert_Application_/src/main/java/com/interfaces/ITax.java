package com.interfaces;

import java.util.List;

import com.model.Tax;

public interface ITax {
    void addTax(Tax tax);
    void updateTax(Tax tax);
    Tax getTaxById(int taxId);
    List<Tax> getAllTaxes();
    void removeTax(int taxId);
}