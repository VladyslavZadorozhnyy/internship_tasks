package com.example.imdbpractise

import android.app.Application
import com.example.imdbpractise.bl.ContactsDatabase
import com.example.imdbpractise.bl.films.FilmsManager
import com.example.imdbpractise.bl.films.FilmsManagerImpl
import com.example.imdbpractise.bl.genres.GenresManager
import com.example.imdbpractise.bl.genres.GenresManagerImpl
import com.example.imdbpractise.bl.tmdb.TmdbManager
import com.example.imdbpractise.bl.tmdb.TmdbManagerImpl
import com.example.imdbpractise.ui.MainViewModel
import com.example.imdbpractise.ui.film.FilmViewModel
import com.example.imdbpractise.ui.filmList.FilmListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class App: Application() {
    override fun onCreate() {
        super.onCreate()

        val database = ContactsDatabase.newInstance(this)

        startKoin {
            val blModule = module {
                single<TmdbManager> { TmdbManagerImpl() }
                single<FilmsManager> { FilmsManagerImpl(get(), database.filmDao) }
                single<GenresManager> { GenresManagerImpl(get(), database.genreDao) }
            }

            val viewModelsModule = module {
                viewModel { MainViewModel(get()) }
                viewModel { FilmListViewModel(it[0], get()) }
                viewModel { FilmViewModel(it[0], get()) }
            }

            androidContext(this@App)
            modules(blModule, viewModelsModule)
        }
    }
}