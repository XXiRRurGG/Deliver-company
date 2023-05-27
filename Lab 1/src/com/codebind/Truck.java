package com.codebind;

public class Truck {
    private String truckNumber;
    private String brand;
    private Cargo cargo;
    public Truck()
    {

    }
    public Truck(String truckNumber, String brand, Cargo cargo)
    {
        this.truckNumber = truckNumber;
        this.brand = brand;
        this.cargo = cargo;
    }
    public void ChangeValues(String truckNumber, String brand, Cargo cargo)
    {
        this.truckNumber = truckNumber;
        this.brand = brand;
        this.cargo = cargo;
    }
    public void Info()
    {
        System.out.println("Brand" + brand);
        System.out.println("Truck number" + truckNumber);
        System.out.println("Cargo info:");
        cargo.Info();
    }
    public String GetNumber()
    {
        return truckNumber;
    }
    public String GetBrand()
    {
        return brand;
    }
    public String toString()
    {
        return truckNumber;
    }
    public String TruckInfo()
    {
        String result = "Number:" + truckNumber + "\n" + "Brand:" + brand + "\n" + cargo.CargoInfo();
        return result;
    }
    public Cargo TruckCargo()
    {
        return cargo;
    }
}
