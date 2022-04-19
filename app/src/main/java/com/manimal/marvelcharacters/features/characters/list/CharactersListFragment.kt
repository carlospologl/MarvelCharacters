package com.manimal.marvelcharacters.features.characters.list

import com.manimal.marvelcharacters.databinding.FragmentCharactersListBinding
import com.manimal.marvelcharacters.features.base.BaseFragment
import com.manimal.marvelcharacters.features.characters.CharactersActivity

class CharactersListFragment : BaseFragment<FragmentCharactersListBinding, CharactersActivity>() {

    override fun getBindingClass(): FragmentCharactersListBinding {
        return FragmentCharactersListBinding.inflate(layoutInflater)
    }
}
