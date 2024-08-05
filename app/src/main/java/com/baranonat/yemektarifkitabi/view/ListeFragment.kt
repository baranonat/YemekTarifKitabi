package com.baranonat.yemektarifkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.baranonat.yemektarifkitabi.adapter.TarifAdapter
import com.baranonat.yemektarifkitabi.databinding.FragmentListeBinding
import com.baranonat.yemektarifkitabi.model.Tarif
import com.baranonat.yemektarifkitabi.roomdb.TarifDao
import com.baranonat.yemektarifkitabi.roomdb.TarifDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class ListeFragment : Fragment() {
    private var _binding: FragmentListeBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: TarifDatabase
    private lateinit var tarifDao: TarifDao
    private var mdissposable=CompositeDisposable()
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
        binding.ListRecyclerView.layoutManager=LinearLayoutManager(requireContext())
       verileriAl()
    }

    fun verileriAl(){
        mdissposable.add(

            tarifDao.getall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)

        )
    }
    fun handleResponse(tarifListesi:List<Tarif>){
        val adapter=TarifAdapter(tarifListesi)
        binding.ListRecyclerView.adapter=adapter
    }

fun ekle(view:View){
    val action= ListeFragmentDirections.actionListeFragmentToTarifFragment("yeni",-1)
    Navigation.findNavController(view).navigate(action)

}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mdissposable.clear()
    }

}