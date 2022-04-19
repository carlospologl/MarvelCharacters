package com.manimal.marvelcharacters.features.characters.detail

import com.manimal.marvelcharacters.databinding.FragmentCharactersDetailBinding
import com.manimal.marvelcharacters.features.base.BaseFragment
import com.manimal.marvelcharacters.features.characters.CharactersActivity

class CharactersDetailFragment : BaseFragment<FragmentCharactersDetailBinding, CharactersActivity>() {

    override fun getBindingClass(): FragmentCharactersDetailBinding {
        return FragmentCharactersDetailBinding.inflate(layoutInflater)
    }
}
