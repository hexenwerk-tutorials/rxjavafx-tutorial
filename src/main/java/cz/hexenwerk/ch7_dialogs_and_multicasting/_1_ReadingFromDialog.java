package cz.hexenwerk.ch7_dialogs_and_multicasting;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public final class _1_ReadingFromDialog extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {

        JavaFxObservable.fromDialog(
                new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to do this?")
        ).subscribe(response -> System.out.println("You pressed " + response.getText()));

        System.exit(0);
    }
}
