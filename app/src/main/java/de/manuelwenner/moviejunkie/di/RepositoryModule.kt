package de.manuelwenner.moviejunkie.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.manuelwenner.moviejunkie.data.repository.IMovieRepository
import de.manuelwenner.moviejunkie.data.repository.MovieRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(
        movieRepository: MovieRepository
    ): IMovieRepository
}