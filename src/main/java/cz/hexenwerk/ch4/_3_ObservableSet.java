package cz.hexenwerk.ch4;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.stage.Stage;

public final class _3_ObservableSet extends Application
{
    @Override
    public void start(Stage stage)
    {
        ObservableSet<String> values = FXCollections.observableSet("Alpha", "Beta", "Gamma");

        JavaFxObservable.emitOnChanged(values)
                .subscribe(System.out::println);

        values.add("Delta");
        values.add("Alpha"); //no effect
        values.remove("Beta");

        System.exit(0); //quit
    }
}