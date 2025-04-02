package data;

public enum HerbTypes {
    Guam("Guam seed"),
    Marrentill("Marrentill seed"),
    Tarromin("Tarromin seed"),
    Harralander("Harralander seed"),
    Ranarr("Ranarr seed"),
    Toadflax("Toadflax seed"),
    Irit("Irit seed"),
    Avantoe("Avantoe seed"),
    Kwuarm("Kwuarm seed"),
    Snapdragon("Snapdragon seed"),
    Huasca("Huasca seed"),
    Cadantine("Cadantine seed"),
    Lantadyme("Lantadyme seed"),
    Dwarf("Dwarf weed seed"),
    Torstol("Torstol seed");

    private final String seedName;

    HerbTypes(String seedName) {
        this.seedName = seedName;
    }

    public String getSeedName() {
        return seedName;
    }
}
