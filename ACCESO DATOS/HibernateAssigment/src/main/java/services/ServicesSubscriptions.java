package services;

import io.vavr.control.Either;
import model.Subscription;

import java.util.List;

public interface ServicesSubscriptions {
    Either<String, List<Subscription>> get(int idReader);
}
