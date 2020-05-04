package cz.hexenwerk.ch2_fundamentals;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class _6_Cold_vs_Hot_Observables extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {

        VBox vBox = new VBox();
        Button button = new Button("Press Me");
        Button secondSubButton = new Button("Subscribe Observer 2");

        Observable<ActionEvent> clicks = JavaFxObservable.actionEventsOf(button);

        //Observer 1
        clicks.subscribe(ae -> System.out.println("Observer 1 Received Click!"));

        //Subscribe Observer 2 when secondSubButton is clicked
        secondSubButton.setOnAction(event ->
        {
            System.out.println("Observer 2 subscribing!");
            secondSubButton.disableProperty().set(true);
            //Observer 2
            clicks.subscribe(ae -> System.out.println("Observer 2 Received Click!"));
        });

        vBox.getChildren().addAll(button, secondSubButton);

        stage.setScene(new Scene(vBox));
        stage.show();
    }
}
