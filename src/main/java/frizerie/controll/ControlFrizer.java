package frizerie.controll;
import frizerie.model.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
public class ControlFrizer {
    private SQLUTIL sqlutil=new SQLUTIL();
    private List<Frizer> frizeri=sqlutil.listaFrizeri();
    private List<Programare> listaProgramari=sqlutil.listaProgramari();
    private List<Concediu> listaConcedii=sqlutil.listaConcedii();
    private HashMap<String,Integer> listaClienti=new HashMap<>();
    public void addFrizer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti datele noului frizer:nume,prenume,telefon");
        String[] dateFrizer=scanner.nextLine().split(",");
        addFrizer(new Frizer(dateFrizer[0],dateFrizer[1],Integer.parseInt(dateFrizer[2])));
    }
    public void deleteFrizer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti numele si prenumele frizerului");
        String[] frizer=scanner.nextLine().split(",");
        deleteFrizer(frizer[0],frizer[1]);
    }
    public void addFrizer(Frizer frizer){
        if(verificareFrizer(frizer.getFirstName(),frizer.getLastName())==false){
            sqlutil.insertTabel(frizer);
        }else{
            System.out.println("Nu am reusit sa adaugam in tabelul frizer");
        }
    }
    public void deleteFrizer(String nume,String prenume){
        if(verificareFrizer(nume,prenume)==true){
            sqlutil.deleteTable(returnareFrizer(nume,prenume));
        }else{
            System.out.println("Acest frizer nu se aflaa in baza noastra de date");
        }
    }
    public void addConcediu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti in aceasta ordine detaliile despre dumneavoastra:nume,prenume,data,Nrzile");
        String[] prop=scanner.nextLine().split(",");
        if(alegereConcediu(prop[0],prop[1],prop[2],Integer.parseInt(prop[3]))==false){
            System.out.println("Aveti deja un concediu setat");
        }else{
            System.out.println("Felicitari sunteti liber in acea data");
        }
    }
    public void afisareClientiZi(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti detaliile despre frizre:nume,prenume,data");
        String[] prop=scanner.nextLine().split(",");
        afisareClientiSaptamana(prop[0],prop[1],prop[2]);
    }
    public void updateFrizer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti datele despre frizer:nume,prenume");
        String[] prop=scanner.nextLine().split(",");
        sqlutil.updatePersoana(returnareFrizer(prop[0],prop[1]));
    }
    public void updateConcediu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti datele despre frizer:nume,prenume");
        String[] prop=scanner.nextLine().split(",");
        sqlutil.updateConcediu(returnareConcediu(prop[0],prop[1]));
    }
    public void afisareClientiFrizer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti datele despre frizer:nume,prenume");
        String[] prop=scanner.nextLine().split(",");
        afisareClientiFrizer(prop[0],prop[1]);
    }
    private void afisareClientiSaptamana(String nume,String prenume,String data){
        LocalDate localDate=LocalDate.parse(data);
        if(verificareFrizer(nume,prenume)==true){
            for(Programare p:listaProgramari){
                if(p.getDataInceput().getYear()==localDate.getYear()&&p.getDataInceput().getMonth()==localDate.getMonth()&&p.getDataInceput().getDayOfMonth()==localDate.getDayOfMonth()){
                    System.out.println(p+","+p.getNuemClient());
                }
            }
        }
    }
    void afisareClientiFrizer(String nume, String prenume){
        int i=1;
        if(verificareFrizer(nume,prenume)==true){
            for(Programare p:listaProgramari){
                if(p.getNumeFrizer().equals(nume)){
                    if(verificareNumeClient(p.getNuemClient())==false){
                        listaClienti.put(p.getNuemClient(),1);
                    }else{
                        listaClienti.replace(p.getNuemClient(),i++);
                    }
                }
            }
        }
        System.out.println(listaClienti);
    }
    public boolean verificareNumeClient(String nume) {
        Set<String> ket = listaClienti.keySet();
        Iterator iterator = ket.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(nume) == true) {
                return true;
            }
        }
        return false;

    }
    private boolean verificareFrizer(String nume,String prenume){
        for(Frizer f:frizeri){
            if(f.getFirstName().equals(nume)&&f.getLastName().equals(prenume))
                return true;
        }
        return false;
    }
    private Frizer returnareFrizer(String nume,String prenume){
        for(Frizer f:frizeri){
            if(f.getFirstName().equals(nume)&&f.getLastName().equals(prenume)){
                return f;
            }
        }
        return null;
    }
    protected boolean alegereConcediu(String name,String lastName,String data,int zile){
        if(listaClienti.isEmpty()){
            sqlutil.insertTabel(new Concediu(name,lastName,data,zile));
            return true;
        }else{

        }    for(Concediu c:listaConcedii){
            if(c.getFirstName().equals(name)){
                sqlutil.insertTabel(new Concediu(name,lastName,data,zile));
                return true;
            }else{

                return false;
            }
        }

        return false;
    }
    private Concediu returnareConcediu(String fristName,String lastName){
        for(Concediu c:listaConcedii){
            if(c.getFirstName().equals(fristName)&&c.getLastName().equals(lastName)){
                return c;
            }
        }
        return null;
    }



}