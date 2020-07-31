package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GUI {
    public static void display() throws FileNotFoundException {
        Stage window = new Stage();
        window.setTitle("Gym Manager");

        GridPane startupLayout = new GridPane();
        startupLayout.setPadding(new Insets(20, 20, 20, 20)); // Padding for elements against the stage border
        startupLayout.setVgap(10); // Padding for individual cells
        startupLayout.setHgap(10);


        // Creating Table View
        TableView<DefaultMember> table = new TableView<>();

        // Creating Columns
        TableColumn<DefaultMember,String> idColumn = createTableColumn("Membership No.", 90, "membershipNumber");
        TableColumn<DefaultMember,String> nameColumn = createTableColumn("Name", 150, "name");
        TableColumn<DefaultMember,String> dateJoinedColumn = createTableColumn("Date Joined", 90, "membershipDate");
        TableColumn<DefaultMember,String> heightColumn = createTableColumn("Height", 90, "height");
        TableColumn<DefaultMember,String> weightColumn = createTableColumn("Weight", 90, "weight");
        TableColumn<DefaultMember,String> memberTypeColumn = createTableColumn("Member Type", 110, "memberType");


        // Search Field
        TextField searchField = new TextField();
        searchField.setPromptText("Search by name...");
        searchField.minHeight(30);
        searchField.minWidth(100);
        searchField.getStyleClass().add("textfield");
        GridPane.setConstraints(searchField, 0, 0);


        // Search Button
        Button searchButton = new Button("Search");
        GridPane.setConstraints(searchButton, 1, 0);
        searchButton.getStyleClass().add("search-button");
        searchButton.setOnAction(event -> table.setItems(nameSearch(searchField)));


        // Reset Button
        Button resetButton = new Button("Reset");
        GridPane.setConstraints(resetButton, 2, 0);
        resetButton.getStyleClass().add("reset-button");
        resetButton.setOnAction(event -> {
            table.setItems(getMember());
            searchField.setText("");
        });
//        searchField.setOnKeyTyped(event -> {
//            table.setItems(nameSearch(searchField));
//        });

        table.setItems(getMember());
        table.getColumns().addAll(idColumn, nameColumn, dateJoinedColumn, heightColumn, weightColumn, memberTypeColumn);
        GridPane.setConstraints(table, 0, 2, 4, 1);

        startupLayout.getChildren().addAll(table, searchField, searchButton, resetButton);
        Scene scene = new Scene(startupLayout, 660, 400);
        scene.getStylesheets().add(GUI.class.getResource("StyleSheet.css").toExternalForm()); // Adding css file to scene
        window.setScene(scene);
        window.show();


    }


    // Table View: Member Member Data
    public static ObservableList<DefaultMember> getMember() {
        ObservableList<DefaultMember> members = FXCollections.observableArrayList();
        ArrayList<Integer> membershipNumbers = Database.printID();
        ArrayList<String> names = Database.printNames();
        ArrayList<String> datesJoined = Database.printDateJoined();
        ArrayList<Double> heights = Database.printHeight();
        ArrayList<Double> weights = Database.printWeight();
        ArrayList<String> memberType = Database.printMemberType();

        for (int i=0; i < names.size(); i++) {
            members.add(new DefaultMember(membershipNumbers.get(i), names.get(i), datesJoined.get(i),
                    heights.get(i), weights.get(i), memberType.get(i)));
        }
        return members;
    }


    public static ObservableList<DefaultMember> nameSearch(TextField textField){
        ObservableList<DefaultMember> defaultMembers = FXCollections.observableArrayList();
        String searchName = Database.toTitleCase(textField.getText());

        if (searchName.isEmpty()) {
             return getMember();
        }

        ArrayList<Integer> id = Database.intSearch(Database.readNameTest(searchName),"_id");
        ArrayList<String> nameArray = Database.stringSearch(Database.readNameTest(searchName),"name");
        ArrayList<String> joinedDate = Database.stringSearch(Database.readNameTest(searchName),"membership-date");
        ArrayList<Double> height = Database.doubleSearch(Database.readNameTest(searchName),"height");
        ArrayList<Double> weight = Database.doubleSearch(Database.readNameTest(searchName),"weight");
        ArrayList<String> memberType = Database.stringSearch(Database.readNameTest(searchName),"member-type");

        for (int i=0; i <= nameArray.size()-1; i++) {
            defaultMembers.add(new DefaultMember(id.get(i), nameArray.get(i), joinedDate.get(i),
                    height.get(i), weight.get(i), memberType.get(i)));
        }

        return defaultMembers;
    }

    private static TableColumn<DefaultMember, String> createTableColumn(String columnHeader, int minWidth, String field) {
        TableColumn<DefaultMember, String> tableColumn = new TableColumn<>(columnHeader);
        tableColumn.setMinWidth(minWidth);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(field));
        return tableColumn;
    }
}
