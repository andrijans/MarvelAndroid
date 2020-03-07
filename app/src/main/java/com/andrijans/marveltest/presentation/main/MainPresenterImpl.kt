package com.andrijans.marveltest.presentation.main

import com.andrijans.marveltest.domain.ILogger
import com.andrijans.marveltest.framework.api.entity.Character
import com.andrijans.marveltest.framework.api.entity.DataContainer
import com.andrijans.marveltest.framework.api.interactor.Listener
import com.andrijans.marveltest.presentation.common.constants.Constants
import javax.inject.Inject

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
class MainPresenterImpl @Inject constructor(val view:MainContract.View,val logger:ILogger):MainContract.Presenter {
    override fun onCreate() {
        view.setupView()
    }

    override fun charactersItemClicked() {
       view.navigateToCharacters()
    }

    override fun assistanceItemClicked() {
        view.navigateToAssistance()
    }

    override fun pageSelected(screen: Constants.SCREEN) {
        when(screen){
            Constants.SCREEN.CHARACTERS -> view.navigateToCharacters()
            Constants.SCREEN.ASSISTANCE -> view.navigateToAssistance()
        }
    }


}