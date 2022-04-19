package com.manimal.marvelcharacters.features.characters.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.manimal.marvelcharacters.databinding.FragmentCharactersDetailBinding
import com.manimal.marvelcharacters.features.base.BaseFragment
import com.manimal.marvelcharacters.features.base.BaseStatus
import com.manimal.marvelcharacters.features.characters.CharactersActivity
import com.manimal.marvelcharacters.features.characters.CharactersViewModel
import com.manimal.marvelcharacters.features.characters.list.CharactersListDataContainer
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersDetailFragment : BaseFragment<FragmentCharactersDetailBinding, CharactersActivity>() {

    private val viewModel: CharactersViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    //region Observers
    private fun initObservers() {
        viewModel.charactersListLiveData.observe(viewLifecycleOwner, charactersListObserver)
        viewModel.getCharactersList()
    }

    private val charactersListObserver = Observer<CharactersListDataContainer> {
        when (it.status) {
            BaseStatus.LOADING -> { }
            BaseStatus.SUCCESS -> {

            }
            BaseStatus.ERROR_CONNECTION -> {

            }
            BaseStatus.ERROR_UNKNOWN -> {

            }
        }
    }
    //endregion

    override fun getBindingClass(): FragmentCharactersDetailBinding {
        return FragmentCharactersDetailBinding.inflate(layoutInflater)
    }
}
