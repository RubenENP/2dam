package dao.impl;


import dao.CharacterDao;
import dao.EpisodesDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.characters.Characters;
import modelo.characters.Result;
import dao.retrofit.llamadas.CharactersAPI;
import retrofit2.Response;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharacterDaoImpl implements CharacterDao {
    final EpisodesDao episodesDao;
    private final CharactersAPI charactersAPI;
    @Inject
    CharacterDaoImpl(EpisodesDao episodesDao, CharactersAPI charactersAPI){
        this.episodesDao = episodesDao;
        this.charactersAPI = charactersAPI;
    }
    public Either<String, List<Result>> getCharactersByName(String name)  {
        Either<String, List<Result>> respuesta;

        Response<Characters> r;
        try {
           r = charactersAPI.getCharacterByName(name).execute();

           if(r.isSuccessful()){
               if(r.body() == null){
                   respuesta = Either.left("El personaje no existe");
               }else {
                   respuesta = Either.right(r.body().results());
               }
           }else{
               respuesta = Either.left("El personaje no existe");
           }
        }catch (IOException e){
            respuesta = Either.left(e.getMessage());
        }

        return respuesta;
    }

    public Either<String, List<Result>> getCharactersByType(String type) {
        Either<String, List<Result>> respuesta;

        Response<Characters> r;

        try{
            r = charactersAPI.getCharacterByType(type, 1).execute();

            if (r.isSuccessful()){
                if (r.body() == null){
                    respuesta = Either.left("No se encontraron personajes");
                }else {
                    int pageLength = r.body().info().pages();
                    List<Result> resultList = new ArrayList<>();
                    for (int i = 1; i <= pageLength; i++) {
                        resultList.addAll(Objects.requireNonNull(charactersAPI.getCharacterByType(type, i).execute().body()).results());
                    }
                    respuesta = Either.right(resultList);
                }
            }else {
                respuesta = Either.left("No se encontraron personajes");
            }
        }catch (IOException e){
            respuesta = Either.left(e.getMessage());
        }

        return respuesta;
    }

    static final String MENSAJE = "ERROR no cargaron los caracteres";
    public Either<String, List<Result>> getCharactersByEpisode(int id) {
        Either<String, List<Result>> respuesta;

        Response<Characters> r;

        try {
            r = charactersAPI.getCharacterByPage(1).execute();
            if (r.isSuccessful()){
                if (r.body() == null){
                    respuesta = Either.left(MENSAJE);
                }else {
                    List<modelo.episodes.Result> listaEpisodios = episodesDao.getAllEpisodes().get();
                    modelo.episodes.Result episodio = listaEpisodios.stream().filter(idEpisodio -> idEpisodio.getId() == id)
                            .findFirst().orElse(null);

                    List<Result> resultList = new ArrayList<>();

                    for (int i = 1; i <= Objects.requireNonNull(r.body()).info().pages(); i++) {
                        resultList.addAll(Objects.requireNonNull(charactersAPI.getCharacterByPage(i).execute().body()).results());
                    }

                    if(episodio == null){
                        respuesta = Either.left(MENSAJE);
                    }else {
                        List<String> charactersUrlList = episodio.getCharacters();
                        List<Result> listaCharacterResultados = new ArrayList<>();

                        for (String url : charactersUrlList) {
                            int id1 = Integer.parseInt(url.substring(42));

                            listaCharacterResultados.add(resultList.stream().filter(resultado -> resultado.getId() == id1).findFirst().orElse(null));
                        }

                        respuesta = Either.right(listaCharacterResultados);
                    }
                }
            }else {
                respuesta = Either.left(MENSAJE);
            }
        } catch (IOException e){
            respuesta = Either.left(e.getMessage());
        }

        return respuesta;
    }
}
