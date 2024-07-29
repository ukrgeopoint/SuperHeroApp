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

class ListFragment : Fragment() {

    private var onItemClick: (HeroItem) -> Unit = {}

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

        ApiClient.client.create(ApiInterface::class.java)
            .getHeroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val adapter = RecyclerViewAdapter(it, onClick = { items: HeroItem ->
                    onItemClick(items)
                })
                listView.adapter = adapter
            }, {
                Toast.makeText(view.context, "Request error", Toast.LENGTH_SHORT).show()
            })

        listView.layoutManager = LinearLayoutManager(view.context)
        listView.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
    }

    fun setOnListFragmentItemClickListener(onItemClick: (HeroItem) -> Unit) {
        this.onItemClick = onItemClick
    }
}