package ru.informpr.cartridges.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CartridgesSections {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Byte access;
    private String code, name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getAccess() {
        return access;
    }

    public void setAccess(Byte access) {
        this.access = access;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
