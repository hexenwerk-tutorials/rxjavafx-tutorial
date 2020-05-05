package cz.hexenwerk.ch8_concurrency;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public final class _6_observeOn_simulate_expensive_computation extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {

        VBox root = new VBox();

        ListView<String> listView = new ListView<>();

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .delay(3, TimeUnit.SECONDS, Schedulers.io())
                .toList()
                .observeOn(JavaFxScheduler.platform())
                .subscribe(list -> listView.getItems().setAll(list));

        root.getChildren().add(listView);

        stage.setScene(new Scene(root));

        stage.show();
    }
}
