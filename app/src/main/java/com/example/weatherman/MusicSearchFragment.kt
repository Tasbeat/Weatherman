package com.example.weatherman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherman.databinding.FragmentMusicSearchBinding
import com.example.weatherman.databinding.FragmentWeatherBinding


class MusicSearchFragment : Fragment() {
    private var _binding: FragmentMusicSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMusicSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }


}