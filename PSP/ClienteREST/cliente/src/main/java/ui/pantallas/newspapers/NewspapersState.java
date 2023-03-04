package ui.pantallas.newspapers;

import domain.model.Newspaper;

import java.util.List;

public record NewspapersState (List<Newspaper> getNewspapers, String error){}
