package com.company;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Programare {
    private LocalDateTime dataInceput;
    private LocalDateTime dataSfarsit;
    private Duration durata;
    public Programare(String data,String ora,int durata) {
        dataInceput=LocalDateTime.of(LocalDate.parse(data), LocalTime.parse(ora));
        this.durata=Duration.ofMinutes(durata);
        dataSfarsit=dataInceput.plusMinutes(durata);
    }
    public boolean disponibilitate(Programare p1){
        if(p1.getDataInceput().getDayOfMonth()==this.getDataInceput().getDayOfMonth()){
            if(this.getDataSfarsit().compareTo(p1.getDataInceput())==0) return false;
            else if(p1.getDataSfarsit().compareTo(this.getDataInceput())==0) return false;
            else if(this.getDataSfarsit().isAfter(p1.getDataInceput())&&this.getDataSfarsit().isBefore(p1.getDataSfarsit())) return false;
            else if(this.getDataInceput().isBefore(p1.getDataInceput())&&this.getDataSfarsit().isAfter(p1.getDataSfarsit())) return false;
            else if(this.getDataInceput().isBefore(p1.getDataInceput())&&this.getDataSfarsit().compareTo(p1.getDataSfarsit())==0) return false;
            else if(this.getDataInceput().compareTo(p1.getDataInceput())==0&&this.getDataSfarsit().isAfter(p1.getDataSfarsit())) return false;
        }
        return true;
    }
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyy-MM-dd,HH:mm");
        return dataInceput.format(dateTimeFormatter)+","+durata+","+dataSfarsit.format(dateTimeFormatter);
    }
    public LocalDateTime getDataInceput() {
        return dataInceput;
    }
    public void setDataInceput(LocalDateTime dataInceput) {
        this.dataInceput = dataInceput;
    }
    public LocalDateTime getDataSfarsit() {
        return dataSfarsit;
    }
    public void setDataSfarsit(LocalDateTime dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }
    public Duration getDurata() {
        return durata;
    }
    public void setDurata(Duration durata) {
        this.durata = durata;
    }
    public int returnData(){
        return this.getDataInceput().getDayOfMonth();
    }


}
