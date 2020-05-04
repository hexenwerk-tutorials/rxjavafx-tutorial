package cz.hexenwerk.ch7_dialogs_and_multicasting;

import io.reactivex.observables.ConnectableObservable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public final class _5_ReadingFromDialog_responseFiltering_connectableObservable extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {

        ConnectableObservable<Boolean> response = JavaFxObservable.fromDialog(
                new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to proceed?"))
                .map((ButtonType r) -> r.equals(ButtonType.OK))
                .toObservable()
                .publish(); //returns ConnectableObservable

        response.subscribe((Boolean r) -> System.out.println("Subscriber 1 received: " + r));

        response.subscribe((Boolean r) -> System.out.println("Subscriber 2 received: " + r));

        response.connect();

        System.exit(0);
    }
}
