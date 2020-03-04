package com.andrijans.marveltest.presentation.main


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrijans.marveltest.R
import com.andrijans.marveltest.domain.ILogger
import com.andrijans.marveltest.framework.api.entity.Character
import com.andrijans.marveltest.presentation.Navigator
import com.andrijans.marveltest.presentation.common.view.adapter.PagingRecyclerOnScrollListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter
    @Inject
    lateinit var logger: ILogger
    @Inject
    lateinit var navigator: Navigator

    private lateinit var adapter: MainAdapter

//    override fun injectView() {
//        App.appComponent.plus(MainModule(this)).inject(this)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getCharacters()
    }

    override fun loadCharacters(characters: MutableList<Character>) {

        val layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(characters, presenter)
        characterList.layoutManager = layoutManager
        characterList.adapter = adapter
        characterList.addOnScrollListener(object : PagingRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(offset: Int) {
                presenter.loadMoreCharacters(offset)
            }
        })
    }

    override fun appendCharacters(characters: MutableList<Character>) {
        adapter.appendData(characters)
    }

    override fun navigateToDetailsScreen(character: Character, shouldFinish: Boolean) {
        navigator.navigateToDetailsScreen(this, character, shouldFinish)
    }


}
