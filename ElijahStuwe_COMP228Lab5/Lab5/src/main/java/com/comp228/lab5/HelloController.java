package com.comp228.lab5;

import com.comp228.lab5.records.GameRecord;
import com.comp228.lab5.records.PlayerGameRecord;
import com.comp228.lab5.records.PlayerRecord;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML private TextField firstNameField, lastNameField, addressField, postalCodeField, provinceField, phoneNumberField;
    @FXML private TableView<PlayerRecord> playerRecordTable;
    @FXML private TableColumn<PlayerRecord, String> firstNameColumn,  lastNameColumn, addressColumn, postalCodeColumn, provinceColumn;
    @FXML private TableColumn<PlayerRecord, Integer> phoneNumberColumn;
    @FXML private TableColumn<PlayerRecord, Void> playerDetailsColumn;

    @FXML private TextField gameTitleField;
    @FXML private TableView<GameRecord> gameRecordTable;
    @FXML private TableColumn<GameRecord, String> gameTitleColumn;
    @FXML private TableColumn<GameRecord, Void> gameDetailsColumn;

    @FXML private TextField gameIdField, playerIdField, playingDateField, scoreField;
    @FXML private TableView<PlayerGameRecord>  playerGameRecordTable;
    @FXML private TableColumn<PlayerGameRecord, Integer> playerIdColumn, gameIdColumn, scoreColumn;
    @FXML private TableColumn<PlayerGameRecord, String> playingDateColumn;
    @FXML private TableColumn<PlayerGameRecord, Void> playerGameDetailsColumn;

    @FXML private Button submitButton, viewButton;
    @FXML private Label statusLabel, recordCountLabel, connectionLabel;

    private final PostgreSQLManager dbManager;
    private final ObservableList<PlayerRecord> playerRecords = FXCollections.observableArrayList();
    private final ObservableList<GameRecord> gameRecords = FXCollections.observableArrayList();
    private final ObservableList<PlayerGameRecord> playerGameRecords = FXCollections.observableArrayList();

    public HelloController() {
        String URL = "aws-1-ca-central-1.pooler.supabase.com";
        int PORT = 5432;
        String DB = "postgres";
        String USER = "postgres.niaquuwtucpfcfprjysj";
        String PASS = "yTtufdfRjIqWr0BJ";
        dbManager = new PostgreSQLManager(URL, PORT, DB, USER, PASS);
    }

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        playerDetailsColumn.setCellFactory(col -> new TableCell<>() {
            private final Button detailsBtn = new Button("View");
            {
                detailsBtn.getStyleClass().addAll("btn", "btn-sm", "btn-outline-primary");
                detailsBtn.setOnAction(e -> {
                    PlayerRecord record = getTableView().getItems().get(getIndex());
                    showPlayerDetails(record);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(detailsBtn);
                }
            }
        });

        playerRecordTable.setItems(playerRecords);

        handleView();
        submitButton.setOnAction(e -> handleSubmit());
        viewButton.setOnAction(e -> handleView());
        connectionLabel.setText("DB: Connected (SSL)");
    }

    @FXML
    private void handleClear() {
        clearFields();
        statusLabel.setText("Fields cleared");
    }

    private void handleSubmit() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String address = addressField.getText().trim();
        String postalCode = postalCodeField.getText().trim();
        String province = provinceField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || postalCode.isEmpty() || province.isEmpty() || phoneNumberField.getText().isBlank()) {
            toast("Please fill all fields", Alert.AlertType.WARNING);
            return;
        }

        //basic phone number confirmation unless I have time to do better
        int phoneNumber;
        try {
            phoneNumber = Integer.parseInt(phoneNumberField.getText().trim());
            if (phoneNumber < 100000000) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            toast("Please enter a valid phone number", Alert.AlertType.ERROR);
            return;
        }

        boolean ok = dbManager.addPlayerRecord(firstName, lastName, address, postalCode, province, phoneNumber);
        if (ok) {
            clearFields();
            handleView();
            statusLabel.setText("Player record added");
        } else {
            statusLabel.setText("Error Adding Player Record. Check logs.");
        }
    }

    private void handleView() {
        playerRecords.clear();
        playerRecords.addAll(dbManager.getAllPlayerRecords());

        int dbCount = dbManager.countPlayerRecords();
        recordCountLabel.setText(String.valueOf(dbCount));

        statusLabel.setText("Records count: " + dbCount + " records");
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        addressField.clear();
        postalCodeField.clear();
        provinceField.clear();
        phoneNumberField.clear();
    }

    private void toast(String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
        statusLabel.setText(msg);
    }

    private void showPlayerDetails(PlayerRecord record) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Player details");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        VBox content = new VBox(10);
        content.getStyleClass().add("card");
        content.setStyle("-fx-padding: 20; -fx-background-color: #ffffff;");

        Label title = new Label(record.getFirstName() + " " + record.getLastName());
        title.getStyleClass().add("h4");

        Label address = new Label("Address: " + record.getAddress());
        Label postalCode = new Label("Postal Code: " + record.getPostalCode());
        Label province = new Label("Province: " + record.getProvince());
        Label phoneNumber = new Label("Phone Number: " + record.getPhoneNumber());

        HBox buttonRow = new HBox(10);
        Button editBtn = new Button("Edit");
        Button deleteBtn = new Button("Delete");

        editBtn.getStyleClass().addAll("btn", "btn-outline-primary");
        deleteBtn.getStyleClass().addAll("btn", "btn-outline-danger");

        buttonRow.getChildren().addAll(editBtn, deleteBtn);

        content.getChildren().addAll(title, address, postalCode, province, phoneNumber, new Separator(), buttonRow);

        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getStylesheets().addAll(
                getClass().getResource("/css/style.css").toExternalForm(),
                org.kordamp.bootstrapfx.BootstrapFX.bootstrapFXStylesheet()
        );

        deleteBtn.setOnAction(e -> {
            dialog.close();
            showDeleteConfirmation(record);
        });

        dialog.showAndWait();
    }

    private void showDeleteConfirmation(PlayerRecord record) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText("Are you sure you want to delete this record?");
        confirm.setContentText(record.getFirstName() + " " + record.getLastName());

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                boolean deleted = dbManager.deletePlayerRecord(record.getFirstName(), record.getLastName());
                if (deleted) {
                    toast("Record removed successfully.", Alert.AlertType.INFORMATION);
                    handleView();
                } else {
                    toast("Could not delete record.", Alert.AlertType.ERROR);
                }
            }
        });
    }
}
