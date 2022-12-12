package com.example.kursah_v2_0;

public class TableData {
    private String ID, Number, Name;

    public TableData(String Number, String Name, String ID){
        this.ID=ID;
        this.Number=Number;
        this.Name=Name;
    }

    public String getID(){ return ID;}
    public void setID(String value){ this.ID=value;}


    public String getNumber(){ return Number;}
    public void setNumber(String value){ this.Number=value;}

    public String getName(){ return Name;}
    public void setName(String value){ this.Name=value;}
}
