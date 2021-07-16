package com.company;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Play {
    private Frizer frizer=new Frizer();
    public void play() throws IOException {
        boolean quit=false;
        meniu();
        Scanner scanner = new Scanner(System.in);
        while(quit !=true){
            int nr = scanner.nextInt();
            switch (nr) {
                case 1:
                    playFrizer();
                    break;
                case 2:
                    playClient();
                    break;
                case 3:
                    quit=true;
                default:
                    meniu();
            }
        }
    }
    private void playClient() throws IOException {
        Scanner scanner = new Scanner(System.in);
        meniuClient();
        int nr=scanner.nextInt();
        switch (nr){
            case 1:
                frizer.adaugareClient();
                frizer.save();
                break;
            case 2:
                frizer.modificareProgramare();
                frizer.save();
                break;
            default:
                meniuClient();

        }
    }
    private void playFrizer() throws IOException {
        Scanner scanner = new Scanner(System.in);
        meniuFrizer();
        int nr=scanner.nextInt();
        switch (nr){
            case 1:
                frizer.deletClient();
                frizer.save();
                break;
            case 2:
                frizer.modificareProgramare();
                frizer.save();
                break;
            case 3:
              frizer.afisareClientiZi();
                break;
        }
    }
    private void meniuFrizer(){
        System.out.println("Daca doresti sa stergi o programare apasati tasta 1");
        System.out.println("Daca doresti sa modifici o programare apasati tasta 2");
        System.out.println("Daca doresti sa vezi toata programarile din ziua de azi apasati tasta 3");
    }
    private void meniuClient(){
        System.out.println("Daca doresti sa iti faci o programare apasa tasta 1");
        System.out.println("Daca doresti sa schimbi data unei programari apasa tasta 2");
    }
    private void meniu(){
        System.out.println("Daca doriti sa accesati meniul frizerului apasati tasta 1");
        System.out.println("Daca sunteti client apasati tasta 2");
        System.out.println("Daca doriti sa iesiti din meniu apasati tasta 3");
    }
}
