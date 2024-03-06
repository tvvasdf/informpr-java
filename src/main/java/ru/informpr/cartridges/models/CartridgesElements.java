package ru.informpr.cartridges.models;

import jakarta.persistence.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

@Entity
public class CartridgesElements {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true)
    private Integer id;

    @Column(unique=true)
    private String code;
    private Byte access;
    private String prefix, model, section;
    private String printers, properties; //json

    public Map<String, Object> getProperties() {
        JSONObject json = new JSONObject(properties);
        return json.toMap();
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String[] getPrinters() {
        JSONArray json = new JSONArray(printers);
        String[] result = new String[json.length()];

        for (int i = 0; i < json.length(); i++) {
            result[i] = json.getString(i);
        }

        return result;
    }

    public void setPrinters(String[] printers) {
        List<String> list = new ArrayList<>();

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

    public Byte getAccess() {
        return access;
    }

    public void setAccess(Byte access) {
        this.access = access;
    }
}
