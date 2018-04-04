package com.andrijans.marveltest.presentation.details

import com.andrijans.marveltest.framework.api.entity.Character

/**
 * Created by andrijanstankovic on 04/04/2018.
 */
class DetailsPresenterImpl(val view: DetailsContract.View) : DetailsContract.Presenter {
    lateinit var character: Character

    override fun openInWebButtonClicked() {
        if (character.urls.isNotEmpty()){
            view.navigateToWebPage(character.urls[0].url)
        }
    }

    override fun navigationBackButtonClicked() {
        view.closeScreen()
    }

    override fun onCreate(character: Character) {
        this.character = character
        view.setCharacterName(character.name)
        view.setCharacterDescription(character.description)
        view.setCharacterImage(character.thumbnail.path + "." + character.thumbnail.extension)
    }

}