package data;

public enum BirdhouseSeedTypes {
    Barley("Barley seed"),
    Hammerstone ("Hammerstone seed"),
    Asgarnian ("Asgarnian seed"),
    Jute ("Jute seed"),
    Yanillian ("Yanillian seed"),
    Krandorian ("Krandorian seed"),
    Wildblood ("Wildblood seed"),
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
    Cadantine("Cadantine seed"),
    Lantadyme("Lantadyme seed"),
    Dwarf("Dwarf weed seed"),
    Torstol("Torstol seed"),
    Marigold("Marigold seed"),
    Rosemary ("Rosemary seed"),
    Nasturtium("Nasturtium seed"),
    Woad("Woad seed"),
    Limpwurt("Limpwurt seed"),
    WhiteLily("White lily seed"),
    Potato("Potato seed"),
    Onion("Onion seed"),
    Cabbage("Cabbage seed"),
    Tomato("Tomato seed"),
    Sweetcorn("Sweetcorn seed"),
    Strawberry("Strawberry seed"),
    Watermelon("Watermelon seed"),
    SnapeGrass("Snape grass seed"),
    Redberry("Redberry seed"),
    PoisonIvy("Poison ivy seed"),
    Cadavaberry ("Cadavaberry seed"),
    Dwellberry("Dwellberry seed"),
    Jangerberry("Jangerberry seed"),
    Whiteberry("Whiteberry seed");

    private final String BirdhouseSeedName;

    BirdhouseSeedTypes(String logName) {
        this.BirdhouseSeedName = getBirdhouseSeedName();
    }

    public String getBirdhouseSeedName() {
        return BirdhouseSeedName;
    }
}
