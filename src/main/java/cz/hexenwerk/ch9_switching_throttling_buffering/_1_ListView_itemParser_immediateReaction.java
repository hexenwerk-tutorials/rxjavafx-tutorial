package cz.hexenwerk.ch9_switching_throttling_buffering;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public final class _1_ListView_itemParser_immediateReaction extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {

        VBox root = new VBox();

        ListView<String> listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listView.getItems().setAll("Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta");

        ListView<String> itemsView = new ListView<>();

        JavaFxObservable.emitOnChanged(listView.getSelectionModel().getSelectedItems())
                .flatMapSingle((ObservableList<String> list) -> Observable.fromIterable(list)
                        .flatMap((String s) -> Observable.fromArray(s.split("(?!^)")))
                        .toList()
                ).subscribe((List<String> l) -> itemsView.getItems().setAll(l));

        root.getChildren().addAll(listView, itemsView);

        stage.setScene(new Scene(root));

        stage.show();
    }
}