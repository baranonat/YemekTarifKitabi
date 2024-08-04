package com.baranonat.yemektarifkitabi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.baranonat.yemektarifkitabi.databinding.FragmentListeBinding


class ListeFragment : Fragment() {
    private var _binding: FragmentListeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton2.setOnClickListener { ekle(it) }
    }

fun ekle(view:View){
    val action= ListeFragmentDirections.actionListeFragmentToTarifFragment()
    Navigation.findNavController(view).navigate(action)

}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}