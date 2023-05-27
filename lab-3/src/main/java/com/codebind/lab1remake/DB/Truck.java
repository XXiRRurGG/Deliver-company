package com.codebind.lab1remake.DB;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@Table(name = "truck")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "truckNumber", "brand", "cargoID" })
public class Truck {
    @Id
    public String truckNumber;
    public String brand;

    public Long cargoId;

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }
}
