package com.andrijans.marveltest.presentation.main

import com.andrijans.marveltest.presentation.common.constants.Constants

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
class MainContract {
    interface View {
        fun setupView()
        fun navigateToCharacters()
        fun navigateToAssistance()
    }

    interface Presenter {
        fun onCreate()
        fun charactersItemClicked()
        fun assistanceItemClicked()
        fun pageSelected(screen: Constants.SCREEN)
    }
}