package data;

public enum CompostTypes {
    Compost ("Compost"),
    Supercompost("Supercompost"),
    Ultracompost("Ultracompost"),
    NONE ("No Compost");

    private final String itemName;

    CompostTypes(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
