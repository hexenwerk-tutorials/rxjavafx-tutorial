package cz.hexenwerk.ch5_combining_observables;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class _2_Merge extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        VBox root = new VBox();

        Button firstButton = new Button("Press Me");
        Button secondButton = new Button("Press Me Too");

        Observable.merge(
                JavaFxObservable.actionEventsOf(firstButton),
                JavaFxObservable.actionEventsOf(secondButton)
        ).subscribe(i -> System.out.println("You pressed one of the buttons!"));

        root.getChildren().addAll(firstButton, secondButton);
        stage.setScene(new Scene(root));
        stage.show();
    }
}