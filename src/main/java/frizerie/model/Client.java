package frizerie.model;

import java.util.ArrayList;
import java.util.List;

public class Client extends Persoana{
    private List<Programare> programari=new ArrayList<>();
    public Client(String firstName, String lastName, int nrTelefon) {
        super(firstName, lastName, nrTelefon);
    }
    public List<Programare> getProgramari() {
        return programari;
    }
    public void setProgramari(List<Programare> programari) {
        this.programari = programari;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}