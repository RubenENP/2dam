package modelo.locations;

import java.util.List;

public record Results(int id, String name, String type, String dimension, List<String> residents, String url, String created) {
}
