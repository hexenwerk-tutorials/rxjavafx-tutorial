package cz.hexenwerk.ch3_evants_and_value_changes;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public final class _3_ActionEventsOf extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        VBox vBox = new VBox();
        Button button = new Button("Press Me!");

        JavaFxObservable.actionEventsOf(button)
                .subscribe((ActionEvent ae) -> System.out.println("Pressed!"));

        vBox.getChildren().add(button);
        stage.setScene(new Scene(vBox));
        stage.show();
    }
}