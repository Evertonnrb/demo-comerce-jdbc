package br.com.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Vendedor implements Serializable {

    private Long id;
    private String nome;
    private String email;
    private Date dataNascimento;
    private BigDecimal salario;
    private BigDecimal comissao;
    private Departamento departamento;

    public Vendedor() {
    }

    public Vendedor(Long id, String nome, String email, Date dataNascimento, BigDecimal salario, BigDecimal comissao, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.comissao = comissao;
        this.departamento = departamento;
    }

    public Vendedor(String nome, String email, Date dataNascimento, BigDecimal salario, BigDecimal comissao, Departamento departamento) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.comissao = comissao;
        this.departamento = departamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor that = (Vendedor) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(email, that.email) &&
                Objects.equals(dataNascimento, that.dataNascimento) &&
                Objects.equals(salario, that.salario) &&
                Objects.equals(comissao, that.comissao) &&
                Objects.equals(departamento, that.departamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, dataNascimento, salario, comissao, departamento);
    }

    @Override
    public String toString() {
        return "Vendedores{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", salario=" + salario +
                ", comissao=" + comissao +
                ", departamento=" + departamento +
                '}';
    }
}
