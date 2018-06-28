package br.edu.fasb.vacinacard;

public class Campanha {
    private String Campanha;
    private String Ano;

    public Campanha(String campanha, String ano){
        this.Campanha = campanha;
        this.Ano  = ano;
    }

    public String getCampanha() {
        return Campanha;
    }

    public void setCampanha(String campanha) {
        Campanha = campanha;
    }

    public String getAno() {
        return Ano;
    }

    public void setAno(String ano) {
        Ano = ano;
    }

    @Override
    public String toString() {
        return this.Campanha;
    }
}
