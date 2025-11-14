package com.example.lab4;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;

public class HelloController {

    @FXML private TextField nameField, addressField, provinceField, cityField, postalCodeField, phoneNumberField, emailField;
    @FXML private final ToggleGroup major = new ToggleGroup();
    @FXML private RadioButton computerScienceButton, businessButton;
    @FXML private ComboBox<String> courseSelection = new ComboBox<String>();
    @FXML private ListView<String> courseList;
    @FXML private CheckBox studentCouncilBox, volunteerWorkBox;
    @FXML private TextArea infoArea;
    @FXML private Button displayButton;

    String[] courses = { "Java", "Python", "C#", "C++", "JavaScript"};

    @FXML public void initialize() {
        computerScienceButton.setToggleGroup(major);
        businessButton.setToggleGroup(major);

        courseSelection.setItems(FXCollections.observableArrayList(courses));

        courseSelection.setOnAction(event -> {
            String selectedCourse = courseSelection.getValue();

            if (selectedCourse == null) {
                return;
            }
            if (courseList.getItems().contains(selectedCourse)) {
                return;
            }
            courseList.getItems().add(selectedCourse);
        });

        displayButton.setOnAction(event -> HandleDisplay());
    }

    private void HandleDisplay()
    {
        infoArea.appendText(nameField.getText() + ", " + addressField.getText() + ", " +
                provinceField.getText() + ", " + cityField.getText() + ", " + postalCodeField.getText() + ", " +
                phoneNumberField.getText() + ", " + emailField.getText() + "\n" +
                major.getSelectedToggle().getUserData().toString()  + "\nCourses: \n" +
                courseSelection.getSelectionModel().getSelectedItem() + "\nAdditional Info: \n" +
                (studentCouncilBox.isSelected() ? "On Student Council \n" : "") +
                (volunteerWorkBox.isSelected() ? "Does Volunteer Work \n" : ""));
    }
}
