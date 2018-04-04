package com.andrijans.marveltest.presentation.main

import com.andrijans.marveltest.framework.api.entity.Character
import com.andrijans.marveltest.framework.api.entity.DataContainer
import com.andrijans.marveltest.framework.api.interactor.Listener

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
class MainContract {
    interface View{
        fun loadCharacters(characters:MutableList<Character>)
        fun appendCharacters(characters: MutableList<Character>)
        fun navigateToDetailsScreen(character: Character,shouldFinish:Boolean)
    }
    interface Presenter{
        fun getCharacters()
        fun loadMoreCharacters(offset:Int)
        fun characterClicked(character:Character)
    }
    interface Interactor{
        fun getCharacters(offset:Int, listener:Listener<DataContainer>)
    }
}