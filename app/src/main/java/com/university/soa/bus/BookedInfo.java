package com.university.soa.bus;

/**
 * Created by pkumar on 5/13/18.
 */

public class BookedInfo {
    String name;
    String Pnumber;
    String empcode;
    String pasnumber;
    String name2;
    String name3;
    String name4;
    public BookedInfo(){

    }

    public String getPnumber() {
        return Pnumber;
    }

    public void setPnumber(String pnumber) {
        Pnumber = pnumber;
    }

    public String getPasnumber() {
        return pasnumber;
    }

    public void setPasnumber(String pasnumber) {
        this.pasnumber = pasnumber;
    }
    public String getName() {
        return name;
    }
    public String getEmpcode() {
        return empcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public BookedInfo(String name,String name2) {
        this.name = name;
        this.name2=name2;
        /*this.name3=name3;
        this.name4=name4;
        this.empcode=empcode;*/
       // this.empcode = empcode;

    }
}
