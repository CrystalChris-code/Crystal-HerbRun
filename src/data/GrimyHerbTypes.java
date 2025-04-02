package data;

public enum GrimyHerbTypes {
    Guam(199,"Grimy guam leaf"),
    Marrentill (201, "Grimy marrentill"),
    Tarromin(203,"Grimy tarromin"),
    Harralander(205,"Grimy harralander"),
    Ranarr(207,"Grimy ranarr weed"),
    Toadflax(3049,"Grimy toadflax"),
    Irit(209,"Grimy irit leaf"),
    Avantoe(211,"Grimy avantoe"),
    Kwuarm(213,"Grimy kwuarm"),
    Snapdragon(3051,"Grimy snapdragon"),
    Huasca(30094,"Grimy huasca"),
    Cadantine(215,"Grimy cadantine"),
    Lantadyme(2485,"Grimy lantadyme"),
    Dwarf(217,"Grimy dwarf weed"),
    Torstol(219,"Grimy torstol");

    private final int id;
    private final String name;

    GrimyHerbTypes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
