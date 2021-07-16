package frizerie.controll;
import frizerie.model.*;

import frizerie.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.Scanner;
public class ControlProgramare {
    private SQLUTIL sqlutil=new SQLUTIL();
    private List<Programare> listaProgramari=sqlutil.listaProgramari();
    private ControlClient controlClient=new ControlClient();
    private List<Frizer> listaFrizeri=sqlutil.listaFrizeri();
    private List<Concediu> listaConcedii=sqlutil.listaConcedii();
    public void addProgramare(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Datele despre dumneavoastra in ordinea:nume,prenume,nrTelefon");
        String[] date=scanner.nextLine().split(",");
        System.out.println("Introduceti ora la care doriti programarea cu informatiile in aceasta ordine:data,ora,durata,numeFrizer");
        String[] informatii=scanner.nextLine().split(",");
        Programare p=new Programare(informatii[0],informatii[1],Integer.parseInt(informatii[2]),date[0],informatii[3]);
        if(adaugareProgramare(new Programare(informatii[0],informatii[1],Integer.parseInt(informatii[2]),date[0],informatii[3]))==true) {
            controlClient.adaugareClient(new Client(date[0], date[1], Integer.parseInt(date[2])));
        }
    }
    public void deleteProgramare(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti programarea pe care doriti sa o stergeti:data,ora,frizer");
        String[] programare=scanner.nextLine().split(",");
        deletProgramare(programare[0],programare[1],programare[2]);
    }
    public void updateProgramare(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti detaliile programari pe care doriti sa o midifciati:data,ora,frizer");
        String[] prop=scanner.nextLine().split(",");
        sqlutil.updateProgramare(returnareProgramare(prop[0],prop[1],prop[2]));
    }
    public boolean adaugareProgramare(Programare programare){
        if(verificareProgramare(programare)==false){
            Frizer f1=returnareFrizer(programare.getNumeFrizer());
            Concediu concediu=returnareConcediu(programare.getNumeFrizer());
            if(concediu==null){
                sqlutil.insertTabel(programare);
                return true;
            }else{
                if((programare.getDataInceput().toLocalDate().isAfter(concediu.getDataSfarsit())&&programare.getDataInceput().toLocalDate().compareTo(concediu.getDataInceput())>0)){
                    sqlutil.insertTabel(programare);
                    return true;
                }
            }
        } else{
            System.out.println("Frizerul:" + programare.getNumeFrizer()+",se afla in concediu in acea perioada");
            return false;
        }
        return false;
    }
    public void deletProgramare(String data,String ora,String frizer){
        if(verificareExistenta(returnareProgramare(data,ora,frizer))==true){
            sqlutil.deleteTable(returnareProgramare(data,ora,frizer));
            System.out.println("Felicitari ai anulat programarea cu succes");
        }else{
            System.out.println("Nu s-a putut sterge aceasta programare");
        }
    }
    public boolean verificareProgramare(Programare programare){

        for(Programare p:listaProgramari){
            if(programare.getDataInceput().getDayOfMonth()==p.getDataInceput().getDayOfMonth()&&programare.getDataInceput().getMonth()==programare.getDataInceput().getMonth()){
                if(p.disponibilitate(programare)==true){
                    return false;
                }else{
                    return true;
                }
            }else{
                return false;
            }

        }
        return true;
    }
    public Programare returnareProgramare(String data,String ora,String frizer){
        for(Programare p:listaProgramari) {
            if(p.getDataInceput().compareTo(LocalDateTime.of(LocalDate.parse(data), LocalTime.parse(ora)))==0&&frizer.equals(p.getNumeFrizer())==true){
                return p;
            }
        }
        return null;
    }
    public boolean verificareExistenta(Programare programare){
        for(Programare p:listaProgramari){
            if(p.getDataInceput().compareTo(programare.getDataInceput())==0&&p.getNuemClient().equals(programare.getNuemClient())
                    &&p.getNumeFrizer().equals(programare.getNumeFrizer())){
                return  true;
            }
        }
        return false;
    }
    public Frizer returnareFrizer(String nume){
        for(Frizer f:listaFrizeri){
            if(f.getFirstName().equals(nume)){
                return f;
            }
        }
        return null;
    }
    public Concediu returnareConcediu(String name){
        for(Concediu c:listaConcedii){
            if(c.getFirstName().equals(name)==true){
                return c;
            }
        }
        return null;
    }
}