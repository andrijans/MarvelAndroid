package com.andrijans.marveltest.presentation.common.di

import com.andrijans.marveltest.presentation.assistance.AssistanceFragment
import com.andrijans.marveltest.presentation.assistance.AssistanceModule
import com.andrijans.marveltest.presentation.characters.CharactersFragment
import com.andrijans.marveltest.presentation.characters.CharactersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [CharactersModule::class])
    abstract fun bindCharactersFragment(): CharactersFragment

    @ContributesAndroidInjector(modules = [AssistanceModule::class])
    abstract fun bindAssistanceFragment():AssistanceFragment
}