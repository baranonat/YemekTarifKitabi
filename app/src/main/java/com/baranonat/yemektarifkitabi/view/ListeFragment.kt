package com.baranonat.yemektarifkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.room.Room
import com.baranonat.yemektarifkitabi.databinding.FragmentListeBinding
import com.baranonat.yemektarifkitabi.roomdb.TarifDao
import com.baranonat.yemektarifkitabi.roomdb.TarifDatabase


class ListeFragment : Fragment() {
    private var _binding: FragmentListeBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: TarifDatabase
    private lateinit var tarifDao: TarifDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db= Room.databaseBuilder(requireContext(),TarifDatabase::class.java,"Tarifler").build()
        tarifDao=db.tarifDao()
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
    val action= ListeFragmentDirections.actionListeFragmentToTarifFragment("Yeni",0)
    Navigation.findNavController(view).navigate(action)

}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}