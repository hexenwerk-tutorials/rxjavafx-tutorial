package cz.hexenwerk.ch6_bindings;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.observers.JavaFxObserver;
import io.reactivex.rxjavafx.subscriptions.CompositeBinding;
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

public final class _3_CompositeBinding_TableView extends Application
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

        TableColumn<Person, Boolean> isAdultCol = new TableColumn<>("Is Adult?");
        isAdultCol.setCellValueFactory((TableColumn.CellDataFeatures<Person, Boolean> v) ->
                v.getValue().isAdultBinding());

        table.getColumns().addAll(nameCol, birthdayCol, ageCol, isAdultCol);
        stage.setScene(new Scene(table));
        stage.show();
    }


    public static class Person
    {

        private final StringProperty nameProperty;
        private final ObjectProperty<LocalDate> birthdayProperty;
        private final Binding<Long> ageBinding;
        private final Binding<Boolean> isAdultBinding;

        private final CompositeBinding bindings = new CompositeBinding();

        Person(String name, LocalDate birhday)
        {
            this.nameProperty = new SimpleStringProperty(name);
            this.birthdayProperty = new SimpleObjectProperty<>(birhday);

            this.ageBinding = JavaFxObservable.valuesOf(birthdayProperty)
                    .map(dt -> ChronoUnit.YEARS.between(dt, LocalDate.now()))
                    // .to(JavaFxObserver::toBinding);
                    .to(JavaFxObserver::toLazyBinding);

            this.isAdultBinding = JavaFxObservable.valuesOf(ageBinding)
                    .map(age -> age >= 18)
                    .to(JavaFxObserver::toBinding);
                    //.to(JavaFxObserver::toLazyBinding);

            bindings.add(ageBinding);
            bindings.add(isAdultBinding);
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

        public Binding<Boolean> isAdultBinding()
        {
            return isAdultBinding;
        }

        public void dispose()
        {
            bindings.dispose();
        }
    }
}
