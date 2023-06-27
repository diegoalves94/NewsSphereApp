package com.djr.newssphere.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.djr.newssphere.data.model.Headline
import com.djr.newssphere.databinding.FragmentHeadlineDetailBinding

class HeadlineDetailFragment : Fragment() {

    private lateinit var binding: FragmentHeadlineDetailBinding
    private val args: HeadlineDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeadlineDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val headline = args.headline
        displayHeadlineDetails(headline)
    }

    private fun displayHeadlineDetails(headline: Headline) {
        if (headline.imageUrl != null) {
            Glide.with(requireContext())
                .load(headline.imageUrl)
                .into(binding.headlineImageView)
        } else {
            binding.headlineImageView.visibility = View.GONE
        }
        binding.titleTextView.text = headline.title
        binding.descriptionTextView.text = headline.description
        binding.contentTextView.text = headline.content
    }

}

