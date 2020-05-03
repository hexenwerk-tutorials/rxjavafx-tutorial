package cz.hexenwerk.ch4;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public final class _5_ObservableArrayList extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        ObservableList<String> values = FXCollections.observableArrayList("Alpha", "Beta", "Gamma");

        JavaFxObservable.changesOf(values)
                .subscribe(System.out::println);

        values.add("Delta");
        values.add("Epsilon");
        values.remove("Alpha");
        values.set(2, "Eta");
        System.exit(0);
    }
}