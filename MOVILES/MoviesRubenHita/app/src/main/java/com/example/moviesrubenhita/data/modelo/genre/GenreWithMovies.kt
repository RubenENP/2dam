package com.example.moviesrubenhita.data.modelo.genre

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.moviesrubenhita.data.modelo.MovieGenreCrossRef
import com.example.moviesrubenhita.data.modelo.movie.MovieEntity

data class GenreWithMovies(
    @Embedded val genre: GenreEntity,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "movieId",
        associateBy = Junction(MovieGenreCrossRef::class)
    )
    val movies: List<MovieEntity>
)
