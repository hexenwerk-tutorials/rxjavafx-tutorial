package cz.hexenwerk.ch4_collections;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public final class _6_ObservableValue extends Application
{

    @Override
    public void start(Stage stage)
    {

        ObservableList<User> values =
                FXCollections.observableArrayList(user -> new ObservableValue[]{user.nameProperty()});

        JavaFxObservable.changesOf(values)
                .subscribe(System.out::println);

        values.add(new User(503, "Tom Nield"));
        values.add(new User(504, "Jason Shwartz"));

        values.get(0).nameProperty().setValue("Thomas Nield");

        System.exit(0);
    }

    static final class User
    {
        private final int id;
        private final Property<String> name = new SimpleStringProperty();

        User(int id, String name)
        {
            this.id = id;
            this.name.setValue(name);
        }

        public int getId()
        {
            return id;
        }

        public Property<String> nameProperty()
        {
            return name;
        }

        @Override
        public String toString()
        {
            return id + "-" + name.getValue();
        }
    }
}