# Inventory Management System

## Overview

This is a Java-based Inventory Management System using Swing for the graphical user interface. The system allows users to manage an inventory of items, equip armor, consume consumables, and dynamically update item quantities.

## Features

- **Add Items**: Press `A` to open a window where you can add items to the inventory by item ID and set quantity.
- **Remove Items**: Press `R` to remove items by specifying the ID and quantity.
- **Equip/Unequip Armor**: Press `E` to equip armor-type items; pressing `E` again will unequip them.
- **Consume Items**: Press `F` to consume consumable-type items, reducing their quantity.
- **Reduce Quantity**: Press `Q` to decrease the selected item's quantity by one.
- **Keyboard Navigation**: Use arrow keys (`UP`/`DOWN`) to navigate through the inventory.
- **JSON Storage**: Inventory data is loaded from a `JSON` file.

## Installation

1. **Clone the Repository**
   ```sh
   git clone https://github.com/k0LecA/inventoryManagementSystem.git
   cd inventoryManagementSystem
   ```
2. **Requirements**
   - Java 8 or higher
   - Jackson library for JSON processing (`jackson-databind`)
   - Swing for GUI
3. **Compile and Run**
   ```sh
   javac -d bin src/main/java/org/example/Main.java
   java -cp bin org.example.Main
   ```

## JSON Data Format

The `items.json` file follows this structure:

```json
[
  {
    "id": "1",
    "name": "Sword",
    "type": "Weapon",
    "description": "A sharp sword for fighting."
  },
  {
    "id": "2",
    "name": "Shield",
    "type": "Armor",
    "description": "A protective shield to block attacks."
  }
]
```

## Future Improvements
- Save inventory state to JSON when closing the application.
- Introduce categories and filters for better item management.

## License

This project is open-source and available under the MIT License.

## Author

Developed by **Jonas Butrimas**
