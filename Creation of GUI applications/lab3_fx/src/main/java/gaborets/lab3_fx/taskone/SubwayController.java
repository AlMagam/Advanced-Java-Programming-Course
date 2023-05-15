package gaborets.lab3_fx.taskone;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
//import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;/////!!!
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SubwayController implements Initializable {
    public SubwayStream subwayStream = new SubwayStream();
    public ObservableList<Hour> observableList;

    public static void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    public static FileChooser getFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        // задаём начало поиска файлов в текущем каталоге
        fileChooser.setInitialDirectory(new File("."));
        // Установили фильтры для поиска файлов
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("XML-files (*.xml)", "*.xml"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        fileChooser.setTitle(title);//title okna
        return fileChooser;
    }
    //связка с интерфейсом
//    @FXML
//    private ImageView imageDB;
    @FXML
    public TextField textFieldMetroStation;
    @FXML
    public TextField textFieldOpeningYear;
    @FXML
    public TextField textFieldHourByComment;
    @FXML
    public TextArea textAreaResults;
    @FXML
    public TableView<Hour> tableViewHours;
    @FXML
    public TableColumn<Hour, Integer> tableColumnPassengerCount;
    @FXML
    public TableColumn<Hour, String> tableColumnComment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewHours.setPlaceholder(new Label(""));
    }

    public void doNew(ActionEvent actionEvent) {
        subwayStream = new SubwayStream();
        observableList = null;
        textFieldMetroStation.setText("");
        textFieldOpeningYear.setText("");
        textFieldHourByComment.setText("");
        textAreaResults.setText("");
        tableViewHours.setItems(null);
        tableViewHours.setPlaceholder(new Label(""));
    }
    @FXML
    public void doOpen(ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Open XML-file");
        File file;
        if ((file = fileChooser.showOpenDialog(null)) != null) {
            try {
                subwayStream = FileStuff.deserializeFromXML(file.getCanonicalPath());
                // Заповнюємо текстові поля прочитаними даними:
                assert subwayStream != null;
                textFieldMetroStation.setText(subwayStream.getName());
                textFieldOpeningYear.setText(subwayStream.getOpeningYear() + "");
                textAreaResults.setText("");
                // Очищаємо та оновлюємо таблицю:
                tableViewHours.setItems(null);
                updateTable();
            } catch (IOException e) {
                showError("file ist nicht gefunden");
            } catch (Exception e) {
                showError("Incorect file format");
            }
        }
    }
    public void doSave(ActionEvent actionEvent) {
        FileChooser fileChooser = getFileChooser("Save XML-file");
        File file;
        System.out.println(subwayStream);
        System.out.println("error1" + subwayStream.getName());
        if ((file = fileChooser.showSaveDialog(null)) != null) {
            System.out.println("error2" + subwayStream.getName());
            try {
                updateSourceData(); // оновлюємо дані в моделі
                System.out.println("!!!!!!!!!!!!!!!!!!!!" + subwayStream.getName());
                FileStuff.serializeToXML(subwayStream, file.getCanonicalPath());
                showMessage("Results saved");
            } catch (Exception e) {
                showError("Error write to file");
            }
        }
    }
    private void updateSourceData() {
        subwayStream = new SubwayStream();
        subwayStream.setOpeningYear(Integer.parseInt(textFieldOpeningYear.getText()));
        subwayStream.setName(textFieldMetroStation.getText());
        for (Hour h : observableList) {
            subwayStream.addHour(h);
        }
    }

    public void doExit(ActionEvent actionEvent) {
        Platform.exit(); // коректне завершення застосунку JavaFX
    }
    public void doAdd(ActionEvent actionEvent) {
        Dialog<Hour> dialog = new Dialog<>();
        dialog.setTitle("Add Time");
        dialog.setContentText("Add time to Metro Stream");

        Label label1 = new Label("Comment: ");
        Label label2 = new Label("Passenger count: ");
        TextField commentTextField = new TextField();
        TextField passengerCountTextField = new TextField();

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(commentTextField, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(passengerCountTextField, 2, 2);
        dialog.getDialogPane().setContent(grid);

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(saveButtonType);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                String comment = commentTextField.getText();
                String passengerCountText = passengerCountTextField.getText();

                // Check if fields are empty
                if (comment.trim().isEmpty() || passengerCountText.trim().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Fields can not be empty!");
                    alert.showAndWait();
                    return null;
                }
                // Check if passenger count is a number
                try {
                    int passengerCount = Integer.parseInt(passengerCountText);
                    return new Hour(passengerCount, comment);
                } catch (NumberFormatException e) {
                    showError("Count of passangers must be a numer!");
                    return null;
                }
                catch (IllegalArgumentException e) {//IllegalArgumentException
                    showError("Count of passangers must be a POSITIV numer!");
                }
            }
            return null;
        });
        Optional<Hour> result = dialog.showAndWait();
        if (result.isPresent()) {
            subwayStream.addHour(result.get().getComment(), result.get().getPassengersNumber());
            updateTable(); // створюємо нові дані
        }
    }
    public void doRemove(ActionEvent actionEvent) {
        // Не можемо видалити рядок, якщо немає даних:
        if (observableList == null) {
            return;
        }
        // Якщо є рядки, видаляємо останній:
        if (observableList.size() > 0) {
            observableList.remove(observableList.size() - 1);
        }
        // Якщо немає рядків, вказуємо, що дані відсутні:
        if (observableList.size() == 0) {
            observableList = null;
        }
        updateSourceData();
    }
    @FXML
    public void sortByPassengerNumberDesc(ActionEvent actionEvent) {
        updateSourceData();
        subwayStream.sortByPassengerNumberDesc();
        updateTable();
    }
    @FXML
    public void sortByCommentLengthDesc(ActionEvent actionEvent) {
        updateSourceData();
        subwayStream.sortByCommentLengthDesc();
        updateTable();
    }
    @FXML
    public void sortByCommentAlphabetically(ActionEvent actionEvent) {
        updateSourceData();
        subwayStream.sortByCommentAlphabetically();
        updateTable();
    }
    public void doAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About program");
        alert.setHeaderText("Data about subway station");
        alert.setContentText("Version 0.0.1");
        alert.showAndWait();
    }
    public void nameChanged(KeyEvent actionEvent) {
        // Коли користувач змінив дані в textFieldMetroStation,
        // автоматично оновлюємо назву:
        subwayStream.setName(textFieldMetroStation.getText());
        System.out.println(subwayStream.getName());
    }
    public void openingYearChanged(KeyEvent actionEvent) {
        subwayStream.setOpeningYear(Integer.parseInt(textFieldOpeningYear.getText()));
        System.out.println(subwayStream.getOpeningYear());
    }
    public void doFindHoursByComment(ActionEvent actionEvent) {
        updateSourceData();
        textAreaResults.setText("");

        for (int i = 0; i < subwayStream.hoursCount(); i++) {
            Hour c = (Hour) subwayStream.getHour(i);
            if (c.containsSubstring(textFieldHourByComment.getText())) {
                showResults(c);
            }
        }
    }
    private void showResults(Hour h) {
        textAreaResults.appendText(h.toString());
        textAreaResults.appendText("\n");
    }
    public void updateComment(TableColumn.CellEditEvent<Hour, String> t) {
        Hour h = t.getTableView().getItems().get(t.getTablePosition().getRow());
        h.setComment(t.getNewValue());

        System.out.println("Comment updated");
        // Обновляем список элементов observableList:
        observableList.setAll(subwayStream.getHours());

        System.out.println("observableList size: " + observableList.size());
        System.out.println("h.getComment(): " + h.getComment());
    }
    public void updatePassenger(TableColumn.CellEditEvent<Hour, Integer> t) {
        Hour h = t.getTableView().getItems().get(t.getTablePosition().getRow());
        h.setPassengersNumber(t.getNewValue());
    }
    public void updateTable() {
        // Заполняем observableList:
        List<Hour> list = new ArrayList<>();
        observableList = FXCollections.observableList(list);
        for (int i = 0; i < subwayStream.hoursCount(); i++) {
            list.add(subwayStream.getHour(i));
        }
        tableViewHours.setItems(observableList);

        // Вказуємо для колонок зв'язану з ними властивість і механізм редагування
        // залежно від типу комірок:
        tableColumnPassengerCount.setCellValueFactory(new PropertyValueFactory<>("passengersNumber"));
        tableColumnPassengerCount.setCellFactory(
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tableColumnPassengerCount.setOnEditCommit(this::updatePassenger);

        tableColumnComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        tableColumnComment.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnComment.setOnEditCommit(this::updateComment);
    }
}
