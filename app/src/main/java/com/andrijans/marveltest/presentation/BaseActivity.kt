package com.andrijans.marveltest.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.andrijans.marveltest.domain.ILogger
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var logger: ILogger
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        injectView()
    }

//    protected abstract fun injectView()
}