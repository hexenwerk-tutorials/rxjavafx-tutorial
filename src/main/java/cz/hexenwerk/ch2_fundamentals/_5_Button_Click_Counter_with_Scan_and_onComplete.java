package cz.hexenwerk.ch2_fundamentals;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class _5_Button_Click_Counter_with_Scan_and_onComplete extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        VBox vBox = new VBox();
        Button button = new Button("Press Me");
        Label countLabel = new Label("0");
        Label doneLabel = new Label("");

        JavaFxObservable.actionEventsOf(button)
                .map(ae -> 1)
                .scan(0, (x, y) -> x + y)
                .take(5)
                .subscribe(
                        clickCount -> countLabel.setText(clickCount.toString()),
                        Throwable::printStackTrace,
                        () -> doneLabel.setText("Done!")
                );

        vBox.getChildren().addAll(countLabel, doneLabel, button);
        stage.setScene(new Scene(vBox));
        stage.show();
    }
}
