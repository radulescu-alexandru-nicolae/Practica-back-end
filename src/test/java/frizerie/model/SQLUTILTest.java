package frizerie.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SQLUTILTest {
    @Test
    void afisareConcediu(){
        SQLUTIL sqlutil=new SQLUTIL();
        List<Concediu> listaConcedii=sqlutil.listaConcedii();

        for(Concediu c:listaConcedii){
            System.out.println(c);
        }
    }
    @Test
    void updateFrizer(){

    }
}