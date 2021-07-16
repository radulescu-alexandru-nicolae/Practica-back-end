package com.company;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
public class Frizer {
private Set<Client> listaProgramari=new TreeSet<>(new ComparatorOre());
    public Frizer() {
        read();
    }
    public void adaugareClient(){
    System.out.println("Introduceti informatiile despre dumneavoastra in ordinea aceasta:nume,gen,an-zi-luna,ora:minut,timp");
    Scanner scanner = new Scanner(System.in);
    String[] informatii=scanner.nextLine().split(",");
    Client client=new Client(informatii[0],informatii[1],new Programare(informatii[2],informatii[3],Integer.parseInt(informatii[4])));
    listaProgramari.add(client);
}
public void modificareProgramare(){
    System.out.println("Introduceti-va numele pentru a verifica daca aveti o programare");
    Scanner scanner = new Scanner(System.in);
    String name=scanner.nextLine();
    if(verificareClient(returClient(name))==true){
        Client client=returClient(name);
        System.out.println(client);
        deleteClient(returClient(name));
        System.out.println("Introduceti noua ora la care doriti programarea");
        String[] ora=scanner.nextLine().split(",");
        Client client1=new Client(name,client.getGen(),new Programare(ora[0],ora[1],Integer.parseInt(ora[2])));
        adaugareClienti(client1);
    }else{
        System.out.println("Nu aveti o programare activa");
    }
}
public void deleteClient(Client client){
        if(verificareClient(client)==true){
            listaProgramari.remove(client);
        }
}
public void deletClient(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Introduceti numele clientului");
    String name=scanner.nextLine();
    if(verificareClient(returClient(name))==true){
        listaProgramari.remove(returClient(name));
    }else{
        System.out.println("Nu este nimeni cu acest nume in lista de programari");
    }
}
public void read(){
    File file=new File("E:\\Frizerie\\src\\com\\company\\Programari");
    String aux="";
    try{
        Scanner scanner =new Scanner(file);
        while(scanner.hasNext()){
            String[] prop=scanner.nextLine().split(",");
            Duration durata= Duration.parse(prop[4]);
            Client client=new Client(prop[0],prop[1],new Programare(prop[2],prop[3],(int)durata.toMinutes()));
            adaugareClienti(client);
        }
    }catch (FileNotFoundException e){
        e.printStackTrace();
    }
}
public void save() throws IOException {
    File file = new File("E:\\Frizerie\\src\\com\\company\\Programari");
    FileWriter fileWriter=new FileWriter(file);
    String aux="";
    Iterator iterator=listaProgramari.iterator();
    while(iterator.hasNext()){
    Client client=(Client) iterator.next();
    aux+=client.toString()+"\n";
    }
    PrintWriter printWriter=new PrintWriter(fileWriter);
    printWriter.print(aux);
    printWriter.close();
}
public void adaugareClienti(Client client){
   listaProgramari.add(client);
}
public boolean verificareClient(Client client){
    for(Client c:listaProgramari){
        if(listaProgramari.contains(c)==false){
            return false;
        }
    }
    return true;
}
public Client returClient(String name){
        Iterator iterator=listaProgramari.iterator();
        while(iterator.hasNext()){
            Client client=(Client) iterator.next();
            if(client.getName().equals(name)){
                return client;
            }
        }
        return null;
    }
    public void afisare(){
        System.out.println(listaProgramari);
    }
    public void afisareClientiZi(){
        System.out.println("Introduceti data");
        Scanner scanner = new Scanner(System.in);
        String data= scanner.nextLine();
        LocalDate localDate=LocalDate.parse(data);
        afisareProgramariZi(localDate);
    }
    public void afisareProgramariZi(LocalDate data){
        Iterator iterator=listaProgramari.iterator();
        while (iterator.hasNext()){
            Client client=(Client) iterator.next();
            if(client.getProgramare().getDataInceput().getDayOfMonth()==data.getDayOfMonth()&&
                    client.getProgramare().getDataInceput().getMonth()==data.getMonth()&&
                    client.getProgramare().getDataInceput().getYear()==data.getYear()){
                System.out.println(client);
            }
        }
    }
}
