package br.com.model.impl;

import br.com.controller.excpetions.BDException;
import br.com.model.dao.DB;
import br.com.model.dao.GenericDao;
import br.com.model.entities.Departamento;
import br.com.model.entities.Vendedor;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VendedorDaoImpl implements GenericDao<Vendedor> {

    private Connection conn;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public VendedorDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void cadastrar(Vendedor vendedor) {
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO vendedores (nome,email,data_nascimento,salario,comissao,id_departamento)" +
                    "values (?,?,?,?,?,?)");
            pst.setString(1, vendedor.getNome());
            pst.setString(2, vendedor.getEmail());
            pst.setDate(3, new Date(vendedor.getDataNascimento().getTime()));
            pst.setBigDecimal(4, vendedor.getSalario());
            pst.setBigDecimal(5, vendedor.getComissao());
            pst.setLong(6, vendedor.getDepartamento().getId());
            pst.execute();
            DB.closeStatement(pst);
        } catch (SQLException e) {
            throw new BDException(e.getMessage());
        } finally {
            DB.closeConnection();
        }
    }


    @Override
    public boolean atualizar(Vendedor vendedor) {
        StringBuilder builder = new StringBuilder();
        builder.append("update vendedores set nome = ?, email = ?, data_nascimento = ?, salario = ?,");
        builder.append(" comissao = ?, id_departamento = ? where id = ?");
        try (PreparedStatement pst = conn.prepareStatement(builder.toString())) {
            pst.setString(1, vendedor.getNome());
            pst.setString(2, vendedor.getEmail());
            pst.setDate(3, new Date(vendedor.getDataNascimento().getTime()));
            pst.setBigDecimal(4, vendedor.getSalario());
            pst.setBigDecimal(5, vendedor.getComissao());
            pst.setLong(6, vendedor.getDepartamento().getId());
            pst.setLong(7, vendedor.getId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new BDException(e.getMessage());
        }
    }

    @Override
    public boolean remover(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from vendedores where id = ?");
        try (PreparedStatement pst = conn.prepareStatement(sql.toString())) {
            pst.setLong(1, id);
            return pst.execute();
        } catch (SQLException e) {
            throw new BDException(e.getMessage());
        }
    }


    @Override
    public Vendedor buscarPorCod(Long obj) {
        StringBuilder builder = new StringBuilder();
        builder.append("select vendedores.*, departamentos.nome as nome_departamento, departamentos.id as id_departamento\n" +
                "\tfrom vendedores inner join departamentos\n" +
                "\ton vendedores.id = departamentos.id\n" +
                "\twhere vendedores.id = ?");
        try {
            PreparedStatement pst = conn.prepareStatement(builder.toString());
            pst.setLong(1, obj);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Departamento departamento = createDepartamento(rs);
                Vendedor v = createVendedor(rs);
                v.setDepartamento(departamento);
                return v;
            }
            return null;
        } catch (SQLException e) {
            throw new BDException(e.getMessage());
        }
    }

    /**
     * TODO implementar o select dos departamentos
     *
     * @return
     */
    @Override
    public List<Vendedor> listarTodos() {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from vendedores");
        try (PreparedStatement pst = conn.prepareStatement(sql.toString())) {
            List<Vendedor> vendedores = new ArrayList<>();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Vendedor vendedor = createVendedor(rs);
                vendedores.add(vendedor);
            }
            return vendedores;
        } catch (SQLException e) {
            throw new BDException(e.getMessage());
        }
    }

    @Override
    public List<Vendedor> buscarPorNome(String nome) {
        StringBuilder sql = new StringBuilder();
        sql.append("select nome from vendedores where nome like ?");
        try (PreparedStatement pst = conn.prepareStatement(sql.toString())) {
            List<Vendedor> vendedores = new ArrayList<>();
            pst.setString(1, "'%"+nome+"%'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Vendedor vendedor = createVendedor(rs);
                vendedores.add(vendedor);
            }
            return vendedores;
        } catch (SQLException e) {
            throw new BDException(e.getMessage());
        }
    }

    public boolean atualizarSalario(Vendedor vendedor,BigDecimal valor){
        StringBuilder sql = new StringBuilder();
        sql.append("update vendedores set salario = ? where id = ?");
        try(PreparedStatement pst = conn.prepareStatement(sql.toString())){
            pst.setBigDecimal(1, vendedor.getSalario().add(valor));
            pst.setLong(2,vendedor.getId());
            return pst.executeUpdate()>0;
        }catch (SQLException e){
            throw new BDException(e.getMessage());
        }
    }


    private Departamento createDepartamento(ResultSet rs) throws SQLException {
        Departamento departamento = new Departamento();
        departamento.setId(rs.getLong("id_departamento"));
        departamento.setNome(rs.getString("nome_departamento"));
        return departamento;
    }

    private Vendedor createVendedor(ResultSet rs) throws SQLException {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(rs.getLong("id"));
        vendedor.setNome(rs.getString("nome"));
        vendedor.setEmail(rs.getString("email"));
        vendedor.setComissao(rs.getBigDecimal("comissao"));
        vendedor.setSalario(rs.getBigDecimal("salario"));
        vendedor.setDataNascimento(rs.getDate("data_nascimento"));
        return vendedor;
    }



}
