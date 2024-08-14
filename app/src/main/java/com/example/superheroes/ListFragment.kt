package com.example.superheroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.data.HeroItem

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
        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        viewModel.getData()

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is MyViewModel.UIState.Empty -> Unit
                is MyViewModel.UIState.Result -> {
                    listView.adapter =
                        RecyclerViewAdapter(uiState.hero, onClick = { items: HeroItem ->
                            onItemClick(items)
                        })
                }
                is MyViewModel.UIState.Processing -> Unit
            }
        }
        viewModel.getData()

        listView.layoutManager = LinearLayoutManager(view.context)
        listView.addItemDecoration(
            DividerItemDecoration(
                view.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    fun setOnListFragmentItemClickListener(onItemClick: (HeroItem) -> Unit) {
        this.onItemClick = onItemClick
    }
}