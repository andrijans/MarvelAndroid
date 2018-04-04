package com.andrijans.marveltest.presentation.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrijans.marveltest.R
import com.andrijans.marveltest.framework.api.entity.Character
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_character.view.*

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
class MainAdapter(val data: MutableList<Character>,val presenter:MainContract.Presenter) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindView(data[position])
        holder?.itemView?.rootLayout?.setOnClickListener({presenter.characterClicked(data[position])})
    }

    fun appendData(characters: MutableList<Character>) {
        val lastItemPos = data.size
        this.data.addAll(characters)
        notifyItemRangeChanged(lastItemPos, data.size)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(character: Character) {
            Glide.with(itemView.context).load(character.thumbnail.path+"."+character.thumbnail.extension).into(itemView.characterImage)
            itemView.characterName.text = character.name
        }
    }
}