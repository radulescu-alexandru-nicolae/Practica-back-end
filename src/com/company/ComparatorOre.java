package com.company;
import java.util.Comparator;
public class ComparatorOre implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        Client p1=(Client) o1;
        Client p2=(Client) o2;
        return p1.getProgramare().getDataInceput().compareTo(p2.getProgramare().getDataInceput());

    }
}
