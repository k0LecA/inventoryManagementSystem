package org.example;

public class Slot {
    Item item;
    int quantity;

    public Slot(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return item.getName() + " x" + quantity;
    }
}
