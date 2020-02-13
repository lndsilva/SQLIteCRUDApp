package br.com.sp.senac.sqlitecrudapp;

public class Empregado {

    int id;
    String nome, depto, dataEntrada;
    double salario;

    public Empregado(int id, String nome, String depto, String dataEntrada, double salario) {
        this.id = id;
        this.nome = nome;
        this.depto = depto;
        this.dataEntrada = dataEntrada;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDepto() {
        return depto;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public double getSalario() {
        return salario;
    }
}
