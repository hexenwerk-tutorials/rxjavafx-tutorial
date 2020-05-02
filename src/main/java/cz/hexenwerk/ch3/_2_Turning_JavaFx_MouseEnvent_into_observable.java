package cz.hexenwerk.ch3;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public final class _2_Turning_JavaFx_MouseEnvent_into_observable extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        VBox vBox = new VBox();

        Label positionLabel = new Label();

        Rectangle rectangle = new Rectangle(200, 200);
        rectangle.setFill(Color.RED);

        JavaFxObservable.eventsOf(rectangle, MouseEvent.MOUSE_MOVED)
                .map(me -> me.getX() + "-" + me.getY())
                .subscribe(positionLabel::setText);

        vBox.getChildren().addAll(positionLabel, rectangle);
        stage.setScene(new Scene(vBox));
        stage.show();
    }
}
