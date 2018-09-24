package com.lama.dsa.model.Client;

public class Client {

    private int id;
    private int number;
    private String name;
    private String address;

    public Client(int id, String name, String address, int number) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }

    public int getNumber() {
        return number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
