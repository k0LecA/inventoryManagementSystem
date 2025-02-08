package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main {
    public static void main(String[] args) {
        ItemLibrary itemLibrary = new ItemLibrary();
        itemLibrary.LoadItems();
        InventoryManager manager = new InventoryManager(itemLibrary);

        // gui
        JFrame frame = new JFrame("Item Inventory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        DefaultListModel<Slot> itemListModel = new DefaultListModel<>();
        manager.displayInventory(itemListModel);

        JList<Slot> itemList = new JList<>(itemListModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.setSelectedIndex(0);

        JScrollPane scrollPane = new JScrollPane(itemList);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(200, frame.getHeight()));

        JLabel descriptionLabel = new JLabel("Description: None");
        JLabel equippedLabel = new JLabel("Equipped Armor: None");

        rightPanel.add(new JLabel("Controls:"));
        rightPanel.add(new JLabel("A: Add Item"));
        rightPanel.add(new JLabel("R: Remove Item"));
        rightPanel.add(new JLabel("Arrow keys: Navigate"));
        rightPanel.add(new JLabel("E: Equip/Unequip Armor"));
        rightPanel.add(new JLabel("Q: Drop"));
        rightPanel.add(new JLabel("F: Consume"));
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(descriptionLabel);
        rightPanel.add(equippedLabel);

        frame.getContentPane().add(rightPanel, BorderLayout.EAST);

        //manager.addItem(itemLibrary.findItemById("3"),1);
        Slot armorSlot=new Slot(null,0);
        itemList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                boolean equiped=false;
                int selectedIndex = itemList.getSelectedIndex();
                int itemCount = itemListModel.size();
                if (e.getKeyCode() == KeyEvent.VK_UP && selectedIndex > 0) {
                    itemList.setSelectedIndex(selectedIndex - 1);
                    descriptionLabel.setText("Description: "+itemListModel.getElementAt(selectedIndex-1).item.getDescription());
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && selectedIndex < itemCount - 1) {
                    itemList.setSelectedIndex(selectedIndex + 1);
                    descriptionLabel.setText("Description: "+itemListModel.getElementAt(selectedIndex+1).item.getDescription());
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    openAddItemWindow(manager, itemLibrary, itemListModel);
                }
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    openRemoveItemWindow(manager, itemListModel);
                }
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    selectedIndex=itemList.getSelectedIndex();
                    Item selectedItem=itemListModel.getElementAt(selectedIndex).item;
                    if(selectedItem.getType().equals("Armor")){
                        if(equiped){
                            equiped=false;
                            equippedLabel.setText("Equiped: none");
                        }else{
                            equiped=true;
                            equippedLabel.setText("Equiped Armor: "+selectedItem.getName());
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    selectedIndex=itemList.getSelectedIndex();
                    Slot selectedSlot=itemListModel.getElementAt(selectedIndex);
                    if(selectedSlot.quantity>0){
                        manager.removeItem(selectedSlot.item,1);
                    }
                    manager.displayInventory(itemListModel);
                    itemList.setSelectedIndex(selectedIndex);

                }
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    selectedIndex=itemList.getSelectedIndex();
                    Slot selectedSlot=itemListModel.getElementAt(selectedIndex);
                    if(selectedSlot.quantity>0 && selectedSlot.item.getType().equals("Consumable")){
                        manager.removeItem(selectedSlot.item,1);
                    }
                    manager.displayInventory(itemListModel);
                    itemList.setSelectedIndex(selectedIndex);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    sortInventory(itemList);
                }
            }
        });
        frame.setVisible(true);
    }
    private static void openAddItemWindow(InventoryManager manager, ItemLibrary itemLibrary, DefaultListModel<Slot> itemListModel) {
        JDialog addItemDialog = new JDialog();
        addItemDialog.setTitle("Add Item");
        addItemDialog.setSize(300, 200);
        addItemDialog.setLocationRelativeTo(null);

        JLabel itemIdLabel = new JLabel("Item ID:");
        JTextField itemIdField = new JTextField(10);

        JLabel quantityLabel = new JLabel("Quantity:");
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

        JButton addButton = new JButton("Add");

        addButton.addActionListener(e -> {
            String itemId = itemIdField.getText();
            int quantity = (int) quantitySpinner.getValue();
            Item item = itemLibrary.findItemById(itemId);
            if (item != null) {
                manager.addItem(item, quantity);
                manager.displayInventory(itemListModel);
                addItemDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(addItemDialog, "Item not found :(");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(itemIdLabel);
        panel.add(itemIdField);
        panel.add(quantityLabel);
        panel.add(quantitySpinner);
        panel.add(new JLabel());
        panel.add(addButton);

        addItemDialog.getContentPane().add(panel);
        addItemDialog.setVisible(true);
    }
    private static void openRemoveItemWindow(InventoryManager manager, DefaultListModel<Slot> itemListModel) {
        JDialog removeItemDialog = new JDialog();
        removeItemDialog.setTitle("Remove Item");
        removeItemDialog.setSize(300, 200);
        removeItemDialog.setLocationRelativeTo(null);

        JLabel itemIdLabel = new JLabel("Item ID:");
        JTextField itemIdField = new JTextField(10);

        JLabel quantityLabel = new JLabel("Quantity:");
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

        JButton removeButton = new JButton("Remove");

        removeButton.addActionListener(e -> {
            String itemId = itemIdField.getText();
            int quantity = (int) quantitySpinner.getValue();
            Item item = manager.getItemLibrary().findItemById(itemId);
            if (item != null) {
                manager.removeItem(item, quantity);
                manager.displayInventory(itemListModel);
                removeItemDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(removeItemDialog, "Item not found!");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(itemIdLabel);
        panel.add(itemIdField);
        panel.add(quantityLabel);
        panel.add(quantitySpinner);
        panel.add(new JLabel());
        panel.add(removeButton);

        removeItemDialog.getContentPane().add(panel);
        removeItemDialog.setVisible(true);
    }
    private static void sortInventory(JList<Slot> list){
        DefaultListModel<Slot> model=(DefaultListModel<Slot>) list.getModel();
        //List<Slot> temp=new ArrayList<Slot>();
    }
}
