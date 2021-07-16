package frizerie.model;

import java.time.LocalDate;
import java.time.Period;

public class Concediu {
    private String firstName;
    private String lastName;
    private LocalDate dataInceput;
    private LocalDate dataSfarsit;
    private int nrZile;
    public Concediu(String firstName, String lastName,String dataInceput,int nrzile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dataInceput=LocalDate.parse(dataInceput);
        this.nrZile=nrzile;
        this.dataSfarsit=LocalDate.parse(dataInceput).plusDays(nrzile);
    }
    public LocalDate getDataSfarsit() {
        return dataSfarsit;
    }
    public void setDataSfarsit(LocalDate dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }

    public LocalDate getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(LocalDate dataInceput) {
        this.dataInceput = dataInceput;
    }

    public int getNrZile() {
        return nrZile;
    }

    public void setNrZile(int nrZile) {
        this.nrZile = nrZile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Concediu{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dataInceput=" + dataInceput +
                ", dataSfarsit=" + dataSfarsit +
                ", nrZile=" + nrZile +
                '}';
    }
}