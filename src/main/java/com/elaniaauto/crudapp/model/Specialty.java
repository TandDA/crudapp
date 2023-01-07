package com.elaniaauto.crudapp.model;

public class Specialty {
    private Integer id;
    private String specName;
    private Status status = Status.ACTIVE;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return specName;
    }

    public Status getStatus() {
        return status;
    }

    public Specialty() {

    }

    public Specialty(Integer id, String name) {
        this.id = id;
        this.specName = name;
        this.status = Status.ACTIVE;
    }



    public void setId(Integer id) {
        this.id = id;
    }


    public void setName(String name) {
        this.specName = name;
    }



    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", name=" + specName +
                ", status'" +status+ '\'' +
                '}';
    }
}
