package com.comp228.lab5.records;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerGameRecord {
    private final IntegerProperty playerId = new SimpleIntegerProperty();
    private final IntegerProperty gameId = new SimpleIntegerProperty();
    private final StringProperty playingDate = new SimpleStringProperty();
    private final IntegerProperty score = new SimpleIntegerProperty();

    public PlayerGameRecord(int playerId, int gameId, String playingDate, int score) {
        this.playerId.set(playerId);
        this.gameId.set(gameId);
        this.playingDate.set(playingDate);
        this.score.set(score);
    }

    public IntegerProperty playerIdProperty() { return playerId; }
    public IntegerProperty gameIdProperty() { return gameId; }
    public StringProperty playingDateProperty() { return playingDate; }
    public IntegerProperty scoreProperty() { return score; }

    public int getPlayerId() { return playerId.get(); }
    public int getGameId() { return gameId.get(); }
    public String getPlayingDate() { return playingDate.get(); }
    public int getScore() { return score.get(); }
}
