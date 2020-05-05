package cz.hexenwerk.ch8_concurrency;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Scanner;

public final class _7_observeOn_url_reader_sample extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        VBox vBox = new VBox();
        Label label = new Label("Input URL");
        TextField input = new TextField();
        TextArea output = new TextArea();
        Button button = new Button("Submit");

        output.setWrapText(true);

        JavaFxObservable.actionEventsOf(button)
                .map(ae -> input.getText())
                .observeOn(Schedulers.io())
                .map(_7_observeOn_url_reader_sample::getResponse)
                .observeOn(JavaFxScheduler.platform())
                .subscribe(output::setText);

        vBox.getChildren().setAll(label, input, output, button);
        stage.setScene(new Scene(vBox));
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
