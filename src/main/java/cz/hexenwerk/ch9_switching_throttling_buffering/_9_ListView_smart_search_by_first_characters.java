package cz.hexenwerk.ch9_switching_throttling_buffering;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public final class _9_ListView_smart_search_by_first_characters extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {

        VBox root = new VBox();

        //Declare a ListView with all U.S. states
        ListView<String> listView = new ListView<>();
        List<String> states = Arrays.asList(getResponse("https://goo.gl/S0xuOi").split("\\r?\\n"));
        listView.getItems().setAll(states);

        //broadcast typed keys
        Observable<String> typedKeys = JavaFxObservable.eventsOf(listView, KeyEvent.KEY_TYPED)
                .map(KeyEvent::getCharacter)
                .publish().refCount();

        //immediately jump to state being typed
        typedKeys.debounce(200, TimeUnit.MILLISECONDS).startWith("")
                .switchMap(s ->
                        typedKeys.scan((x, y) -> x + y)
                                .switchMap(input ->
                                        Observable.fromIterable(states)
                                                .filter(st -> st.toUpperCase().startsWith(input.toUpperCase()))
                                                .take(1)
                                )
                ).observeOn(JavaFxScheduler.platform())
                .subscribe((String st) ->
                        listView.getSelectionModel().select(st)
                );

        root.getChildren().add(listView);

        stage.setScene(new Scene(root));

        stage.show();
    }

    private static String getResponse(String path)
    {
        try
        {
            return new Scanner(new URL(path).openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (Exception e)
        {
            return e.getMessage();
        }
    }
}