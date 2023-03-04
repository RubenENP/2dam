package ui.pantallas.newspapers;

import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import servicios.NewspapersServicios;

import java.time.LocalDate;
import java.util.List;

public class NewspapersViewModel {
    private final NewspapersServicios newspaperServicios;

    @Inject
    NewspapersViewModel(NewspapersServicios newspaperServicios){
        this.newspaperServicios = newspaperServicios;

        state = new SimpleObjectProperty<>(new NewspapersState(FXCollections.observableArrayList(), null));
    }
    private final ObjectProperty<NewspapersState> state;
    public ReadOnlyObjectProperty<NewspapersState> getState() {
        return state;
    }

    public void getAllNewspapers(){
        newspaperServicios.getAllNewspapers()
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    NewspapersState newspapersState;

                    if (either.isLeft()){
                        newspapersState = new NewspapersState(null, either.getLeft());
                    } else {
                        newspapersState = new NewspapersState(either.get(), null);
                    }

                    state.setValue(newspapersState);
                });
    }

    public void getNewspaperById(int id) {
        newspaperServicios.getUnNewspaper(id)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                   NewspapersState newspapersState;

                    if (either.isLeft()){
                        newspapersState = new NewspapersState(null, either.getLeft());
                    } else {
                        newspapersState = new NewspapersState(List.of(either.get()), null);
                    }

                    state.setValue(newspapersState);
                });
    }

    public void addNewspaper(String name, LocalDate releaseDate) {
        newspaperServicios.postNewspaper(0, name, releaseDate)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    NewspapersState newspapersState;

                    if (either.isLeft()){
                        newspapersState = new NewspapersState(null, either.getLeft());
                    } else {
                        newspapersState = new NewspapersState(List.of(either.get()), null);
                    }

                    state.setValue(newspapersState);
                });
    }

    public void deleteNewspaper(int id) {
        newspaperServicios.deleteNewspaper(id)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    NewspapersState newspapersState;

                    if (either.isLeft()){
                        newspapersState = new NewspapersState(null, either.getLeft());
                        state.setValue(newspapersState);
                    } else {
                        getAllNewspapers();
                    }
                });
    }
}
