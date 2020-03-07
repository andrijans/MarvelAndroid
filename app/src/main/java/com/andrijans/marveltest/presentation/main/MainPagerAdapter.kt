package com.andrijans.marveltest.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.andrijans.marveltest.presentation.assistance.AssistanceFragment
import com.andrijans.marveltest.presentation.characters.CharactersFragment
import com.andrijans.marveltest.presentation.common.constants.Constants

class MainPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {

        return when (Constants.SCREEN.values()[position]) {
            Constants.SCREEN.CHARACTERS -> CharactersFragment.newInstance()
            Constants.SCREEN.ASSISTANCE -> AssistanceFragment.newInstance()
        }
    }

    override fun getItemCount(): Int {
        return Constants.SCREEN.values().size
    }


}