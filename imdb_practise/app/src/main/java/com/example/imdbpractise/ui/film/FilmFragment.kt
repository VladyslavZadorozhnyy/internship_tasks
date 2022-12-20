package com.example.imdbpractise.ui.film

import android.os.Bundle
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.imdbpractise.R
import com.example.imdbpractise.databinding.FilmLayoutBinding
import com.example.imdbpractise.ui.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FilmFragment: BindingFragment<FilmLayoutBinding>(FilmLayoutBinding::inflate) {
    companion object {
        fun newInstance(filmIdPassed: Long) = FilmFragment().apply {
            arguments = bundleOf("filmId" to filmIdPassed)
        }
    }

    private val viewModel by viewModel<FilmViewModel>{
        parametersOf(requireArguments().getLong("filmId"))
    }

    override fun onBindingCreated(binding: FilmLayoutBinding, savedInstanceState: Bundle?) {
        viewModel.filmLiveData.observe(viewLifecycleOwner) {
            binding.film = it

            Glide.with(this)
                .load(it.basicPosterUrl + it.posterPath)
                .placeholder(R.drawable.ic_baseline_local_movies_24)
                .error(R.drawable.error)
                .override(500, 500)
                .into(binding.posterView)

            if (it.homepage == "") {
                binding.homeLink = resources.getString(R.string.no_home_page_link)
            } else {
                binding.homeLink = it.homepage
            }
        }
    }
}