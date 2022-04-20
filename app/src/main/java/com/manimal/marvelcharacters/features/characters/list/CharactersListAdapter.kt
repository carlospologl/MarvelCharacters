package com.manimal.marvelcharacters.features.characters.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manimal.domain.model.CharacterData
import com.manimal.marvelcharacters.databinding.ItemCharactersListBinding

class CharactersListAdapter(
    private val charactersList: List<CharacterData>,
    private val action: (id: Int?) -> Unit
) : RecyclerView.Adapter<CharactersListAdapter.CharactersListVIewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListVIewHolder {
        return CharactersListVIewHolder(
            ItemCharactersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharactersListVIewHolder, position: Int) {
        holder.bind(charactersList[position])
    }

    override fun getItemCount() = charactersList.size

    inner class CharactersListVIewHolder(
        private val binding: ItemCharactersListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterData) {
            binding.tvItemCharactersListName.text = character.name
            binding.tvItemCharactersListDescription.text = character.description

            Glide.with(binding.root.context)
                .load(character.thumbnail)
                .into(binding.ivItemCharactersListImage)

            binding.root.setOnClickListener {
                action.invoke(character.id)
            }
        }
    }
}
