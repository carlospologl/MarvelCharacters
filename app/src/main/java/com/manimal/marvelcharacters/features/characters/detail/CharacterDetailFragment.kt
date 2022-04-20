package com.manimal.marvelcharacters.features.characters.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.manimal.marvelcharacters.databinding.FragmentCharacterDetailBinding
import com.manimal.marvelcharacters.features.base.BaseFragment
import com.manimal.marvelcharacters.features.base.BaseStatus
import com.manimal.marvelcharacters.features.characters.CharactersActivity
import com.manimal.marvelcharacters.features.characters.CharactersViewModel
import com.manimal.marvelcharacters.features.characters.list.CharactersListDataContainer
import com.manimal.marvelcharacters.utils.DateUtils
import com.manimal.marvelcharacters.utils.setGone
import com.manimal.marvelcharacters.utils.setVisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

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
            BaseStatus.LOADING -> { binding.pbCharacterDetailLoading.setVisible() }
            BaseStatus.SUCCESS -> {
                val results = it.data?.results
                binding.pbCharacterDetailLoading.setGone()
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
                binding.pbCharacterDetailLoading.setGone()
            }
            BaseStatus.FAILED -> {
                binding.pbCharacterDetailLoading.setGone()
            }
        }
    }
    //endregion

    override fun getBindingClass(): FragmentCharacterDetailBinding {
        return FragmentCharacterDetailBinding.inflate(layoutInflater)
    }
}
