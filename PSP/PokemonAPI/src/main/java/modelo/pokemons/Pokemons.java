package modelo.pokemons;

import lombok.Data;

@Data
public class Pokemons {
    int count;
    String next;
    String previous;
    Results results;
}
