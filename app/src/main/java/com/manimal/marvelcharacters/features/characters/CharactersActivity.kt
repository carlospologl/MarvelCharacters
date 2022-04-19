package com.manimal.marvelcharacters.features.characters

import com.manimal.marvelcharacters.databinding.ActivityCharactersBinding
import com.manimal.marvelcharacters.features.base.BaseActivity

class CharactersActivity : BaseActivity<ActivityCharactersBinding>() {

    override fun getBindingClass(): ActivityCharactersBinding {
        return ActivityCharactersBinding.inflate(layoutInflater)
    }
}