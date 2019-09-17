package br.com.tests;

import br.com.model.dao.DB;
import br.com.model.dao.DaoFactory;
import br.com.model.entities.Departamento;
import br.com.model.entities.Vendedor;
import br.com.model.impl.VendedorDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
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
        Departamento departamento = new Departamento(1L,"Sexy Shop");
        Vendedor vendedor = new Vendedor("Brunna Surfistinha","surfistinha@gmail.com",
                sdf.parse("12/10/2000"),new BigDecimal(1200_0),new BigDecimal(0.1),departamento);
        impl.cadastrar(vendedor);
    }

}
