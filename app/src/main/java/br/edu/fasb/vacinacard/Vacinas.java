package br.edu.fasb.vacinacard;

public class Vacinas {
    private String Vacina;

    public Vacinas(String vacina){
        this.Vacina = vacina;
    }

    public String getVacina() {
        return Vacina;
    }

    public void setVacina(String vacina) {
        Vacina = vacina;
    }

    @Override
    public String toString() {
        return this.Vacina;
    }
}
