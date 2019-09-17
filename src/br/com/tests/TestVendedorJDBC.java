package br.com.tests;

import br.com.model.dao.DaoFactory;
import br.com.model.entities.Departamento;
import br.com.model.entities.Vendedor;
import br.com.model.impl.VendedorDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestVendedorJDBC {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    VendedorDaoImpl impl = new DaoFactory().createDaoVendedor();

    @Test
    public void buscarVendedor() {
        Vendedor v = impl.buscarPorCod(3L);
        System.out.println(impl.buscarPorCod(3L));
    }

    @Test
    public void deveCadastrarSomeBoby() throws ParseException {
        Departamento departamento = new Departamento(1L, "Sexy Shop");
        Vendedor vendedor = new Vendedor("Brunna Surfistinha", "surfistinha@gmail.com",
                sdf.parse("12/10/2000"), new BigDecimal(1200_0), new BigDecimal(0.1), departamento);
        impl.cadastrar(vendedor);
    }

    @Test
    public void deveAtualiarVendedor() throws ParseException {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(1L);
        vendedor.setNome("Eliza S. F. Bolsonaro");
        vendedor.setEmail("eliza_silva@hotmail.com");
        vendedor.setDataNascimento(sdf.parse("12/12/2001"));
        vendedor.setSalario(new BigDecimal(2.000));
        vendedor.setComissao(new BigDecimal(0.12));
        Departamento departamento = new Departamento();
        departamento.setId(2L);
        vendedor.setDepartamento(departamento);
        impl.atualizar(vendedor);
        //Assert.assertTrue(impl.atualizar(vendedor));
    }

    @Test
    public void deveRemoverVendedor() {
        impl.remover(9L);
    }

    @Test
    public void listarVendedores() {
        for (Vendedor v : impl.listarTodos()) {
            System.out.println(v.toString());
        }
    }

    @Test
    public void deveBuscarPorNome() {
        String nome = "R";
        for (Vendedor v : impl.buscarPorNome(nome)) {
            System.out.println("Rodando o teste");
            System.out.println(v.toString());
        }
    }

    @Test
    public void deveAtualizarSalario() {
        Vendedor vendedor = impl.buscarPorCod(1L);
        impl.atualizarSalario(vendedor, new BigDecimal(300));
    }
}
