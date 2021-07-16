package frizerie.controll;

import frizerie.model.Programare;
import frizerie.model.SQLUTIL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlProgramareTest {
    ControlProgramare controlProgramare=new ControlProgramare();
    @Test
    void adaugareProgramare() {
        Programare programare=new Programare("2021-10-01","09:00",30,"Georgiana","Andu");
        assertEquals(true,controlProgramare.adaugareProgramare(programare));
    }
    @Test
    void deletProgramare() {
        controlProgramare.deletProgramare("2021-01-01","09:00","Bogdan");
    }
    @Test
    void returnareProgramare(){
        Programare p= controlProgramare.returnareProgramare("2021-01-01","12:00","Bogdan");
        System.out.println(p);
    }
    @Test
    void verificareProgramare(){
        Programare programare=new Programare("2021-10-01","09:00",30,"Georgiana","Andu");
        assertEquals(true,controlProgramare.verificareProgramare(programare));
    }

}