package com.internshala.javafxapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	@FXML
	public Label welcomeLabel;

	@FXML
	public ChoiceBox<String> choiceBox;

	@FXML
	public TextField userInputField;

	@FXML
	public Button convertButton;

	private static final String C_To_F_Text="Celsius to Fahrenheit";
	private static final String F_To_C_Text="Fahrenheit to Celsius";

	private boolean isC_to_F=true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		choiceBox.getItems().add(C_To_F_Text);
		choiceBox.getItems().add(F_To_C_Text);

		choiceBox.setValue(C_To_F_Text);

		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{

			if (newValue.equals(C_To_F_Text)){
				isC_to_F=true;
			} else {
				isC_to_F=false;
			}
		});

		convertButton.setOnAction(event -> {
			convert();
		});
	}

	private void convert() {
		String input=userInputField.getText();

		float enteredTemperature=0.0f;

		try {
			enteredTemperature=Float.parseFloat(input);
		}catch (Exception e){
			warnUser();
			return;
			//No code executed
		}

		float newTemp;
		if (isC_to_F){
			newTemp=(enteredTemperature*9/5)+32;   //C to F
		} else {
			newTemp=(enteredTemperature-32)*5/9;   //F to C
		}
		display(newTemp);
	}

	private void warnUser() {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Occurred");
		alert.setHeaderText("Invalid Temperature Entered");
		alert.setContentText("Please enter a valid temperature");
		alert.show();
	}

	private void display(float newTemp) {

		String unit=isC_to_F?"F":"C";
		System.out.println("The new temperature is: "+newTemp +" " + unit);

		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The new temperature is: "+newTemp +" " + unit);
		alert.show();
	}
}
