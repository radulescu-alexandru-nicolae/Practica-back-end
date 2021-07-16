package frizerie.model;
public class Persoana {
    private String firstName;
    private String lastName;
    private int nrTelefon;
    public Persoana(String firstName, String lastName, int nrTelefon) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nrTelefon = nrTelefon;
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
    public int getNrTelefon() {
        return nrTelefon;
    }
    public void setNrTelefon(int nrTelefon) {
        this.nrTelefon = nrTelefon;
    }
    @Override
    public String toString() {
        return "Persoana{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nrTelefon=" + nrTelefon +
                '}';
    }
}