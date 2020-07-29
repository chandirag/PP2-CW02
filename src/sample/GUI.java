package sample;

import com.mongodb.DBCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        startupLayout.setPadding(new Insets(0, 0, 0, 0)); // Padding for elements against the stage border
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



        table.setItems(getMember());
        table.getColumns().addAll(idColumn, nameColumn, dateJoinedColumn, heightColumn, weightColumn);
        startupLayout.getChildren().add(table);

        Scene scene = new Scene(startupLayout, 513, 400);
        window.setScene(scene);
        window.show();
    }


    public static ObservableList<DefaultMember> getMember(){
        ObservableList<DefaultMember> members = FXCollections.observableArrayList();
        ArrayList<Integer> membershipNumbers = Database.printID();
        ArrayList<String> names = Database.printNames();
        ArrayList<String> datesJoined = Database.printDateJoined();
        ArrayList<Double> heights = Database.printHeight();
        ArrayList<Double> weights = Database.printWeight();


        for (int i=0; i < names.size(); i++) {
            members.add(new DefaultMember(membershipNumbers.get(i), names.get(i), datesJoined.get(i),
                    heights.get(i), weights.get(i)));
        }
        return members;
    }
}
