package frizerie.controll;
import frizerie.model.Client;
import frizerie.model.SQLUTIL;
import java.util.List;
import java.util.Scanner;
public class ControlClient {
    private SQLUTIL sqlutil=new SQLUTIL();
    private List<Client> clienti= sqlutil.listaClienti();
    protected boolean verficiareClient(String nume,String prenume){
        for(Client c:clienti){
            if(c.getFirstName().equals(nume)&&c.getLastName().equals(prenume)==true){
                return true;
            }
        }
        return false;
    }
    public void adaugareClient(Client client){
        if(verficiareClient(client.getFirstName(),client.getLastName())==false){
            sqlutil.insertTabel(client);
        }else{
            System.out.println("Aceasta persoana deja se afla in baza noastra de date");
        }
    }
    public void updateClient(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti datele despre client:nume,prenume");
        String[] prop=scanner.nextLine().split(",");
        sqlutil.updatePersoana(returnareClient(prop[0],prop[1]));
    }
    public void deleteClient(String nume,String prenume){
        if(verficiareClient(nume,prenume)==true){
            sqlutil.deleteTable(returnareClient(nume,prenume));
        }else{
            System.out.println("Aceasta persaoana nu se afla in baza noastra de date");
        }
    }
    private Client returnareClient(String nume,String prenume){
        for(Client c:clienti){
            if(c.getFirstName().equals(nume)&&c.getLastName().equals(prenume)==true){
                return c;
            }
        }
        return null;
    }



}
