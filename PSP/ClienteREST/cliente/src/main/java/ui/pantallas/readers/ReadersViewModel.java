package ui.pantallas.readers;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import servicios.ReadersServicios;
import ui.pantallas.newspapers.NewspapersState;

import java.time.LocalDate;
import java.util.List;

public class ReadersViewModel {
    private final ReadersServicios readersServicios;

    @Inject
    ReadersViewModel(ReadersServicios readersServicios) {
        this.readersServicios = readersServicios;

        state = new SimpleObjectProperty<>(new ReadersState(FXCollections.observableArrayList(), null));
    }
    private final ObjectProperty<ReadersState> state;
    public ReadOnlyObjectProperty<ReadersState> getState() {
        return state;
    }


    public void getAllReaders() {
        readersServicios.getAllReaders()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ReadersState readersState;
                    if (either.isLeft()){
                        readersState = new ReadersState(null, either.getLeft());
                    } else {
                        readersState = new ReadersState(either.get(), null);
                    }

                    state.setValue(readersState);
                });
    }

    public void getReaderById(int id) {
        readersServicios.getReaderById(id)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ReadersState readersState;
                    if (either.isLeft()){
                        readersState = new ReadersState(null, either.getLeft());
                    } else {
                        readersState = new ReadersState(List.of(either.get()), null);
                    }

                    state.setValue(readersState);
                });
    }

    public void addReader(String name, LocalDate birthDate) {
        readersServicios.addReader(name, birthDate)
                .observeOn(Schedulers.single())
                .subscribe(
                        either -> {
                            ReadersState readersState;
                            if (either.isLeft()){
                                readersState = new ReadersState(null, either.getLeft());
                                state.setValue(readersState);
                            } else {
                                getAllReaders();
                            }
                        }
                );
    }

    public void updateReader(int id, String name, LocalDate birthDate) {
        readersServicios.updateReader(id, name, birthDate)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    ReadersState readersState;
                    if (either.isLeft()){
                        readersState = new ReadersState(null, either.getLeft());
                        state.setValue(readersState);
                    } else {
                        getAllReaders();
                    }
                });
    }
}
