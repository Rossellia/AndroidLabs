package com.example.myapplication;

public class DataStructure {
    String name;
    String type;
    String object_number;
    String feature;

    public DataStructure(String name, String type, String object_number, String feature ) {
        this.name=name;
        this.type=type;
        this.object_number=object_number;
        this.feature=feature;

    }
    public void additem(String name, String type, String object_number, String feature ) {
        this.name=name;
        this.type=type;
        this.object_number=object_number;
        this.feature=feature;

    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getVersion_number() {
        return object_number;
    }

    public String getFeature() {
        return feature;
    }

}
