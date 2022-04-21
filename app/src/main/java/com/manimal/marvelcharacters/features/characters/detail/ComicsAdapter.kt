package com.manimal.marvelcharacters.features.characters.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manimal.domain.model.CharacterData
import com.manimal.marvelcharacters.databinding.ItemCharacterDetailComicsBinding
import com.manimal.marvelcharacters.databinding.ItemCharactersListBinding

class ComicsAdapter(
    private val comics: List<String>
) : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        return ComicsViewHolder(
            ItemCharacterDetailComicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(comics[position])
    }

    override fun getItemCount() = comics.size

    inner class ComicsViewHolder(
        private val binding: ItemCharacterDetailComicsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comicName: String) {
            binding.tvItemCharacterDetailComicsName.text = comicName
        }
    }
}
