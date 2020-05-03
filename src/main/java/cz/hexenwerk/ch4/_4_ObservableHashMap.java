package cz.hexenwerk.ch4;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.stage.Stage;

public final class _4_ObservableHashMap extends Application
{
    @Override
    public void start(Stage stage)
    {
        ObservableMap<Integer, String> values = FXCollections.observableHashMap();

        JavaFxObservable.emitOnChanged(values)
                .subscribe(System.out::println);

        values.put(1, "Alpha");
        values.put(2, "Beta");
        values.put(3, "Gamma");
        values.put(1, "Alpha"); //no effect
        values.put(3, "Delta");
        values.remove(2);
        System.exit(0);
    }
}