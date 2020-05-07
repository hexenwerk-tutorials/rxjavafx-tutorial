package cz.hexenwerk.ch10_decoupling;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;


public final class _2_TableRefresher extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        VBox root = new VBox();

        //make refresh Button
        Button button1 = new Button("Refresh");
        JavaFxObservable.actionEventsOf(button1)
                .subscribe(MyEventModel.getInstance().getRefreshRequests());

        //make refresh MenuItem
        Button button2 = new Button("Refresh");
        JavaFxObservable.actionEventsOf(button2)
                .subscribe(MyEventModel.getInstance().getRefreshRequests());

        //CTRL + R hotkeys on a TableView
        TableView<Person> table = createTableView();

        root.getChildren().addAll(button1, button2, table);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private TableView<Person> createTableView()
    {
        TableView<Person> table = new TableView<>();

        JavaFxObservable.eventsOf(table, KeyEvent.KEY_PRESSED)
                .filter(ke -> ke.isControlDown() && ke.getCode().equals(KeyCode.R))
                .map(ke -> new ActionEvent())
                .subscribe(MyEventModel.getInstance().getRefreshRequests());

        AtomicInteger yearCounter = new AtomicInteger(1979);
        MyEventModel.getInstance().getRefreshRequests()
                .subscribe((ActionEvent ae) ->
                        table.getItems().setAll(
                                new Person("Thomas Nield", LocalDate.of(yearCounter.incrementAndGet(), 1, 18)),
                                new Person("Sam Tulsa", LocalDate.of(1980, 5, 12)),
                                new Person("Ron Johnson", LocalDate.of(1975, 3, 8))
                        )
                );


        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory((TableColumn.CellDataFeatures<Person, String> v) ->
                v.getValue().getNameProperty());

        TableColumn<Person, LocalDate> birthdayCol = new TableColumn<>("Birthday");
        birthdayCol.setCellValueFactory((TableColumn.CellDataFeatures<Person, LocalDate> v) ->
                v.getValue().getBirthdayProperty());
        birthdayCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

        table.getColumns().addAll(nameCol, birthdayCol);
        return table;
    }

    private static class MyEventModel
    {
        private static final MyEventModel instance = new MyEventModel();

        private final Subject<ActionEvent> refreshRequests = PublishSubject.create();

        private MyEventModel()
        {
        }

        public static MyEventModel getInstance()
        {
            return instance;
        }

        public Subject<ActionEvent> getRefreshRequests()
        {
            return refreshRequests;
        }
    }

    public static class Person
    {

        private final StringProperty nameProperty;
        private final ObjectProperty<LocalDate> birthdayProperty;

        public Person(String name, LocalDate birthday)
        {
            this.nameProperty = new SimpleStringProperty(name);
            this.birthdayProperty = new SimpleObjectProperty<>(birthday);
        }

        public StringProperty getNameProperty()
        {
            return nameProperty;
        }

        public ObjectProperty<LocalDate> getBirthdayProperty()
        {
            return birthdayProperty;
        }
    }
}