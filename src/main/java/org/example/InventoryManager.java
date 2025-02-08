package org.example;
import javax.swing.*;

import java.util.LinkedList;


public class InventoryManager {

    private LinkedList<Slot> inventory;
    private ItemLibrary itemLibrary;

    public InventoryManager(ItemLibrary itemLibrary) {
        this.itemLibrary = itemLibrary;
        inventory = new LinkedList<>();
    }

    public ItemLibrary getItemLibrary() {
        return itemLibrary;
    }

    public void addItem(Item item, int quantity) {
        for (Slot slot : inventory) {
            if (slot.item.getId().equals(item.getId())) {
                slot.quantity += quantity;
                return;
            }
        }
        inventory.add(new Slot(item, quantity));
    }

    public void removeItem(Item item, int quantity) {
        for (Slot slot : inventory) {
            if (slot.item.getId().equals(item.getId())) {
                slot.quantity -= quantity;
                if (slot.quantity <= 0) {
                    inventory.remove(slot);
                }
                return;
            }
        }
    }

    public LinkedList<Slot> getInventory() {
        return inventory;
    }

    public void displayInventory(DefaultListModel<Slot> itemListModel) {
        itemListModel.clear();
        for (Slot slot : inventory) {
            itemListModel.addElement(slot);
        }
    }

    public Slot getSelectedSlot(DefaultListModel<Slot> itemListModel, int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < itemListModel.size()) {
            return itemListModel.getElementAt(selectedIndex);
        }
        return null;
    }
}
