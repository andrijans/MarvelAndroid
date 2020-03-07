package com.andrijans.marveltest.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.andrijans.marveltest.R
import com.andrijans.marveltest.domain.ILogger
import com.andrijans.marveltest.framework.api.entity.Character
import com.andrijans.marveltest.presentation.Navigator
import com.andrijans.marveltest.presentation.common.view.adapter.PagingRecyclerOnScrollListener
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject

class CharactersFragment : DaggerFragment(), CharactersContract.View {

    @Inject
    lateinit var presenter: CharactersContract.Presenter
    @Inject
    lateinit var logger: ILogger
    @Inject
    lateinit var navigator: Navigator

    private var adapter: CharactersAdapter? = null

    companion object {
        fun newInstance(): CharactersFragment = CharactersFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun loadCharacters(characters: MutableList<Character>) {
        val layoutManager = GridLayoutManager(activity, 2)
        adapter = CharactersAdapter(characters, presenter)
        characterList.layoutManager = layoutManager
        characterList.adapter = adapter
        characterList.addOnScrollListener(object : PagingRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(offset: Int) {
                presenter.loadMoreCharacters(offset)
            }
        })
    }

    override fun appendCharacters(characters: MutableList<Character>) {
        adapter?.appendData(characters)
    }

    override fun navigateToDetailsScreen(character: Character, shouldFinish: Boolean) {
        activity?.also { navigator.navigateToDetailsScreen(it, character, shouldFinish) }
    }

}