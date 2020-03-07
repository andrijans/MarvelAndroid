package com.andrijans.marveltest.presentation.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrijans.marveltest.R
import com.andrijans.marveltest.framework.api.entity.Character
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_character.view.*

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
class CharactersAdapter(val data: MutableList<Character>, val presenter: CharactersContract.Presenter) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(data[position])
        holder.itemView.rootLayout?.setOnClickListener { presenter.characterClicked(data[position]) }
    }

    fun appendData(characters: MutableList<Character>) {
        val lastItemPos = data.size
        this.data.addAll(characters)
        notifyItemRangeChanged(lastItemPos, data.size)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(character: Character) {
            Glide.with(itemView.context).setDefaultRequestOptions(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).load(character.thumbnail.path + "." + character.thumbnail.extension).into(itemView.characterImage)
            itemView.characterName.text = character.name
        }
    }
}