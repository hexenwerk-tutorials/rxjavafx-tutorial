package cz.hexenwerk.ch9_switching_throttling_buffering;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class _4_Swithch_off_infinite_process_with_switchMap extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        VBox vBox = new VBox();

        ToggleButton toggleButton = new ToggleButton("START");
        Label timerLabel = new Label("0");

        JavaFxObservable.valuesOf(toggleButton.selectedProperty())
                .switchMap((Boolean selected) ->
                {
                    if (selected)
                    {
                        toggleButton.setText("STOP");
                        return Observable.interval(10, TimeUnit.MILLISECONDS);
                    } else
                    {
                        toggleButton.setText("START");
                        return Observable.empty();
                    }
                })
                .observeOn(JavaFxScheduler.platform())
                .subscribe((Long i) -> timerLabel.setText(i.toString()));

        vBox.getChildren().setAll(toggleButton, timerLabel);
        stage.setScene(new Scene(vBox));
        stage.show();
    }
}