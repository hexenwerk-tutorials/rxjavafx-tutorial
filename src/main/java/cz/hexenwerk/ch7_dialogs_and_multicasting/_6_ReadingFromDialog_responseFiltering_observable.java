package cz.hexenwerk.ch7_dialogs_and_multicasting;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public final class _6_ReadingFromDialog_responseFiltering_observable extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {

        Observable<Boolean> response = JavaFxObservable.fromDialog(
                new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to proceed?"))
                .map((ButtonType r) -> r.equals(ButtonType.OK))
                .toObservable()
                .publish()
                .refCount();

        response.subscribe((Boolean r) -> System.out.println("Subscriber 1 received: " + r));

        response.subscribe((Boolean r) -> System.out.println("Subscriber 2 received: " + r));

        System.exit(0);
    }
}
