package cz.hexenwerk.ch8_concurrency;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class _5_observeOn extends Application
{
    @Override
    public void start(Stage stage)
    {

        VBox root = new VBox();

        ListView<String> listView = new ListView<>();

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.io())
                .toList()
                .observeOn(JavaFxScheduler.platform())
                .subscribe(list -> listView.getItems().setAll(list));

        root.getChildren().add(listView);

        stage.setScene(new Scene(root));

        stage.show();
    }
}
