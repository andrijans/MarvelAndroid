package com.andrijans.marveltest.presentation.characters

import com.andrijans.marveltest.domain.ILogger
import com.andrijans.marveltest.framework.api.entity.Character
import com.andrijans.marveltest.framework.api.entity.DataContainer
import com.andrijans.marveltest.framework.api.interactor.Listener
import com.andrijans.marveltest.presentation.common.util.NetworkUtil

class CharactersPresenterImpl(val view: CharactersContract.View, val logger: ILogger, val interactor: CharactersContract.Interactor, val networkUtil: NetworkUtil) : CharactersContract.Presenter {
    override fun loadMoreCharacters(offset: Int) {
        interactor.getCharacters(offset, object : Listener<DataContainer>() {
            override fun onNext(value: DataContainer) {
                super.onNext(value)
                view.appendCharacters(value.results)
            }

            override fun onError(e: Throwable) {
                logger.e(e)
            }
        })
    }

    override fun onViewCreated() {
        interactor.getCharacters(0, object : Listener<DataContainer>() {
            override fun onNext(value: DataContainer) {
                super.onNext(value)
                view.showDataScreen()
                view.loadCharacters(value.results)
            }

            override fun onError(e: Throwable) {
                logger.e(e)
                if (networkUtil.isNetworkAvailable()) {
                    view.showErrorScreen()
                } else {
                    view.showOfflineScreen()
                }
            }
        })


    }

    override fun characterClicked(character: Character) {
        view.navigateToDetailsScreen(character, false)
    }

    override fun retryBtnClicked() {
        onViewCreated()
    }

}