package cz.hexenwerk.ch3_evants_and_value_changes;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class _1_Turning_JavaFx_KeyEnvent_into_observable extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        VBox vBox = new VBox();
        ListView<String> listView = new ListView<>();

        for (int i = 0; i <= 9; i++)
        {
            listView.getItems().add(String.valueOf(i));
        }

        JavaFxObservable.eventsOf(listView, KeyEvent.KEY_TYPED)
                .map(KeyEvent::getCharacter)
                .filter(s -> s.matches("[0-9]"))
                .subscribe(s -> listView.getSelectionModel().select(s));

        vBox.getChildren().add(listView);
        stage.setScene(new Scene(vBox));
        stage.show();
    }
}
