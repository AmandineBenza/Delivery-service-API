package com.lama.dsa.model.Client;

public class Client {

    private long id;
    private int telNumber;
    private String name;
    private String address;

    public Client(long id, String name, String address, int number) {
        this.id = id;
        this.telNumber = number;
        this.name = name;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getNumber() {
        return telNumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLastName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(int number) {
        this.telNumber = number;
    }

}
