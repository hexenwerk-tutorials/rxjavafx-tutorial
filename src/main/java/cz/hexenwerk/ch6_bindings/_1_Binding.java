package cz.hexenwerk.ch6_bindings;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class _1_Binding extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        VBox root = new VBox();

        Label label = new Label("Input a 6-character String");

        TextField input = new TextField();

        Button button = new Button("Proceed");

        // 1. JavaFx bind()
//        button.disableProperty()
//                .bind(input.textProperty()
//                        .length()
//                        .isNotEqualTo(6)
//                );

        // 2.
        JavaFxObservable.valuesOf(input.textProperty())
                .map(s -> s.length() != 6)
                .subscribe(b -> button.disableProperty().setValue(b));

        root.getChildren().addAll(label, input, button);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
