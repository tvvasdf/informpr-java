package ru.informpr.cartridges.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

@Entity
public class Cartridges {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String prefix;
    private String model;
    private String section;
    private String printers; //json
    private String properties; //json

    public String getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        JSONObject json = new JSONObject(properties);
        this.properties = json.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrinters() {
        return printers;
    }

    public void setPrinters(String[] printers) {
        List<Object> list = new ArrayList<>();

        for (int i = 0; i < printers.length; i++) {
            if (printers[i].isEmpty()) {
                continue;
            }

            list.add(i, printers[i]);
        }

        JSONArray json = new JSONArray(list);

        this.printers = json.toString();
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
