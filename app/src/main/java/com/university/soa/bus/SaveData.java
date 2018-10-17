package com.university.soa.bus;

public class SaveData {
    String name;
    String empcode;

    public SaveData(){
    //Required for firebase
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

    public SaveData(String name, String empcode) {
        this.name = name;
        this.empcode = empcode;
    }
}
