package cz.hexenwerk.ch5_combining_observables;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.ArrayList;

public final class _7_CombineLatest extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {

        //declare two ObservableLists
        ObservableList<String> startLocations =
                FXCollections.observableArrayList("Dallas", "Houston", "Chicago", "Boston");

        ObservableList<String> endLocations =
                FXCollections.observableArrayList("San Diego", "Salt Lake City", "Seattle");

        //this ObservableList will hold contents of both
        ObservableList<String> allLocations = FXCollections.observableArrayList();

        // 1.
        //this will pump both ObservableLists into `allLocations`
        Observable.combineLatest(
                JavaFxObservable.emitOnChanged(startLocations),
                JavaFxObservable.emitOnChanged(endLocations),
                (l1, l2) ->
                {
                    ArrayList<String> combined = new ArrayList<>();
                    combined.addAll(l1);
                    combined.addAll(l2);
                    return combined;
                }
        ).subscribe(allLocations::setAll);

        // 2.
        /* If you want to go a step further, you can easily modify this operation so that the combined ObservableList only contains distinct items from both
        ObservableLists. Simply add a flatMapSingle() before the Observer that intercepts the ArrayList, turns it into an Observable, distincts it, and
        collects it back into a List. Notice when you run it, the duplicate "Dallas" emission is held back.*/
        Observable.combineLatest(
                JavaFxObservable.emitOnChanged(startLocations),
                JavaFxObservable.emitOnChanged(endLocations),
                (l1, l2) ->
                {
                    ArrayList<String> combined = new ArrayList<>();
                    combined.addAll(l1);
                    combined.addAll(l2);
                    return combined;
                }
        ).flatMapSingle(l -> Observable.fromIterable(l).distinct().toList())
                .subscribe(allLocations::setAll);

        // 3.
        /* A more advanced but elegant way to accomplish either task above is to return an Observable<Observable<String>> from the combineLatest(), and then
        flatten it with a flatMap() afterwards. This avoids creating an intermediary ArrayList and is a bit leaner. */
        Observable.combineLatest(
                JavaFxObservable.emitOnChanged(startLocations),
                JavaFxObservable.emitOnChanged(endLocations),
                (l1, l2) -> Observable.fromIterable(l1).concatWith(Observable.fromIterable(l2))
        ).flatMapSingle(obs -> obs.distinct().toList())
                .subscribe(allLocations::setAll);

        //print `allLocations` every time it changes, to prove its working
        JavaFxObservable.emitOnChanged(allLocations).subscribe(System.out::println);

        //do modifications to trigger above operations
        startLocations.add("Portland");
        endLocations.add("Dallas");
        endLocations.add("Phoenix");
        startLocations.remove("Boston");

        System.exit(0);
    }
}
