package frizerie.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramareTest {
   SQLUTIL sqlutil=new SQLUTIL();

    @Test
    void getNumeFrizer(){
        List<Programare> programareList=new ArrayList<>();
        programareList=sqlutil.listaProgramari();
        int i=0;
       for(Programare p:programareList){
           String nume=p.getNuemClient();
           System.out.println(nume);
       }
    }

}