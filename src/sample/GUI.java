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
import java.util.NoSuchElementException;

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

        // Column: Name
        TableColumn<DefaultMember,String> idColumn = new TableColumn<>("Name");
        idColumn.setMinWidth(90);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("membershipNumber"));

        // Column: Name
        TableColumn<DefaultMember,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Column: Date Joined
        TableColumn<DefaultMember,String> dateJoinedColumn = new TableColumn<>("Date Joined");
        dateJoinedColumn.setMinWidth(90);
        dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<>("membershipDate"));

        // Column: Height
        TableColumn<DefaultMember,String> heightColumn = new TableColumn<>("Height");
        heightColumn.setMinWidth(90);
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));

        // Column: Weight
        TableColumn<DefaultMember,String> weightColumn = new TableColumn<>("Weight");
        weightColumn.setMinWidth(90);
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        // Column: Member Type
        TableColumn<DefaultMember,String> memberTypeColumn = new TableColumn<>("Member Type");
        memberTypeColumn.setMinWidth(110);
        memberTypeColumn.setCellValueFactory(new PropertyValueFactory<>("memberType"));

        // Search Field
        TextField searchField = new TextField();
        searchField.setPromptText("Search by name...");
        searchField.minHeight(30);
        searchField.minWidth(100);
        GridPane.setConstraints(searchField, 0, 0);

        // Search Button
        Button searchButton = new Button("Search");
        GridPane.setConstraints(searchButton, 1, 0);
        searchButton.setOnAction(event -> table.setItems(nameSearch(searchField)));
//        searchButton.setOnAction(event -> table.setItems(getSearchResults(searchField)));

        // Reset Button
        Button resetButton = new Button("Reset");
        GridPane.setConstraints(resetButton, 2, 0);
        resetButton.setOnAction(event -> {
            table.setItems(getMember());
            searchField.setText("");
        });

        table.setItems(getMember());
        table.getColumns().addAll(idColumn, nameColumn, dateJoinedColumn, heightColumn, weightColumn, memberTypeColumn);
        GridPane.setConstraints(table, 0, 2, 4, 1);

        startupLayout.getChildren().addAll(table, searchField, searchButton, resetButton);
        Scene scene = new Scene(startupLayout, 660, 400);
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


//    public static ObservableList<DefaultMember> getSearchResults(TextField textField) {
//        ObservableList<DefaultMember> members = FXCollections.observableArrayList();
//
//        try {
//            String searchName = textField.getText();
//
//            ArrayList<String> nameArray = Database.putResultsIntoArray(Database.findDocumentByName(searchName));
//
//            for (int i=0; i < nameArray.size()-1; i++) {
//                members.add(new DefaultMember(2000, nameArray.get(i), "DATE",
//                        100, 100, "MEMBER-TYPE"));
//            }
//
//        }catch (NoSuchElementException e){
//            System.out.println("test");
//        }
//        return members;
//    }

    public static ObservableList<DefaultMember> nameSearch(TextField textField){
        ObservableList<DefaultMember> defaultMembers = FXCollections.observableArrayList();

        try {
            String searchName = textField.getText();

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

        }catch (NoSuchElementException e){
            System.out.println("test");
        }
        return defaultMembers;

    }
}
