package com.company;
public class Client {
    private String name;
    private String gen;
    private Programare programare;
    public Client() {
    }
    public Programare getProgramare() {
        return programare;
    }
    public void setProgramare(Programare programare) {
        this.programare = programare;
    }
    public Client(String name, String gen, Programare programare) {
        this.name = name;
        this.gen = gen;
        this.programare=programare;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGen() {
        return gen;
    }
    public void setGen(String gen) {
        this.gen = gen;
    }
    @Override
    public String toString() {
        return this.name+","+this.gen+","+this.programare.toString();
    }
    @Override
    public boolean equals(Object obj){
        Client client=(Client) obj;
        return
                this.getProgramare().getDataInceput().compareTo(client.getProgramare().getDataInceput())==0&&
                this.getProgramare().getDataSfarsit().compareTo(client.getProgramare().getDataSfarsit())==0;
    }
}
