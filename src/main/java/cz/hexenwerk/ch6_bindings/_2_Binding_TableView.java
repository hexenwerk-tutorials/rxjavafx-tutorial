package cz.hexenwerk.ch6_bindings;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.observers.JavaFxObserver;
import javafx.application.Application;
import javafx.beans.binding.Binding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class _2_Binding_TableView extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {

        TableView<Person> table = new TableView<>();
        table.setEditable(true);

        table.getItems().setAll(
                new Person("Thomas Nield", LocalDate.of(1989, 1, 18)),
                new Person("Sam Tulsa", LocalDate.of(1980, 5, 12)),
                new Person("Ron Johnson", LocalDate.of(1975, 3, 8))
        );

        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory((TableColumn.CellDataFeatures<Person, String> v) ->
                v.getValue().getNameProperty());

        TableColumn<Person, LocalDate> birthdayCol = new TableColumn<>("Birthday");
        birthdayCol.setCellValueFactory((TableColumn.CellDataFeatures<Person, LocalDate> v) ->
                v.getValue().getBirthdayProperty());
        birthdayCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

        TableColumn<Person, Long> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory((TableColumn.CellDataFeatures<Person, Long> v) ->
                v.getValue().getAgeBinding());

        table.getColumns().addAll(nameCol, birthdayCol, ageCol);
        stage.setScene(new Scene(table));
        stage.show();
    }


    public static class Person
    {

        private final StringProperty nameProperty;
        private final ObjectProperty<LocalDate> birthdayProperty;
        private final Binding<Long> ageBinding;

        public Person(String name, LocalDate birthday)
        {
            this.nameProperty = new SimpleStringProperty(name);
            this.birthdayProperty = new SimpleObjectProperty<>(birthday);

            this.ageBinding = JavaFxObserver.toBinding(
                    JavaFxObservable.valuesOf(birthdayProperty)
                            .map((LocalDate dt) -> ChronoUnit.YEARS.between(dt, LocalDate.now()))
            );
        }

        public StringProperty getNameProperty()
        {
            return nameProperty;
        }

        public ObjectProperty<LocalDate> getBirthdayProperty()
        {
            return birthdayProperty;
        }

        public Binding<Long> getAgeBinding()
        {
            return ageBinding;
        }

        public void dispose()
        {
            ageBinding.dispose();
        }
    }
}
