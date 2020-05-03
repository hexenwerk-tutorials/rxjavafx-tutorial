package cz.hexenwerk.ch4;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public final class _2_ObservableList extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        VBox root = new VBox();

        ObservableList<String> values = FXCollections.observableArrayList("Alpha", "Beta", "Gamma");

        Label distinctLengthsConcatLabel = new Label();
        distinctLengthsConcatLabel.setTextFill(Color.RED);

        JavaFxObservable.emitOnChanged(values)
                .flatMapSingle(list ->
                        Observable.fromIterable(list)
                                .map(String::length)
                                .distinct()
                                .reduce("", (x, y) -> x + (x.equals("") ? "" : "|") + y)
                ).subscribe(distinctLengthsConcatLabel::setText)
        ;

        TextField inputField = new TextField();
        Button addButton = new Button("ADD");

        JavaFxObservable.actionEventsOf(addButton)
                .map(ae -> inputField.getText())
                .filter(s -> s != null && !s.trim().isEmpty())
                .subscribe(s ->
                        {
                            values.add(s);
                            inputField.clear();
                        }
                );

        root.getChildren().addAll(
                new Label("VALUES"), new ListView<String>(values),
                new Label("DISTINCT LENGTHS"), distinctLengthsConcatLabel,
                inputField, addButton);
        stage.setScene(new Scene(root));
        stage.show();
    }
}