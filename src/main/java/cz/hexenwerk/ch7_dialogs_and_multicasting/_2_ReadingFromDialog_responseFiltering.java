package cz.hexenwerk.ch7_dialogs_and_multicasting;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public final class _2_ReadingFromDialog_responseFiltering extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {

        Button runButton = new Button("Run Process");

        JavaFxObservable.actionEventsOf(runButton)
                .flatMapMaybe((ActionEvent ae) ->
                        JavaFxObservable.fromDialog(
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Are you sure you want to run the process?"))
                                .filter(response -> response.equals(ButtonType.OK)))
                .flatMapSingle((ButtonType bt) -> Observable.range(1, 10).toList())
                .subscribe((List<Integer> li) -> System.out.println("Processed integer list: " + li));

        VBox root = new VBox();
        root.getChildren().add(runButton);

        stage.setScene(new Scene(root));

        stage.show();
    }
}
