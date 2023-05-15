package gaborets.lab3_fx.taskvear;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class Slovar extends Application {
    // Константы
    private static final String SEARCH_ENG_LABEL = "Enter searched word in Eng:";
    private static final String SEARCH_UKR_LABEL = "Enter searched word in Ukr:";
    private static final String SEARCH_ENG_BUTTON_LABEL = "Search in Ukr word";
    private static final String SEARCH_UKR_BUTTON_LABEL = "Search in Eng word";
    private static final String EMPTY_FIELD_ERROR = "Enter a word";
    private static final String NO_TRANSLATION_ERROR = "No translation found for: ";
    private static final String ALREADY_EXISTS = " already have in the dictionary";
    private static final String ALPHABETICAL_CHARACTERS_ERROR = "The word can only contain alphabetical characters.";
    private static final VBox vboxForAdd = new VBox();

    private static final TextField engField = new TextField();
    private static final TextField ukrField = new TextField();
    // Кєшир част исп значения
    ObservableMap<String, String> attribMap = FXCollections.observableHashMap();

    private final TextField resultOfSearchTextField = new TextField();
    private final Label engSearchLabel = new Label(SEARCH_ENG_LABEL);
    private final TextField engSearchField = new TextField();
    private final Button engSearchButton = new Button(SEARCH_ENG_BUTTON_LABEL);
    private final VBox vboxForEngSearch = new VBox();
    private final Label ukrSearchLabel = new Label(SEARCH_UKR_LABEL);
    private final TextField ukrSearchField = new TextField();
    private final Button ukrSearchButton = new Button(SEARCH_UKR_BUTTON_LABEL);
    private final VBox vboxForUkrSearch = new VBox();

    private final HBox hBox = new HBox();

    public static void main(String[] args) {
        launch(args);
    }
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    public void initSearchBox() {
        engSearchButton.setOnAction(e -> searchWord(engSearchField.getText(), true));
        ukrSearchButton.setOnAction(e -> searchWord(ukrSearchField.getText(), false));

        resultOfSearchTextField.setEditable(false);

        vboxForEngSearch.getChildren().addAll(engSearchLabel, engSearchField, engSearchButton);
        vboxForUkrSearch.getChildren().addAll(ukrSearchLabel, ukrSearchField, ukrSearchButton);

        hBox.setSpacing(12);
        hBox.getChildren().addAll(vboxForEngSearch, vboxForUkrSearch);
    }
    private void searchWord(String searchedValue, boolean isEngSearch) {
        // searchedValue пустой
        if (searchedValue.isEmpty()) {
            showError(EMPTY_FIELD_ERROR);
            return;
        }
        String result = null;
        if (isEngSearch) {// поиск слова в attribMap по англ слову
            result = attribMap.get(searchedValue);
        } else {// поиск слова в attribMap по укр слову
            for (Map.Entry<String, String> entry : attribMap.entrySet()) {
                if (searchedValue.equals(entry.getValue())) {
                    result = entry.getKey();
                    break;
                }
            }
        }
        if (result == null) {
            showError(NO_TRANSLATION_ERROR + searchedValue);// Show an error message
        } else {
            System.out.println("Searched word: " + result);
            resultOfSearchTextField.setText(result);// Set the result in the resultOfSearchTextField
        }
    }
    public void initAddBox() {
        Label engLabel = new Label("Eng:");
        Label ukrLabel = new Label("Ukr:");
        Button addButton = new Button("Add new word");
        addButton.setOnAction(e -> {
            String engValue = engField.getText().trim();
            String ukrValue = ukrField.getText().trim();
            if (!engValue.equals("") && !ukrValue.equals("")) { // Check if fields are not empty
                if (attribMap.containsKey(engValue)) {
                    showError(ALREADY_EXISTS + engValue);
                } else if (attribMap.containsValue(ukrValue)) {
                    showError(ALREADY_EXISTS + ukrValue);
                }else if (!engValue.matches("^[a-zA-Z]+$")) { // Check that word contains only alphabetical characters
                    showError(ALPHABETICAL_CHARACTERS_ERROR + engValue);
                } else {
                    attribMap.put(engValue, ukrValue); // Добавл слово в attribMap
                    engField.clear();
                    ukrField.clear();
                }
            } else { showError(EMPTY_FIELD_ERROR); }
        });
        // Add the UI elements to the vboxForAdd
        vboxForAdd.getChildren().addAll(engLabel, engField, ukrLabel, ukrField, addButton);
    }
    @Override
    public void start(Stage stage) {
        stage.setTitle("Slovar");
        FlowPane rootNode = new FlowPane(15, 15);
        rootNode.setAlignment(Pos.CENTER_LEFT);
        Scene scene = new Scene(rootNode, 450, 450); // окно
        stage.setScene(scene);

        attribMap.addListener((MapChangeListener<String, String>) change -> System.out.println("Item was added\n" + attribMap));
        attribMap.put("First", "Первый");
        attribMap.put("Second", "Второй");
        attribMap.put("Third", "Третий");
        initAddBox();
        initSearchBox();

        rootNode.getChildren().addAll(vboxForAdd, hBox, resultOfSearchTextField);
        stage.setScene(scene);
        stage.show();
    }
}
