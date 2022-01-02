package com.parking.demo.models;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private int id;
    private String nom;
    public String nbPlacesLibre;
    private String position;
    private String adresse;
    private String nbPlacesTotal;
    private int ratio;
    private String type;
    private String etat;

    public Location(int id, String nom, String nbPlacesLibre, String nbPlacesTotal, String position, String adresse, String type, String etat) {
        this.id = id;
        this.nom = nom;
        this.nbPlacesLibre = nbPlacesLibre;
        this.nbPlacesTotal = nbPlacesTotal;
        this.position = position;
        this.adresse = adresse;
        this.type = type;
        this.etat = etat;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("id=").append(id);
        sb.append(", nomParking=").append(id);
        sb.append(", placesLibre=").append(nbPlacesLibre).append('\'');
        sb.append(", placesTotal=").append(nbPlacesTotal).append('\'');
        sb.append(", position=").append(position).append('\'');
        sb.append(", adresse=").append(adresse).append('\'');
        sb.append(", type=").append(type).append('\'');
        sb.append(", etat").append(etat);
        sb.append('}');

        Gson gson = new Gson();
        String json = gson.toJson(sb.toString());
        return json;
    }
}
