package frizerie.model;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class SQLUTIL {
    private String JDBCurl = "jdbc:mysql://localhost:3306/frizerie?autoReconnect=true&uSSL=false";
    private String username = "root";
    private String password = "root";
    private Connection connection = null;
    private Statement statement = null;
    public SQLUTIL() {
        try {
            connection = DriverManager.getConnection(JDBCurl, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void executeStatement(String execute) {
        try {
            statement.execute(execute);

        } catch (SQLException e) {
            System.out.println("Nu am reusit:" + execute);
        }
    }
    public void insertTabel(Object obj) {
        String insertTo = "";
        if (obj instanceof Programare) {
            Programare programare = (Programare) obj;
            insertTo += "insert into programare(DataInceput,DataSfarsit,numeClient,numeFrizer) values(";
            insertTo += String.format("'%s','%s','%s','%s'", programare.getDataInceput(), programare.getDataSfarsit(), programare.getNuemClient(), programare.getNumeFrizer());
            insertTo += ")";
            executeStatement(insertTo);
        } else if (obj instanceof Frizer){
            Frizer frizer = (Frizer) obj;
            insertTo += "insert into frizer(firstName,lastName,numarTelefon) values(";
            insertTo += String.format("'%s','%s','%d'", frizer.getFirstName(), frizer.getLastName(), frizer.getNrTelefon());
            insertTo += ")";
            executeStatement(insertTo);
        } else if (obj instanceof Client){
            Client client = (Client) obj;
            insertTo += "insert into client(firstName,lastName,numarTelefon) values(";
            insertTo += String.format("'%s','%s','%d'", client.getFirstName(), client.getLastName(), client.getNrTelefon());
            insertTo += ")";
            executeStatement(insertTo);
        }else if(obj instanceof Concediu){
            Concediu concediu=(Concediu) obj;
            insertTo+="insert into concediu(firstName,lastName,DataInceput,DataSfarsit) values(";
            insertTo+=String.format("'%s','%s','%s','%s'",concediu.getFirstName(),concediu.getLastName(),concediu.getDataInceput(),concediu.getDataSfarsit());
            insertTo+=")";
            executeStatement(insertTo);
        }
    }
    public void deleteTable(Object object) {
        String delete = "";
        if (object instanceof Programare) {
            Programare programare = (Programare) object;
            delete += String.format("delete from programare where numeClient='%s'", programare.getNuemClient());
            executeStatement(delete);
        } else if (object instanceof Client) {
            Client client = (Client) object;
            delete += String.format("delete from client where firstName='%s' and lastName = '%s'", client.getFirstName(), client.getLastName());
            executeStatement(delete);
        } else if (object instanceof Frizer) {
            String deleteProgramari="";
            Frizer frizer = (Frizer) object;
            deleteProgramari+=String.format("delete from programare where numeFrizer='%s'",frizer.getFirstName());
            executeStatement(deleteProgramari);
            delete += String.format("delete from frizer where firstName='%s' and lastName='%s'", frizer.getFirstName(), frizer.getLastName());
            executeStatement(delete);
            String deleteConcediu="";
            deleteConcediu+=String.format("delete from concediu where firstName='%s' and lastName='%s'",frizer.getFirstName(),frizer.getLastName());
            executeStatement(deleteConcediu);
        }
    }
    public void updatePersoana(Object obj) {
        String update="";
        if(obj instanceof Client) {
            update+= "update client set ";
        }else{
            update+="update frizer set";
        }
        System.out.println("Introduceti detaliile pe care doriti sa le schimbati separate prin virgula:firstName,lastName,numarTelefon");
        Scanner scanner = new Scanner(System.in);
        String[] prop = scanner.nextLine().split(",");
        String nume="";
        for (String s : prop) {
            switch (s) {
                case "firstName":
                    System.out.println("Introduceti primul nume");
                    nume = scanner.nextLine();
                    if(prop[0].equals("firstName")) {
                        update += String.format(" firstName='%s'", nume);
                    }else{
                        update += String.format(",firstName='%s'", nume);
                    }
                    break;
                case "lastName":
                    System.out.println("Introduceti al doilea nume");
                    String nume2 = scanner.nextLine();
                    if(prop[0].equals("lastName")==true){
                        update+=String.format("lastName='%s'",nume2);

                    }else {

                        update += String.format(",lastName='%s'", nume2);
                    }
                    break;
                case "numarTelefon":
                    System.out.println("Introduceti numarul de telefon");
                    int nr = scanner.nextInt();
                    if(prop[0].equals("numarTelefon")==true) {
                        update += String.format("numarTelefon='%d'", nr);
                    }else{
                        update+=String.format(",numarTelefon='%d'",nr);
                    }
                    break;
            }
        }
        if(obj instanceof Frizer){
            Frizer frizer=(Frizer) obj;
            update += String.format("where firstName='%s' and lastName='%s'",frizer.getFirstName(),frizer.getLastName());
            executeStatement(String.format("update programare set numeFrizer='%s' where numeFrizer='%s'",nume,frizer.getFirstName()));
        }else{
            Client client=(Client) obj;
            update += String.format("where firstName='%s' and lastName='%s'",client.getFirstName(),client.getLastName());
        }
        executeStatement(update);
    }
    public void updateProgramare(Programare programare) {
        Scanner scanner = new Scanner(System.in);
        Programare aux=programare;
        String update="update programare set ";
        System.out.println("Introduceti datele pe care doriti sa le modificati:DataInceput,DataSfarsit,numeClient,numeFrizer");
        String[] prop=scanner.nextLine().split(",");
        for(String s:prop){
            switch (s){
                case "DataInceput":
                    System.out.println("Introduceti data in aceasta forma:yyy-mm-dd,HH:mm");
                    String data=scanner.nextLine();
                    if(prop[0].equals("DataInceput")==true) {
                        update += String.format("DataInceput='%s'", data);
                    }else{
                        update += String.format(",DataInceput='%s'", data);
                    }
                    break;
                case "DataSfarsit":
                    System.out.println("Introduceti data in aceasta forma:yyy-mm-dd,HH:mm");
                    String dataSf=scanner.nextLine();
                    if(prop[0].equals("DataSfarsit")==true){
                        update += String.format("DataSfarsit='%s'", dataSf);
                    }else{
                        update += String.format(",DataSfarsit='%s'", dataSf);
                    }
                    break;
                case "numeClient":
                    System.out.println("Introduceti numele clientului");
                    String nume=scanner.nextLine();
                    if(prop[0].equals("numeClient")==true){
                        update+=String.format("numeClient='%s'",nume);
                    }else{
                        update+=String.format(",numeClient='%s'",nume);
                    }
                    break;
                case "numeFrizer":
                    System.out.println("Introduceti numele frizerului");
                    String numeFrizer=scanner.nextLine();
                    if(prop[0].equals("numeFrizer")==true) {
                        update += String.format("numeFrizer='%s'", numeFrizer);
                    }else{
                        update += String.format(",numeFrizer='%s'", numeFrizer);
                    }
                    break;
            }
        }
        update += String.format("where DataInceput='%s' and numeClient='%s' and numeFrizer='%s'", aux.getDataInceput(),aux.getNuemClient(),aux.getNumeFrizer());
        executeStatement(update);
    }
    public void updateConcediu(Concediu concediu){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti datele pe care doriti sa le schimbati:firstName,lastName,DataInceput,DataSfarsit");
        String[] prop=scanner.nextLine().split(",");
        String update="update concediu set ";
        for(String s: prop){
            switch (s){
                case "firstName":
                    System.out.println("Introduceti numele frizerului");
                    String nume=scanner.nextLine();
                    if(prop[0].equals("firstName")==true){
                        update += String.format("firstName='%s'",nume);
                    }else{
                        update+=String.format(",firstName='%s'",nume);
                    }
                    break;
                case "lastName":
                    System.out.println("Introduceti prenumele frizerului");
                    String prenume=scanner.nextLine();
                    if(prop[0].equals("lastName")==true){
                        update+=String.format("lastName='%s'",prenume);
                    }else{
                        update+=String.format(",lastName='%s'",prenume);

                    }
                    break;
                case "DataInceput":
                    System.out.println("Introduceti data sub forma aceasta:yyy-MM-dd");
                    String data=scanner.nextLine();
                    if(prop[0].equals("DataInceput")==true){
                        update+=String.format("DataInceput='%s'",data);
                    }else{
                        update+=String.format(",DataInceput='%s'",data);

                    }
                    break;
                case "DataSfarsit":
                    System.out.println("Introduceti data sub forma aceasta:yyy-MM-dd");
                    String sfarsit=scanner.nextLine();
                    if(prop[0].equals("DataSfarsit")==true){
                        update+=String.format("DataSfarsit='%s'",sfarsit);
                    }else{
                        update+=String.format(",DataSfarsit='%s'",sfarsit);

                    }break;
            }
        }
        update+=String.format("where firstName='%s' and lastName='%s'",concediu.getFirstName(),concediu.getLastName());
        executeStatement(update);

    }
    private ResultSet allClienti() {
        executeStatement("select*from client");
        try {
            return statement.getResultSet();
        } catch (Exception e) {
            System.out.println("Nu s-a executat");
            return null;

        }
    }
    private ResultSet allConcedii(){
        executeStatement("select*from concediu");
        try{
            return statement.getResultSet();
        }catch (Exception e){
            System.out.println("Nu s-a executat");
            return null;
        }
    }
    private ResultSet allFrizeri() {
        executeStatement("select*from frizer");
        try {
            return statement.getResultSet();
        } catch (Exception e) {
            System.out.println("Nu s-a executat");
            return null;
        }
    }
    private ResultSet allProgramari() {
        executeStatement("select*from programare");
        try {
            return statement.getResultSet();
        } catch (Exception e) {
            System.out.println("Nu s-a executat");
            return null;
        }
    }
    public List<Client> listaClienti() {
        ResultSet set = allClienti();
        List<Client> clienti = new ArrayList<>();
        try {
            while (set.next()) {
                clienti.add(new Client(set.getString(2), set.getString(3), Integer.parseInt(set.getString(4))));
            }
            return clienti;
        } catch (Exception e) {
            System.out.println("Nu s-a creat lista");
            return null;
        }
    }
    public List<Frizer> listaFrizeri() {
        ResultSet set = allFrizeri();
        List<Frizer> frizeri = new ArrayList<>();
        try {
            while (set.next()) {
                frizeri.add(new Frizer(set.getString(2), set.getString(3), Integer.parseInt(set.getString(4))));
            }
            return frizeri;
        } catch (Exception e) {
            System.out.println("Nu s-a creat lista");
            return null;
        }
    }
    public List<Programare> listaProgramari() {
        ResultSet set = allProgramari();
        List<Programare> lista = new ArrayList<>();
        try {
            while (set.next()){
                DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyy-MM-dd,HH:mm");
                LocalDateTime datainceput=LocalDateTime.of(LocalDate.parse(set.getString(2).split(" ")[0]),LocalTime.parse(set.getString(2).split(" ")[1]));
                LocalDateTime datasfarsit=LocalDateTime.of(LocalDate.parse(set.getString(3).split(" ")[0]),LocalTime.parse(set.getString(3).split(" ")[1]));
                String data1=datainceput.format(dateTimeFormatter);
                String data2=datasfarsit.format(dateTimeFormatter);
                lista.add(new Programare(data1.split(",")[0],data1.split(",")[1],(int)ChronoUnit.MINUTES.between(datainceput,datasfarsit),set.getString(4),set.getString(5)));

            }
            return lista;
        }catch(Exception e){
            System.out.println("Nu s-a realizat lista");
            return null;
        }
    }
    public List<Concediu> listaConcedii(){
        ResultSet set=allConcedii();
        List<Concediu> concedii=new ArrayList<>();
        try{
            while (set.next()){
                LocalDate dataInceput=LocalDate.parse(set.getString(4).split(" ")[0]);
                int nrzile=(int)ChronoUnit.DAYS.between(dataInceput,LocalDate.parse(set.getString(5).split(" ")[0]));
                concedii.add(new Concediu(set.getString(2),set.getString(3),set.getString(4).split(" ")[0],nrzile));
            }
            return concedii;
        }catch (Exception e){
            System.out.println("Nu s-a creat lista");
            return null;
        }
    }
}