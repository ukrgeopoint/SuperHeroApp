package com.example.superheroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.data.HeroItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private var onItemClick: (HeroItem) -> Unit = {}
    private val myScope = CoroutineScope(Dispatchers.Main)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: RecyclerView = view.findViewById(R.id.listView)

        myScope.launch {
            val hero = ApiClient.client.create(ApiInterface::class.java)
                .getHeroes()

            listView.adapter = RecyclerViewAdapter(hero, onClick = { items: HeroItem ->
                onItemClick(items)
            })
        }

        listView.layoutManager = LinearLayoutManager(view.context)
        listView.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
    }

    fun setOnListFragmentItemClickListener(onItemClick: (HeroItem) -> Unit) {
        this.onItemClick = onItemClick
    }
}