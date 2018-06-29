package br.edu.fasb.vacinacard;

public class Usuario {
    private String Nome;
    private int Idade;
    private String Endereco;
    private String Mae;
    private String Pai;
    private String TipoSangue;
    private String Cpf;
    private String Senha;

    public Usuario(String nome, int idade, String endereco, String mae, String pai, String tiposangue, String cpf, String senha){
        this.Nome = nome;
        this.Idade = idade;
        this.Endereco = endereco;
        this.Mae = mae;
        this.Pai = pai;
        this.TipoSangue = tiposangue;
        this.Cpf = cpf;
        this.Senha = senha;

    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getIdade() {
        return Idade;
    }

    public void setIdade(int idade) {
        Idade = idade;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getMae() {
        return Mae;
    }

    public void setMae(String mae) {
        Mae = mae;
    }

    public String getPai() {
        return Pai;
    }

    public void setPai(String pai) {
        Pai = pai;
    }

    public String getTipoSangue() {
        return TipoSangue;
    }

    public void setTipoSangue(String tipoSangue) {
        TipoSangue = tipoSangue;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    @Override
    public String toString() {
        return this.Nome;
    }
}
