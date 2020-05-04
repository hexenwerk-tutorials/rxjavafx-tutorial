package cz.hexenwerk.ch7_dialogs_and_multicasting;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class _3_ReadingFromDialog_responseFiltering extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {

        Button runButton = new Button("Run Process");

        JavaFxObservable.actionEventsOf(runButton)
                .flatMap(ae ->
                        Observable.range(1, 10)
                                .flatMapMaybe((Integer i) -> JavaFxObservable.fromDialog(
                                        new Alert(Alert.AlertType.CONFIRMATION,
                                                "Are you sure you want to process integer " + i + "?",
                                                ButtonType.NO,
                                                ButtonType.YES))
                                        .filter((ButtonType response) -> response.equals(ButtonType.YES))
                                        .map((ButtonType response) -> i)))
                .subscribe((Integer i) -> System.out.println("Processed integer: " + i));

        VBox root = new VBox();
        root.getChildren().add(runButton);

        stage.setScene(new Scene(root));

        stage.show();
    }
}
