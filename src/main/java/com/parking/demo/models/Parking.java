package com.parking.demo.models;



import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Parking {
    @JsonValue
    private List<Location> locations;

    public Parking() {
    }

    @Override
    public String toString() {
       return "" + locations + "";
    }
}
