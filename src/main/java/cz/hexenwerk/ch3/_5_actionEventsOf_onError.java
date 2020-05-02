package cz.hexenwerk.ch3;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public final class _5_actionEventsOf_onError extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        VBox root = new VBox();
        Label label = new Label("Input Number");
        TextField input = new TextField();
        Label totalLabel = new Label();
        Button button = new Button("Add to Total");


//        JavaFxObservable.actionEventsOf(button)
//                .map(ae -> Integer.valueOf(input.getText()))
//                .scan(0, (x, y) -> x + y)
//                .subscribe(i ->
//                        {
//                            totalLabel.setText(i.toString());
//                            input.clear();
//                        },
//                        e -> new Alert(Alert.AlertType.ERROR, e.getMessage()).show()
//                );

//        JavaFxObservable.actionEventsOf(button)
//                .map(ae -> Integer.valueOf(input.getText()))
//                .scan(0, (x, y) -> x + y)
//                .retry() // registers as observable after error
//                .subscribe(i ->
//                        {
//                            totalLabel.setText(i.toString());
//                            input.clear();
//                        },
//                        e -> new Alert(Alert.AlertType.ERROR, e.getMessage()).show()
//                );

//        JavaFxObservable.actionEventsOf(button)
//                .map(ae -> Integer.valueOf(input.getText()))
//                .scan(0, (x, y) -> x + y)
//                .doOnError(e -> new Alert(Alert.AlertType.ERROR, e.getMessage()).show())
//                .retry()
//                .subscribe(i -> totalLabel.setText(i.toString()));

        JavaFxObservable.actionEventsOf(button)
                .map(ae -> input.getText())
                .filter(s -> s.matches("[0-9]+"))
                .map(Integer::valueOf)
                .scan(0, (x, y) -> x + y)
                .subscribe(i ->
                        {
                            totalLabel.setText(i.toString());
                            input.clear();
                        }
                );

        root.getChildren().setAll(label, input, totalLabel, button);
        stage.setScene(new Scene(root));
        stage.show();
    }
}