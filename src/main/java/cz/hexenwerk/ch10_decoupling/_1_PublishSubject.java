package cz.hexenwerk.ch10_decoupling;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public final class _1_PublishSubject extends Application
{
    private final Subject<String> textInputs = PublishSubject.create();

    @Override
    public void start(Stage stage) throws Exception
    {

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        Label label = new Label();

        //pass emissions to the Subject
        JavaFxObservable.valuesOf(textField1.textProperty()).subscribe(textInputs);
        JavaFxObservable.valuesOf(textField2.textProperty()).subscribe(textInputs);

        //receive emissions from the Subject
        textInputs.map(s -> new StringBuilder(s).reverse().toString())
                .subscribe(label::setText);

        VBox vBox = new VBox(textField1, textField2, label);

        stage.setScene(new Scene(vBox));
        stage.show();
    }
}