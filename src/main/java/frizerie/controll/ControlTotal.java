package frizerie.controll;

import frizerie.model.Client;
import frizerie.model.Frizer;
import frizerie.model.Programare;
import frizerie.model.SQLUTIL;

import java.util.Scanner;
public class ControlTotal {
    private ControlProgramare controlProgramare=new ControlProgramare();
    private ControlClient controlClient=new ControlClient();
    private ControlFrizer controlFrizer=new ControlFrizer();
    public void play(){
        meniuFinal();
        Scanner scanner=new Scanner(System.in);
        int nr=scanner.nextInt();
        switch (nr){
            case 1:
                playClient();
                break;
            case 2:
                playAdmin();
                break;
            case 3:
                playFrizer();
                break;
        }
    }
    private void playClient(){
        Scanner scanner = new Scanner(System.in);
        meniuClient();
        boolean quit=false;
        while (quit==false){
            int nr=scanner.nextInt();

            switch (nr){
                case 1:
                    controlProgramare.addProgramare();
                    break;
                case 2:
                    controlProgramare.updateProgramare();
                    break;
                case 3:
                    controlProgramare.deleteProgramare();
                    break;
                case 4:
                    quit=true;
                    break;

            }
        }
    }
    private void playFrizer(){
        Scanner scanner = new Scanner(System.in);
        boolean quit=false;
        meniuFrizer();
        while (quit==false){
            int nr=scanner.nextInt();

            switch (nr){
                case 1:
                    controlFrizer.afisareClientiZi();
                    break;
                case 2:
                    controlFrizer.addConcediu();
                    break;
                case 3:
                    quit=true;
                    break;
            }
        }
    }
    private void playAdmin(){
        Scanner scanner = new Scanner(System.in);
        boolean quit=false;
        meniuAdmin();
        while (quit==false) {
            int nr = scanner.nextInt();
            switch (nr){
                case 1:
                    controlFrizer.addFrizer();
                    break;
                case 2:
                    controlFrizer.deleteFrizer();
                    break;
                case 3:
                    controlFrizer.updateFrizer();
                    break;
                case 4:
                    controlProgramare.updateProgramare();
                    break;
                case 5:
                    controlClient.updateClient();
                    break;
                case 6:
                    controlFrizer.afisareClientiFrizer();
                    break;
                case 7:
                    controlFrizer.updateConcediu();
                case 8:
                    quit=true;
                    break;
            }
        }
    }
    private void meniuClient(){
        System.out.println("Daca doresti sa iti faci o programare apasa tasta 1");
        System.out.println("Daca doresti sa modifici o programare existenta apasa tasta 2");
        System.out.println("Daca doresti sa anulezi o programare apasa tasta 3");
        System.out.println("Daca doresti sa iesi din meniu apasa tasta 4");
    }
    private void meniuAdmin(){
        System.out.println("Daca doresti sa adaugi un frizer apasa tasta 1");
        System.out.println("Daca doresti sa concediezi un frizer apasa tasta 2");
        System.out.println("Daca doresti sa modifici datele unui frizer apasa tasta 3");
        System.out.println("Daca doresti sa modifici o programare apasa tasta 4");
        System.out.println("Daca doresti sa modifici datele unui client apasa tasta 5");
        System.out.println("Daca doresti sa aflii detalii despre clientii unui anumit frizer apasa tasta 6");
        System.out.println("Daca doresti sa modifici un concediu apasa tasta 7");
        System.out.println("Daca doresti sa iesi din meniu apasa tasta 8");
    }
    private void meniuFrizer(){
        System.out.println("Daca doresti sa vezi programarile dintr-o anumita zi apasa tasta 1");
        System.out.println("Daca doresti sa iti alegi perioada de concediu apasa tasta 2");
        System.out.println("Daca doresti sa iesi din meniu apasa tasta 3");

    }
    private  void meniuFinal(){
        System.out.println("Daca esti client apasa tasta 1");
        System.out.println("daca esti admin apasa tasta 2");
        System.out.println("Daca esti frizer apasa tasta 3");
    }

}
