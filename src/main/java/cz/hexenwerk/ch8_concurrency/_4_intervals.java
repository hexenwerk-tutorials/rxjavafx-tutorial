package cz.hexenwerk.ch8_concurrency;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public final class _4_intervals extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {

        VBox root = new VBox();

        Label label = new Label();

        Observable.interval(1, TimeUnit.SECONDS, JavaFxScheduler.platform())
                .map(Object::toString)
                .subscribe(label::setText);

        root.getChildren().add(label);

        stage.setScene(new Scene(root));

        stage.setMinWidth(60);
        stage.show();
    }
}
