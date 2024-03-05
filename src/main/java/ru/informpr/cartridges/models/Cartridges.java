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
    private String prefix, model, section;
    private String printers, properties; //json

    public String getProperties() {
        return properties;
    }
//    public Map<String, Object> getProperties() {
//        JSONObject json = new JSONObject(properties);
//        return json.toMap();
//    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

//    public void setProperties(Map<String, String> properties) {
//        JSONObject json = new JSONObject(properties);
//        this.properties = json.toString();
//    }

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
//    public String[] getPrinters() {
//        JSONArray json = new JSONArray(printers);
//        Stri] result = new String[json.length()];
//
//        for (int i = 0; i < json.length(); i++) {
//            result[i] = json.getString(i);
//        }
//
//        return result;
//    }

    public void setPrinters(String printers) {
        this.printers = printers;
    }

//    public void setPrinters(String[] printers) {
//        List<String> list = new ArrayList<>();
//
//        for (int i = 0; i < printers.length; i++) {
//            if (printers[i].isEmpty()) {
//                continue;
//            }
//
//            list.add(i, printers[i]);
//        }
//
//        JSONArray json = new JSONArray(list);
//
//        this.printers = json.toString();
//    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
