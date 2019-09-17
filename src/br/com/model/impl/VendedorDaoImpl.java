package br.com.model.impl;

import br.com.model.dao.GenericDao;

import java.util.List;

public class VendedorDaoImpl implements GenericDao<VendedorDaoImpl> {

    @Override
    public void cadastrar(VendedorDaoImpl obj) {

    }

    @Override
    public VendedorDaoImpl atualizar(Long obj) {
        return null;
    }

    @Override
    public boolean remover(Long obj) {
        return false;
    }

    @Override
    public VendedorDaoImpl buscarPorCod(Long obj) {
        return null;
    }

    @Override
    public List<VendedorDaoImpl> listarTodos() {
        return null;
    }

    @Override
    public List<VendedorDaoImpl> buscarPorNome(String obj) {
        return null;
    }
}
