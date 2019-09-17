package br.com.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

    void cadastrar(T obj);

    T atualizar(Long obj);

    boolean remover(Long obj);

    T buscarPorCod(Long obj);

    List<T> listarTodos();

    List<T> buscarPorNome(String obj);
}
