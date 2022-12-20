package com.example.imdbpractise.ui.filmList

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbpractise.databinding.FilmListFragmentBinding
import com.example.imdbpractise.ui.BindingFragment
import org.koin.core.parameter.parametersOf
import org.koin.androidx.viewmodel.ext.android.viewModel


class FilmListFragment: BindingFragment<FilmListFragmentBinding>(FilmListFragmentBinding::inflate) {
    companion object {
        fun newInstance(genreId: Long) = FilmListFragment().apply {
            arguments = bundleOf("genreId" to genreId)
        }
    }

    interface FilmChosen {
        fun onFilmClicked(filmId: Long)
    }

    private lateinit var filmsAdapter: FilmsAdapter

    private val viewModel by viewModel<FilmListViewModel>{
        parametersOf(requireArguments().getLong("genreId"))
    }

    override fun onBindingCreated(binding: FilmListFragmentBinding, savedInstanceState: Bundle?) {
        filmsAdapter = FilmsAdapter(
            viewLifecycleOwner,
            viewModel.adapterFilmsData,
            viewModel.noItemsLeftLiveData,
            requireContext(),
            { (activity as FilmChosen).onFilmClicked(it) },
            { viewModel.loadFilms(it) }
        )

//        viewModel.adapterFilmsData.observe(viewLifecycleOwner) {
//            filmsAdapter.items = it
//        }
//
//        viewModel.noItemsLeftLiveData.observe(viewLifecycleOwner) {
//            filmsAdapter.noItemsLeft = it
//        }

        binding.shortFilmRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.shortFilmRecycler.adapter = filmsAdapter
    }
}