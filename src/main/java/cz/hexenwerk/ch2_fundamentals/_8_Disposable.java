package cz.hexenwerk.ch2_fundamentals;

import io.reactivex.disposables.Disposable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class _8_Disposable extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        VBox vBox = new VBox();
        Button button = new Button("Press Me");
        Button unsubscribeButton = new Button("Unsubscribe");
        Label countLabel = new Label("0");

        Disposable disposable = JavaFxObservable.actionEventsOf(button)
                .map(ae -> 1)
                .scan(0, (x, y) -> x + y)
                .subscribe(clickCount -> countLabel.setText(clickCount.toString()));

        unsubscribeButton.setOnAction(e -> disposable.dispose());

        vBox.getChildren().addAll(button, unsubscribeButton, countLabel);
        stage.setScene(new Scene(vBox));
        stage.show();
    }
}
