package com.andrijans.marveltest.presentation.details

import com.andrijans.marveltest.framework.api.entity.Character

/**
 * Created by andrijanstankovic on 04/04/2018.
 */
class DetailsPresenterImpl(val view: DetailsContract.View) : DetailsContract.Presenter {
    var character: Character? = null

    override fun openInWebButtonClicked() {
        character?.also {
            if (!it.urls.isNullOrEmpty()) {
                view.navigateToWebPage(it.urls[0].url)
            }
        }
    }

    override fun navigationBackButtonClicked() {
        view.closeScreen()
    }

    override fun onCreate(character: Character?) {
        this.character = character
        character?.let {
            view.setCharacterName(it.name)
            view.setCharacterDescription(it.description)
            view.setCharacterImage(it.thumbnail.path + "." + it.thumbnail.extension)
            view.setNumberOfComics(it.comics.available)
            view.setNumberOfSeries(it.series.available)
            view.setNumberOfStories(it.stories.available)
        }

    }

}