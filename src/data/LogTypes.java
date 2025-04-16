package data;

public enum LogTypes {
    Regular("Logs"),
    Oak ("Oak logs"),
    Willow ("Willow logs"),
    Teak ("Teak logs"),
    Maple ("Maple logs"),
    Mahogany ("Mahogany logs"),
    Yew ("Yew logs"),
    Magic ("Magic logs"),
    Redwood("Redwood logs");

    private final String logName;

    LogTypes(String logName) {
        this.logName = logName;
    }

    public String getLogName() {
        return logName;
    }
}
