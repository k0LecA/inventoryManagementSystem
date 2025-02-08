package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ItemLibrary {
    private LinkedList<Item> items;

    public ItemLibrary() {
        items = new LinkedList<Item>();
    }

    public void LoadItems() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Item> itemList = mapper.readValue(new File("items.json"), new TypeReference<List<Item>>() {});
            items.addAll(itemList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public Item findItemById(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        System.out.println("item not found");
        return null;
    }
}
