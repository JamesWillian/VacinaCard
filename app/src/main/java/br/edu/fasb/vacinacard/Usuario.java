package br.edu.fasb.vacinacard;

public class Usuario {
    private String Nome;
    private int Idade;
    private String Endereco;
    private String Mae;
    private String Pai;
    private String TipoSangue;

    public Usuario(String nome, int idade, String endereco, String mae, String pai, String tiposangue){
        this.Nome = nome;
        this.Idade = idade;
        this.Endereco = endereco;
        this.Mae = mae;
        this.Pai = pai;
        this.TipoSangue = tiposangue;
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

    @Override
    public String toString() {
        return this.Nome;
    }
}
