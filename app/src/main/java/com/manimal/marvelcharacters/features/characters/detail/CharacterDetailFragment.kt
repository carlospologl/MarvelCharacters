package com.manimal.marvelcharacters.features.characters.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.manimal.marvelcharacters.R
import com.manimal.marvelcharacters.databinding.FragmentCharacterDetailBinding
import com.manimal.marvelcharacters.features.base.BaseFragment
import com.manimal.marvelcharacters.features.base.BaseStatus
import com.manimal.marvelcharacters.features.characters.CharactersActivity
import com.manimal.marvelcharacters.features.characters.CharactersViewModel
import com.manimal.marvelcharacters.features.characters.list.CharactersListDataContainer
import com.manimal.marvelcharacters.utils.DateUtils
import com.manimal.marvelcharacters.utils.DialogUtils
import com.manimal.marvelcharacters.utils.DialogWrapper
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding, CharactersActivity>() {

    private val viewModel: CharactersViewModel by viewModel()
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    //region Observers
    private fun initObservers() {
        viewModel.characterDetailLiveData.observe(viewLifecycleOwner, characterDetailObserver)
        viewModel.getCharacterDetail(isNetworkNotConnected(), args.characterId)
    }

    private val characterDetailObserver = Observer<CharactersListDataContainer> {
        when (it.status) {
            BaseStatus.LOADING -> {
                showSkeleton()
            }
            BaseStatus.SUCCESS -> {
                val results = it.data?.results
                hideSkeleton()
                if (results?.isNotEmpty() == true) {
                    val characterData = results[0]
                    binding.tvCharacterDetailName.text = characterData.name
                    binding.tvCharacterDetailDescription.text = characterData.description
                    characterData.modified?.let { date ->
                        binding.tvCharacterDetailLastModifiedValue.text = DateUtils.getDateWithFormat(
                            date,
                            DateUtils.FORMAT_DATE_COMPLETE_TIMEZONE,
                            DateUtils.FORMAT_DATE_DD_MM_YY
                        )
                    }


                    Glide.with(binding.root.context)
                        .load(characterData.thumbnail)
                        .into(binding.ivCharacterDetailImage)
                }
            }
            BaseStatus.ERROR_CONNECTION -> {
                hideSkeleton()
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
                            viewModel.getCharactersList(isNetworkNotConnected())
                        }
                    )
                )
            }
            BaseStatus.FAILED -> {
                hideSkeleton()
                DialogUtils.createDialog(
                    requireActivity(),
                    DialogWrapper(
                        title = R.string.generic_error_internet_title,
                        message = R.string.generic_error_internet_message,
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

    //region skeleton
    private fun showSkeleton() {
        binding.apply {
            ivCharacterDetailImage.loadSkeleton()
            tvCharacterDetailName.loadSkeleton(length = 16)
            tvCharacterDetailDescription.loadSkeleton(length = 200)
            tvCharacterDetailLastModifiedValue.loadSkeleton(length = 12)
        }
    }

    private fun hideSkeleton() {
        binding.apply {
            ivCharacterDetailImage.hideSkeleton()
            tvCharacterDetailName.hideSkeleton()
            tvCharacterDetailDescription.hideSkeleton()
            tvCharacterDetailLastModifiedValue.hideSkeleton()
        }
    }
    //endregion

    override fun getBindingClass(): FragmentCharacterDetailBinding {
        return FragmentCharacterDetailBinding.inflate(layoutInflater)
    }
}
