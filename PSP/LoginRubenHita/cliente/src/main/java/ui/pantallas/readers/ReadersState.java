package ui.pantallas.readers;

import domain.model.reader.Reader;

import java.util.List;

public record ReadersState(List<Reader> readerList, String error) {
}
