package cz.hexenwerk.ch3;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Optional;


public final class _4_valuesOf extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        HBox hBox = new HBox();

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().setAll("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        JavaFxObservable.valuesOf(comboBox.valueProperty())
                .subscribe((String v) -> System.out.println(v + " was selected"));

        JavaFxObservable.valuesOf(comboBox.valueProperty())
                .map(String::length)
                .subscribe(i -> System.out.println("A String with length " + i + " was selected"));

        JavaFxObservable.valuesOf(comboBox.valueProperty())
                .map(String::length)
                .scan(0, (x, y) -> x + y)
                .subscribe(i -> System.out.println("Rolling length total: " + i));


        /* handling nullable values*/
        ComboBox<String> nullableComboBox = new ComboBox<>();
        nullableComboBox.getItems().setAll(null, "Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        JavaFxObservable.valuesOf(comboBox.valueProperty(), "N/A")
                .subscribe((String v) -> System.out.println(v + " was selected"));

        JavaFxObservable.nullableValuesOf(nullableComboBox.valueProperty())
                .subscribe((Optional<String> v) -> System.out.println(v.orElse("N/A") + " was selected"));

        hBox.getChildren().addAll(comboBox, nullableComboBox);
        stage.setScene(new Scene(hBox));
        stage.show();
    }
}