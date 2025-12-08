package com.comp228.lab5.records;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameRecord {
    private final StringProperty gameTitle = new SimpleStringProperty();

    public GameRecord(String gameTitle) {
        this.gameTitle.set(gameTitle);
    }

    public StringProperty gameTitleProperty() { return gameTitle; }
    public String getGameTitle() { return gameTitle.get(); }
}
