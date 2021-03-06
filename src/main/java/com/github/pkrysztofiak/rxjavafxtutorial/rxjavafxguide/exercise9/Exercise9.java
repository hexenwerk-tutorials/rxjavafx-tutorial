package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise9;

import com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise8.Player;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise9 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		ObservableList<Player> players = FXCollections.observableArrayList();

		for (int i = 0; i < 40; i++) {
			players.add(new Player("name" + String.valueOf(i) , "surname" + String.valueOf(i), null));
		}

		ListView<Player> listView = new ListView<>(players);

		listView.setCellFactory(lv -> new EmployeeListCell());

		Button removeButton = new Button("Remove");
		Button addButton = new Button("Add");
		Button gcButton = new Button("GC");

		JavaFxObservable.actionEventsOf(addButton).subscribe(addAction -> players.add(new Player("nameNew", "surnameNew", null)));
		JavaFxObservable.actionEventsOf(removeButton).subscribe(removeAction -> players.remove(players.size() - 1));
		JavaFxObservable.actionEventsOf(gcButton).subscribe(gcAction -> System.gc());

		VBox vBox = new VBox(listView, new HBox(addButton, removeButton, gcButton));
		VBox.setVgrow(listView, Priority.ALWAYS);

		stage.setScene(new Scene(vBox));
		stage.show();
	}
}