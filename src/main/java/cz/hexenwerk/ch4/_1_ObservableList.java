package cz.hexenwerk.ch4;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public final class _1_ObservableList extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        VBox root = new VBox();

        ObservableList<String> values = FXCollections.observableArrayList("Alpha", "Beta", "Gamma");

        ListView<Integer> distinctLengthsListView = new ListView<>();

        JavaFxObservable.emitOnChanged(values)
                .flatMapSingle((ObservableList<String> list) ->
                        Observable.fromIterable(list)
                                .map(String::length)
                                .distinct()
                                .toList())
                .subscribe((List<Integer> lengths) ->
                        distinctLengthsListView.getItems().setAll(lengths));

        TextField inputField = new TextField();
        Button addButton = new Button("ADD");

        JavaFxObservable.actionEventsOf(addButton)
                .map((ActionEvent ae) -> inputField.getText())
                .filter((String s) -> s != null && !s.trim().isEmpty())
                .subscribe((String s) ->
                        {
                            values.add(s);
                            inputField.clear();
                        }
                );

        root.getChildren().addAll(
                new Label("VALUES"), new ListView<String>(values),
                new Label("DISTINCT LENGTHS"), distinctLengthsListView,
                inputField, addButton
        );
        stage.setScene(new Scene(root));
        stage.show();
    }
}