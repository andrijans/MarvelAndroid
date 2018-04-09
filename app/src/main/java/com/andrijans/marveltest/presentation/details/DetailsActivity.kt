package com.andrijans.marveltest.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.andrijans.marveltest.R
import com.andrijans.marveltest.domain.ILogger
import com.andrijans.marveltest.framework.api.entity.Character
import com.andrijans.marveltest.presentation.Navigator
import com.bumptech.glide.Glide
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : DaggerAppCompatActivity(), DetailsContract.View {

    @Inject
    lateinit var logger: ILogger
    @Inject
    lateinit var navigator: Navigator

    companion object {
        val CHARACTER_KEY = "CHARACTER_KEY"

        fun getCallingIntent(context: Context, character: Character): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(CHARACTER_KEY, character)
            return intent
        }
    }

    @Inject
    lateinit var presenter: DetailsContract.Presenter

//    override fun injectView() {
//        App.appComponent.plus(DetailsModule(this)).inject(this)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initToolbar()
        presenter.onCreate(intent.getParcelableExtra(CHARACTER_KEY))
        openInWebButton.setOnClickListener({ presenter.openInWebButtonClicked() })
    }

    override fun setCharacterImage(url: String) {
        Glide.with(this).load(url).into(characterImage)
    }

    override fun setCharacterName(name: String) {
        if (name.isBlank()) {
            collapsingToolbar.title = getString(R.string.no_description)
        } else {
            collapsingToolbar.title = name
        }
    }

    override fun setCharacterDescription(descriptionString: String) {
        description.text = descriptionString
    }

    override fun navigateToWebPage(url: String) {
        navigator.openWebAddress(this, url)
    }

    override fun closeScreen() {
        finish()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener({ presenter.navigationBackButtonClicked() })
    }

}
