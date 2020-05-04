package cz.hexenwerk.ch5_combining_observables;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This seems like a good idea, right? When I select a letter, and I select a number, the two are zipped together and sent to the Observer! But there is something subtle and problematic with this. Select multiple letters without selecting any numbers, then select multiple numbers. Notice how the letters are backlogged and each one is waiting for a number to be paired with? This is problematic and probably not what you want. If you select "A", then "B", then "C" followed by "1", then "2", then "3", you are going to get "A-1", "B-2", and "C-3" printed to the console.
 * Here is another way of looking at it. The problem with our zipping example is for every selected "letter", you need to select a "number" to evenly pair with it. If you make several selections to one combo box and neglect to make selections on the other, you are going to have a backlog of emissions waiting to be paired. If you select eight different letters (shown below), and only four numbers, the next number you select is going to pair with the "D", not "F" which is currently selected. If you select another letter its only going to worsen the backlog and make it more confusing as to what the next number will pair with.
 * */
public final class _5_Zipping_UI_example_not_optimal extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {

        ComboBox<String> letterCombo = new ComboBox<>();
        letterCombo.getItems().setAll("A", "B", "C", "D", "E", "F");

        ComboBox<Integer> numberCombo = new ComboBox<>();
        numberCombo.getItems().setAll(1, 2, 3, 4, 5);

        Observable<String> letterSelections =
                JavaFxObservable.valuesOf(letterCombo.valueProperty());

        Observable<Integer> numberSelections =
                JavaFxObservable.valuesOf(numberCombo.valueProperty());

        Observable.zip(letterSelections, numberSelections, (l, n) -> l + "-" + n)
                .subscribe(System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done!")
                );

        HBox root = new HBox();
        root.getChildren().setAll(letterCombo, numberCombo);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
