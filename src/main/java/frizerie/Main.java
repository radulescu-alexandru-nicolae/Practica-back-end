package frizerie;

import frizerie.controll.ControlTotal;
import frizerie.model.Concediu;
import frizerie.model.SQLUTIL;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       ControlTotal controlTotal=new ControlTotal();
       controlTotal.play();
    }
}
