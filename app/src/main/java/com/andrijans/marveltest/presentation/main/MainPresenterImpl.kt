package com.andrijans.marveltest.presentation.main

import com.andrijans.marveltest.domain.ILogger
import com.andrijans.marveltest.framework.api.entity.Character
import com.andrijans.marveltest.framework.api.entity.DataContainer
import com.andrijans.marveltest.framework.api.interactor.Listener
import javax.inject.Inject

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
class MainPresenterImpl @Inject constructor(val view:MainContract.View,val logger:ILogger,val interactor:MainContract.Interactor):MainContract.Presenter {

    override fun loadMoreCharacters(offset: Int) {
       interactor.getCharacters(offset,object :Listener<DataContainer>(){
           override fun onNext(value: DataContainer) {
               super.onNext(value)
               view.appendCharacters(value.results)
           }

           override fun onError(e: Throwable?) {
               logger.e(e)
           }
       })
    }

    override fun getCharacters() {
       interactor.getCharacters(0,object :Listener<DataContainer>(){
           override fun onNext(value: DataContainer) {
               super.onNext(value)
               view.loadCharacters(value.results)
           }

           override fun onError(e: Throwable?) {
               logger.e(e)
           }
       })


    }

    override fun characterClicked(character: Character) {
        view.navigateToDetailsScreen(character,false)
    }

}