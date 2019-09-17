package br.com.model.dao;

import br.com.model.impl.VendedorDaoImpl;

public class DaoFactory {

    public static VendedorDaoImpl createDaoVendedor(){
        return new VendedorDaoImpl();
    }
}
