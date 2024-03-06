package ru.informpr.cartridges.models;

import jakarta.persistence.*;

@Entity
public class CartridgesSections {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique=true)
    private Integer id;
    @Column(unique=true)
    private String code;
    private Byte access;
    private String name, category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
