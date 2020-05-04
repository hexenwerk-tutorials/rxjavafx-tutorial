package cz.hexenwerk.ch7_dialogs_and_multicasting;

import io.reactivex.Maybe;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public final class _4_ReadingFromDialog_responseFiltering_maybe extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {

        Maybe<Boolean> response = JavaFxObservable.fromDialog(
                new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to proceed?"))
                .map((ButtonType r) -> r.equals(ButtonType.OK));

        response.subscribe((Boolean r) -> System.out.println("Subscriber 1 received: " + r));

        response.subscribe((Boolean r) -> System.out.println("Subscriber 2 received: " + r));

        System.exit(0);
    }
}
