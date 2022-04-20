package com.manimal.marvelcharacters.features.characters.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.manimal.marvelcharacters.databinding.FragmentCharactersListBinding
import com.manimal.marvelcharacters.features.base.BaseFragment
import com.manimal.marvelcharacters.features.base.BaseStatus
import com.manimal.marvelcharacters.features.characters.CharactersActivity
import com.manimal.marvelcharacters.features.characters.CharactersViewModel
import com.manimal.marvelcharacters.utils.setGone
import com.manimal.marvelcharacters.utils.setVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListFragment : BaseFragment<FragmentCharactersListBinding, CharactersActivity>() {

    private val viewModel: CharactersViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    //region Observers
    private fun initObservers() {
        viewModel.apply {
            charactersListLiveData.observe(viewLifecycleOwner, charactersListObserver)
            getCharactersList(isNetworkNotConnected())
        }
    }

    private val charactersListObserver = Observer<CharactersListDataContainer> {
        when (it.status) {
            BaseStatus.LOADING -> { binding.pbCharactersListLoading.setVisible() }
            BaseStatus.SUCCESS -> {
                val results = it.data?.results
                binding.pbCharactersListLoading.setGone()
                if (results?.isNotEmpty() == true) {
                    val charactersListAdapter = CharactersListAdapter(
                        charactersList = results,
                        action = {

                        }
                    )

                    binding.rvCharactersListList.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = charactersListAdapter
                    }
                }
            }
            BaseStatus.ERROR_CONNECTION -> {
                binding.pbCharactersListLoading.setGone()
            }
            BaseStatus.ERROR_UNKNOWN -> {
                binding.pbCharactersListLoading.setGone()
            }
        }
    }
    //endregion

    override fun getBindingClass(): FragmentCharactersListBinding {
        return FragmentCharactersListBinding.inflate(layoutInflater)
    }
}
