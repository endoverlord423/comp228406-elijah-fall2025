package com.example.hellofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML private TextField firstNameField, lastNameField, ageField, colorField, programField;
    @FXML private TableView<StudentRecord> recordTable;
    @FXML private TableColumn<StudentRecord, String> firstNameColumn, lastNameColumn, favoriteColorColumn, collegeProgramColumn;
    @FXML private TableColumn<StudentRecord, Integer> ageColumn;
    @FXML private Button submitButton, viewButton;
    @FXML private Label statusLabel, recordCountLabel, connectionLabel;
    @FXML private TableColumn<StudentRecord, Void> detailsColumn;

    private final PostgreSQLManager dbManager;
    private final ObservableList<StudentRecord> records = FXCollections.observableArrayList();

//    private static final Dotenv dotenv = Dotenv.configure().directory("src/main/resources").load();
//    private static final String URL = dotenv.get("jdbc:postgresql://aws-1-ca-central-1.pooler.supabase.com:5432/postgres");
//    private static final int PORT = Integer.parseInt(dotenv.get("5432"));
//    private static final String DB = dotenv.get("postgres");
//    private static final String USER = dotenv.get("postgres.niaquuwtucpfcfprjysj");
//    private static final String PASS = dotenv.get("yTtufdfRjIqWr0BJ");

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
        firstNameColumn.setCellValueFactory(data -> data.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(data -> data.getValue().lastNameProperty());

        // Add the Details button column dynamically
        detailsColumn.setCellFactory(col -> new TableCell<>() {
            private final Button detailsBtn = new Button("View");
            {
                detailsBtn.getStyleClass().addAll("btn", "btn-sm", "btn-outline-primary");
                detailsBtn.setOnAction(e -> {
                    StudentRecord record = getTableView().getItems().get(getIndex());
                    showStudentDetails(record);
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

        recordTable.setItems(records);

        handleView();
        submitButton.setOnAction(e -> handleSubmit());
        viewButton.setOnAction(e -> handleView());
        connectionLabel.setText("DB: Connected (SSL)");
    }

    @FXML
    private void handleClear() {
        clearFields();
        statusLabel.setText("Fields cleared.");
    }

    private void handleSubmit() {
        String first = firstNameField.getText().trim();
        String last  = lastNameField.getText().trim();
        String color = colorField.getText().trim();
        String prog  = programField.getText().trim();

        if (first.isEmpty() || last.isEmpty() || color.isEmpty() || prog.isEmpty() || ageField.getText().isBlank()) {
            toast("Please fill all fields.", Alert.AlertType.WARNING);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageField.getText().trim());
            if (age < 0 || age > 120) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            toast("Age must be a valid number (0–120).", Alert.AlertType.ERROR);
            return;
        }

        boolean ok = dbManager.addRecord(first, last, age, color, prog);
        if (ok) {
            clearFields();
            handleView(); // refresh table + counter
            statusLabel.setText("Record added.");
        } else {
            statusLabel.setText("Error adding record. Check logs.");
        }
    }

    private void handleView() {
        records.clear();
        records.addAll(dbManager.getAllRecords());

        int dbCount = dbManager.countRecords();
        recordCountLabel.setText(String.valueOf(dbCount));

        statusLabel.setText("Loaded " + dbCount + " records.");
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        ageField.clear();
        colorField.clear();
        programField.clear();
    }

    private void toast(String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
        statusLabel.setText(msg);
    }

    private void showStudentDetails(StudentRecord record) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Student Details");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        VBox content = new VBox(10);
        content.getStyleClass().add("card");
        content.setStyle("-fx-padding: 20; -fx-background-color: #ffffff;");

        Label title = new Label("👤 " + record.getFirstName() + " " + record.getLastName());
        title.getStyleClass().add("h4");

        Label ageLabel = new Label("Age: " + record.getAge());
        Label colorLabel = new Label("Favorite Color: " + record.getFavoriteColor());
        Label programLabel = new Label("College Program: " + record.getCollegeProgram());

        HBox buttonRow = new HBox(10);
        Button editBtn = new Button("✏️ Edit");
        Button deleteBtn = new Button("🗑️ Delete");

        editBtn.getStyleClass().addAll("btn", "btn-outline-primary");
        deleteBtn.getStyleClass().addAll("btn", "btn-outline-danger");

        buttonRow.getChildren().addAll(editBtn, deleteBtn);

        content.getChildren().addAll(title, ageLabel, colorLabel, programLabel, new Separator(), buttonRow);

        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getStylesheets().addAll(
                getClass().getResource("/css/style.css").toExternalForm(),
                org.kordamp.bootstrapfx.BootstrapFX.bootstrapFXStylesheet()
        );

        // Event handlers for sub-dialogs
        editBtn.setOnAction(e -> {
            dialog.close();
            showEditDialog(record);
        });

        deleteBtn.setOnAction(e -> {
            dialog.close();
            showDeleteConfirmation(record);
        });

        dialog.showAndWait();
    }

    private void showEditDialog(StudentRecord record) {
        Dialog<Void> editDialog = new Dialog<>();
        editDialog.setTitle("Edit Student");
        editDialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);

        VBox form = new VBox(10);
        form.setStyle("-fx-padding: 20;");
        form.getStyleClass().add("card");

        TextField firstNameField = new TextField(record.getFirstName());
        TextField lastNameField = new TextField(record.getLastName());
        TextField ageField = new TextField(String.valueOf(record.getAge()));
        TextField colorField = new TextField(record.getFavoriteColor());
        TextField programField = new TextField(record.getCollegeProgram());

        firstNameField.getStyleClass().add("form-control");
        lastNameField.getStyleClass().add("form-control");
        ageField.getStyleClass().add("form-control");
        colorField.getStyleClass().add("form-control");
        programField.getStyleClass().add("form-control");

        Button saveBtn = new Button("💾 Save Changes");
        saveBtn.getStyleClass().addAll("btn", "btn-primary");

        form.getChildren().addAll(
                new Label("First Name"), firstNameField,
                new Label("Last Name"), lastNameField,
                new Label("Age"), ageField,
                new Label("Favorite Color"), colorField,
                new Label("College Program"), programField,
                new Separator(),
                saveBtn
        );

        editDialog.getDialogPane().setContent(form);
        editDialog.getDialogPane().getStylesheets().addAll(
                getClass().getResource("/css/style.css").toExternalForm(),
                org.kordamp.bootstrapfx.BootstrapFX.bootstrapFXStylesheet()
        );

        saveBtn.setOnAction(ev -> {
            try {
                boolean updated = dbManager.updateRecord(
                        record.getFirstName(),  // old primary key
                        firstNameField.getText(),
                        lastNameField.getText(),
                        Integer.parseInt(ageField.getText()),
                        colorField.getText(),
                        programField.getText()
                );
                if (updated) {
                    toast("Record updated successfully!", Alert.AlertType.CONFIRMATION);
                    handleView();
                    editDialog.close();
                } else {
                    toast("Could not update record.", Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException ex) {
                toast("Age must be a number.", Alert.AlertType.ERROR);
            }
        });

        editDialog.showAndWait();
    }

    private void showDeleteConfirmation(StudentRecord record) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText("Are you sure you want to delete this record?");
        confirm.setContentText(record.getFirstName() + " " + record.getLastName());

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                boolean deleted = dbManager.deleteRecord(record.getFirstName(), record.getLastName());
                if (deleted) {
                    toast("Record removed successfully.", Alert.AlertType.INFORMATION);
                    handleView();
                } else {
                    toast("Could not delete record.", Alert.AlertType.ERROR);
                }
            }
        });
    }



    // Theme toggles (buttons are already in FXML header)
    @FXML
    private void setLightTheme() {
        Scene scene = recordTable.getScene();
        if (scene == null) return;
        scene.getStylesheets().removeIf(s -> s.contains("dark.css"));
    }

    @FXML
    private void setDarkTheme() {
        Scene scene = recordTable.getScene();
        if (scene == null) return;
        if (scene.getStylesheets().stream().noneMatch(s -> s.contains("dark.css"))) {
            // Create dark.css later if you want true dark mode
            scene.getStylesheets().add(getClass().getResource("/css/dark.css").toExternalForm());
        }
    }
}

