package com.company;

import static org.junit.jupiter.api.Assertions.*;

class FrizerTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void adaugareClienti() {
        Frizer frizer=new Frizer();
        frizer.adaugareClienti(new Client("Mihalex","M",new Programare("2021-02-01","12:00",30)));
    }
    @org.junit.jupiter.api.Test
    void disponibilitate1(){
        Programare This=new Programare("2021-01-01","12:00",30);
        Programare p1=new Programare("2021-01-01","12:30",30);
        assertEquals(false,This.disponibilitate(p1));
        Programare p2=new Programare("2021-01-01","09:15",30);
        Programare p3=new Programare("2021-01-01","09:45",5);
        assertEquals(false,p2.disponibilitate(p3));
        Programare p4=new Programare("2021-01-01","13:15",30);
        Programare p5=new Programare("2021-01-01","12:45",5);
        assertEquals(false,p4.disponibilitate(p5));
    }
    @org.junit.jupiter.api.Test
    void disponibilitate2(){
        Programare p1=new Programare("2021-01-01","12:00",30);
        Programare p2=new Programare("2021-01-01","12:30",30);
        assertEquals(false,p1.disponibilitate(p2));

        Programare p3=new Programare("2021-01-01","11:59",1);
        Programare p4=new Programare("2021-01-01","12:00",2);
        assertEquals(false,p3.disponibilitate(p4));


        Programare p5=new Programare("2021-01-01","23:59",1);
        Programare p6=new Programare("2021-01-01","01:00",2);
        assertEquals(false,p5.disponibilitate(p6));
    }
    @org.junit.jupiter.api.Test
    void disponibilitate3(){
        Programare p1=new Programare("2021-01-01","12:00",30);
        Programare p2=new Programare("2021-01-01","12:29",30);
        assertEquals(false,p1.disponibilitate(p2));

        Programare p3=new Programare("2021-01-01","11:00",30);
        Programare p4=new Programare("2021-01-01","11:14",30);
        assertEquals(false,p3.disponibilitate(p4));

        Programare p5=new Programare("2021-01-01","12:00",30);
        Programare p6=new Programare("2021-01-01","12:01",30);
        assertEquals(false,p5.disponibilitate(p6));
    }
    @org.junit.jupiter.api.Test
    void disponibilitate4(){
        Programare p1=new Programare("2021-01-01","12:00",30);
        Programare p2=new Programare("2021-01-01","12:01",28);
        assertEquals(false,p1.disponibilitate(p2));

        Programare p3=new Programare("2021-01-01","11:49",60);
        Programare p4=new Programare("2021-01-01","12:25",10);
        assertEquals(false,p3.disponibilitate(p4));

        Programare p5=new Programare("2021-01-01","09:45",35);
        Programare p6=new Programare("2021-01-01","09:50",25);
        assertEquals(false,p5.disponibilitate(p6));

    }
    @org.junit.jupiter.api.Test
    void disponibilitate5(){
        Programare p1=new Programare("2021-01-01","12:00",30);
        Programare p2=new Programare("2021-01-01","12:15",15);
        assertEquals(false,p1.disponibilitate(p2));

        Programare p3=new Programare("2021-01-01","09:45",25);
        Programare p4=new Programare("2021-01-01","09:55",15);
        assertEquals(false,p3.disponibilitate(p4));

        Programare p5=new Programare("2021-01-01","10:00",25);
        Programare p6=new Programare("2021-01-01","10:01",24);
        assertEquals(false,p5.disponibilitate(p6));
    }
    @org.junit.jupiter.api.Test
    void disponibilitate6(){
        Programare p1=new Programare("2021-01-01","12:00",30);
        Programare p2=new Programare("2021-01-01","12:00",28);
        assertEquals(false,p1.disponibilitate(p2));

        Programare p3=new Programare("2021-01-01","11:59",30);
        Programare p4=new Programare("2021-01-01","11:59",29);
        assertEquals(false,p3.disponibilitate(p4));

        Programare p5=new Programare("2021-01-01","12:39",11);
        Programare p6=new Programare("2021-01-01","12:39",10);
        assertEquals(false,p5.disponibilitate(p6));
    }
}