package org.example;

public class Item {
    private String id;
    private String name;
    private String type;
    private String description;

    public Item() {}

    public Item(String id, String name, String type, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
}
