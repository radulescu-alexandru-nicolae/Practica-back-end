package frizerie.model;

import java.util.Comparator;

public class ComparatorOre implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Programare p1=(Programare) o1;
        Programare p2=(Programare) o2;
        return p1.getDataInceput().compareTo(p2.getDataInceput());
    }
}
