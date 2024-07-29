package com.example.superheroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.superheroes.data.HeroItem

class DetailsFragment : Fragment() {

    private lateinit var imageHero: HeroItem

    private var name: String = ""
    private var description: String = ""

    private var imageViewHero: ImageView? = null
    private var textViewName: TextView? = null
    private var textViewDescription: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageViewHero = view.findViewById(R.id.ivHero)
        textViewName = view.findViewById(R.id.tvName)
        textViewDescription = view.findViewById(R.id.tvDescription)

        // Glide
        imageViewHero?.let {
            Glide.with(this)
                .load(imageHero.images.lg)
                .into(it)
        }
        textViewName?.text = name
        textViewDescription?.text = description
    }

    fun setName(item: HeroItem) {
        imageHero = item
        name = item.name
        description = item.biography.publisher
    }

}