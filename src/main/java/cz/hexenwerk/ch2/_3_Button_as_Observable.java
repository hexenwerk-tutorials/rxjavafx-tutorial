package cz.hexenwerk.ch2;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class _3_Button_as_Observable extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {

        VBox vBox = new VBox();
        Button button = new Button("Press Me");

        JavaFxObservable.actionEventsOf(button).subscribe(System.out::println);

        vBox.getChildren().add(button);
        stage.setScene(new Scene(vBox));
        stage.show();
    }
}
