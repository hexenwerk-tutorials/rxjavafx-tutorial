package cz.hexenwerk.ch2;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class _4_Button_Click_Counter_with_Scan extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        VBox vBox = new VBox();
        Button button = new Button("Press Me");
        Label countLabel = new Label("0");

        JavaFxObservable.actionEventsOf(button)
                .map(ae -> 1)
                .scan(0, (x, y) -> x + y)
                .subscribe(clickCount -> countLabel.setText(clickCount.toString()));

        vBox.getChildren().add(countLabel);
        vBox.getChildren().add(button);
        stage.setScene(new Scene(vBox));
        stage.show();
    }
}
