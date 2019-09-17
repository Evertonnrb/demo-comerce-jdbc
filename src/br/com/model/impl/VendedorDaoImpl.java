package br.com.model.impl;

import br.com.controller.excpetions.BDException;
import br.com.model.dao.DB;
import br.com.model.dao.GenericDao;
import br.com.model.entities.Departamento;
import br.com.model.entities.Vendedor;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class VendedorDaoImpl implements GenericDao<Vendedor> {

    private Connection conn;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st = null;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public VendedorDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void cadastrar(Vendedor vendedor) {
        try {
            pst = conn.prepareStatement("INSERT INTO vendedores (nome,email,data_nascimento,salario,comissao,id_departamento)" +
                    "values (?,?,?,?,?,?)");
            conn.setAutoCommit(false);
            pst.setString(1, vendedor.getNome());
            pst.setString(2, vendedor.getEmail());
            pst.setDate(3, new Date(vendedor.getDataNascimento().getTime()));
            pst.setBigDecimal(4, vendedor.getSalario());
            pst.setBigDecimal(5, vendedor.getComissao());
            pst.setLong(6, vendedor.getDepartamento().getId());
            pst.execute();
            conn.commit();
        } catch (SQLException e) {
            throw new BDException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }


    @Override
    public Vendedor atualizar(Long obj) {
        return null;
    }

    @Override
    public boolean remover(Long obj) {
        return false;
    }

    @Override
    public Vendedor buscarPorCod(Long obj) {
        StringBuilder builder = new StringBuilder();
        builder.append("select vendedores.*, departamentos.nome as nome_departamento, departamentos.id as id_departamento\n" +
                "\tfrom vendedores inner join departamentos\n" +
                "\ton vendedores.id = departamentos.id\n" +
                "\twhere vendedores.id = ?");
        try {
            pst = conn.prepareStatement(builder.toString());
            pst.setLong(1, obj);
            rs = pst.executeQuery();
            if (rs.next()) {
                Vendedor v = createVendedor(rs);
                return v;
            }
            return null;
        } catch (SQLException e) {
            throw new BDException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pst);

        }

    }

    @Override
    public List<Vendedor> listarTodos() {
        return null;
    }

    @Override
    public List<Vendedor> buscarPorNome(String obj) {
        return null;
    }

    private Departamento createDepartamento(ResultSet rs) throws SQLException {
        Departamento departamento = new Departamento();
        departamento.setId(rs.getLong("id_departamento"));
        departamento.setNome(rs.getString("nome_departamento"));
        return departamento;
    }

    private Vendedor createVendedor(ResultSet rs) throws SQLException {
        Departamento departamento = createDepartamento(rs);
        Vendedor vendedor = new Vendedor();
        vendedor.setId(rs.getLong("id"));
        vendedor.setNome(rs.getString("nome"));
        vendedor.setEmail(rs.getString("email"));
        vendedor.setComissao(rs.getBigDecimal("comissao"));
        vendedor.setSalario(rs.getBigDecimal("salario"));
        vendedor.setDataNascimento(rs.getDate("data_nascimento"));
        vendedor.setDepartamento(departamento);
        return vendedor;
    }
}
