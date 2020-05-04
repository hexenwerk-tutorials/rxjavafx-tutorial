package cz.hexenwerk.ch5_combining_observables;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class _3_Merge_counting_buttons extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        VBox root = new VBox();

        Label label = new Label("0");

        Button buttonUp = new Button("UP");
        Button buttonDown = new Button("DOWN");

        // 1.
        Observable
                .merge(JavaFxObservable.actionEventsOf(buttonUp).map(ae -> 1),
                        JavaFxObservable.actionEventsOf(buttonDown).map(ae -> -1))
                .scan(0, (x, y) -> x + y)
                .subscribe(i -> label.setText(i.toString()));

        // 2.
//        JavaFxObservable.actionEventsOf(buttonUp)
//                .map(ae -> 1)
//                .mergeWith(JavaFxObservable.actionEventsOf(buttonDown).map(ae -> -1))
//                .scan(0, (x, y) -> x + y)
//                .subscribe(i -> label.setText(i.toString()));

        root.getChildren().addAll(label, buttonUp, buttonDown);
        stage.setScene(new Scene(root));
        stage.show();
    }
}