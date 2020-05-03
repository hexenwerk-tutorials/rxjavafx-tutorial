package cz.hexenwerk.ch4;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.sources.Flag;
import io.reactivex.rxjavafx.sources.ListChange;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class _8_DistinctChnagesOf extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {

        VBox root = new VBox();

        Label header1 = new Label("VALUES");
        ListView<String> listView = new ListView<>();

        Label header2 = new Label("DISTINCT VALUES");
        ListView<String> distinctListView = new ListView<>();

        JavaFxObservable.distinctChangesOf(listView.getItems())
                .subscribe((ListChange<String> c) ->
                        {
                            if (c.getFlag().equals(Flag.ADDED))
                                distinctListView.getItems().add(c.getValue());
                            else
                                distinctListView.getItems().remove(c.getValue());
                        }
                );

        TextField inputField = new TextField();

        Button addButton = new Button("Add");
        JavaFxObservable.actionEventsOf(addButton)
                .map((ActionEvent ae) -> inputField.getText())
                .subscribe((String s) ->
                        {
                            listView.getItems().add(s);
                            inputField.clear();
                        }
                );

        Button removeButton = new Button("Remove");
        JavaFxObservable.actionEventsOf(removeButton)
                .map((ActionEvent ae) -> inputField.getText())
                .subscribe((String s) ->
                        {
                            listView.getItems().remove(s);
                            inputField.clear();
                        }
                );

        root.getChildren().addAll(header1, listView, header2,
                distinctListView, inputField, addButton, removeButton);

        stage.setScene(new Scene(root));
        stage.show();
    }
}