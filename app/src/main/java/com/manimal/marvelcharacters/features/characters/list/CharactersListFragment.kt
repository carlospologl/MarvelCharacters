package com.manimal.marvelcharacters.features.characters.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.manimal.marvelcharacters.R
import com.manimal.marvelcharacters.databinding.FragmentCharactersListBinding
import com.manimal.marvelcharacters.features.base.BaseFragment
import com.manimal.marvelcharacters.features.base.BaseStatus
import com.manimal.marvelcharacters.features.characters.CharactersActivity
import com.manimal.marvelcharacters.features.characters.CharactersViewModel
import com.manimal.marvelcharacters.utils.DialogUtils
import com.manimal.marvelcharacters.utils.DialogWrapper
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListFragment : BaseFragment<FragmentCharactersListBinding, CharactersActivity>() {

    private val viewModel: CharactersViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCharactersListList.layoutManager = LinearLayoutManager(requireContext())
        initObservers()
    }

    //region Observers
    private fun initObservers() {
        viewModel.apply {
            charactersListLiveData.observe(viewLifecycleOwner, charactersListObserver)
            getCharactersList()
        }
    }

    private val charactersListObserver = Observer<CharactersListDataContainer> {
        when (it.status) {
            BaseStatus.LOADING -> {
                binding.rvCharactersListList.loadSkeleton(R.layout.item_characters_list) {
                    itemCount(8)
                }
            }
            BaseStatus.SUCCESS -> {
                val results = it.data?.results
                binding.rvCharactersListList.hideSkeleton()
                if (results?.isNotEmpty() == true) {
                    val charactersListAdapter = CharactersListAdapter(
                        charactersList = results,
                        action = { nullableId ->
                            nullableId?.let { id ->
                                findNavController().navigate(
                                    CharactersListFragmentDirections.toCharactersDetailFragment(id)
                                )
                            }
                        }
                    )

                    binding.rvCharactersListList.adapter = charactersListAdapter
                }
            }
            BaseStatus.ERROR_CONNECTION -> {
                binding.rvCharactersListList.hideSkeleton()
                DialogUtils.createDialog(
                    requireActivity(),
                    DialogWrapper(
                        title = R.string.generic_error_internet_title,
                        message = R.string.generic_error_internet_message,
                        positiveMessage = R.string.generic_error_button_exit,
                        negativeMessage = R.string.generic_error_button_retry,
                        positiveAction = {
                            requireActivity().finish()
                        },
                        negativeAction = {
                            viewModel.getCharactersList()
                        }
                    )
                )
            }
            BaseStatus.FAILED -> {
                binding.rvCharactersListList.hideSkeleton()
                DialogUtils.createDialog(
                    requireActivity(),
                    DialogWrapper(
                        title = R.string.generic_error_unknown_title,
                        message = R.string.generic_error_unknown_message,
                        stringMessage = it.error?.message,
                        positiveMessage = R.string.generic_error_button_ok,
                        positiveAction = {
                            requireActivity().finish()
                        }
                    )
                )
            }
        }
    }
    //endregion

    override fun getBindingClass(): FragmentCharactersListBinding {
        return FragmentCharactersListBinding.inflate(layoutInflater)
    }
}
