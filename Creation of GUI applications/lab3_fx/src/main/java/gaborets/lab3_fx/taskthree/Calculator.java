package gaborets.lab3_fx.taskthree;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {
    private Button button;
    private TextField field1, field2, field3;
    private ToggleGroup radioGroup;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Calculator");
        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);
        Scene scene = new Scene(rootNode, 300, 300); // розміри вікна
        stage.setScene(scene);

        RadioButton radioButtonPlus = new RadioButton("+");
        RadioButton radioButtonMinus = new RadioButton("-");
        RadioButton radioButtonDivide = new RadioButton("/");
        RadioButton radioButtonMultiply = new RadioButton("*");
        radioGroup = new ToggleGroup();

        radioGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            button.setDisable(false);
        });
        radioButtonPlus.setToggleGroup(radioGroup);
        radioButtonMinus.setToggleGroup(radioGroup);
        radioButtonDivide.setToggleGroup(radioGroup);
        radioButtonMultiply.setToggleGroup(radioGroup);
        field1 = new TextField();
        field2 = new TextField();
        field3 = new TextField();
        field3.setEditable(false);

        button = new Button("Calculate");
        button.setDisable(true);
        button.setOnAction(this::buttonClick);

        HBox hbox = new HBox(radioButtonPlus, radioButtonMinus, radioButtonDivide, radioButtonMultiply);
        hbox.setSpacing(15);
        VBox vbox = new VBox(field1, hbox, field2, button, field3);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(15, 15, 15, 15));
        vbox.setAlignment(Pos.CENTER);
        rootNode.getChildren().addAll(vbox);
        stage.show();
    }

    private void buttonClick(Event event) {
        try {
            double num1 = Double.parseDouble(field1.getText());
            double num2 = Double.parseDouble(field2.getText());
            RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
            String operation = selectedRadioButton.getText();
            double result = switch (operation) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> {
                    if (num2 == 0) { // на ноль
                        throw new IllegalArgumentException("Нельзя делить на ноль.");
                    }
                    yield num1 / num2;
                }
                default -> throw new IllegalStateException("Ошибка : " + operation);
            };
            field3.setText(String.format("%.2f", result));
        } catch (NumberFormatException e1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Неправильный ввод! Нужно два числа !");
            alert.showAndWait();
        } catch (IllegalArgumentException e2) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("На 0 не делится !");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
