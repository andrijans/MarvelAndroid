package com.andrijans.marveltest.presentation.main


import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.andrijans.marveltest.R
import com.andrijans.marveltest.presentation.BaseActivity
import com.andrijans.marveltest.presentation.common.constants.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private var mainAdapter: MainPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onCreate()
        initToolbar()

    }

    override fun setupView() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.characters -> presenter.charactersItemClicked()
                R.id.assistance -> presenter.assistanceItemClicked()
            }
            true
        }
        mainAdapter = MainPagerAdapter(this)
        mainPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        mainPager.adapter = mainAdapter
        mainPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                presenter.pageSelected(Constants.SCREEN.values()[position])
            }
        })
        mainPager.isUserInputEnabled = false
    }

    override fun navigateToCharacters() {
        mainPager.currentItem = Constants.SCREEN.CHARACTERS.ordinal
        bottomNavigationView.menu.findItem(R.id.characters).isChecked = true
        toolbarTitle.text = getString(R.string.charactersTitle)
    }

    override fun navigateToAssistance() {
        mainPager.currentItem = Constants.SCREEN.ASSISTANCE.ordinal
        bottomNavigationView.menu.findItem(R.id.assistance).isChecked = true
        toolbarTitle.text = getString(R.string.assistance)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar?.also {
            toolbarTitle.text = getString(R.string.charactersTitle)
        }
    }

}
