package com.andrijans.marveltest.presentation.details

import com.andrijans.marveltest.framework.api.entity.Character

/**
 * Created by andrijanstankovic on 04/04/2018.
 */
class DetailsContract {
    interface View {
        fun setCharacterImage(url: String)
        fun setCharacterName(name:String)
        fun setCharacterDescription(description:String)
        fun setNumberOfComics(number:Int)
        fun setNumberOfSeries(number:Int)
        fun setNumberOfStories(number:Int)
        fun navigateToWebPage(url: String)
        fun closeScreen()
    }

    interface Presenter {
        fun onCreate(character: Character?)
        fun navigationBackButtonClicked()
        fun openInWebButtonClicked()
    }
}