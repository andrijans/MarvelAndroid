package com.andrijans.marveltest.presentation.characters

import com.andrijans.marveltest.framework.api.entity.Character
import com.andrijans.marveltest.framework.api.entity.DataContainer
import com.andrijans.marveltest.framework.api.interactor.Listener

class CharactersContract {
    interface View{
        fun loadCharacters(characters:MutableList<Character>)
        fun appendCharacters(characters: MutableList<Character>)
        fun navigateToDetailsScreen(character: Character, shouldFinish:Boolean)
    }
    interface Presenter{
        fun onViewCreated()
        fun loadMoreCharacters(offset:Int)
        fun characterClicked(character: Character)
    }
    interface Interactor{
        fun getCharacters(offset:Int, listener: Listener<DataContainer>)
    }
}