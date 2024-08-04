package com.baranonat.yemektarifkitabi

import android.Manifest
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.baranonat.yemektarifkitabi.databinding.FragmentTarifBinding
import com.google.android.material.snackbar.Snackbar


class TarifFragment : Fragment() {
    private var _binding: FragmentTarifBinding? = null
    private val binding get() = _binding!!
    private lateinit var permissionLauncher:ActivityResultLauncher<String>
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var secilenGorsel: Uri?=null
    private var secilenBitmap: Bitmap?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            registerLauncher()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTarifBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.setOnClickListener { gorselSec(it) }
        binding.kaydetButton.setOnClickListener { kaydet((it)) }
        binding.silButton.setOnClickListener { sil(it) }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun gorselSec(view: View){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){

            if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_MEDIA_IMAGES)!=PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.READ_MEDIA_IMAGES)){
                    Snackbar.make(view,"Galeriye ulaşmamız için izin vermen gerekiyor",Snackbar.LENGTH_INDEFINITE).setAction(
                        "İzin ver",
                        View.OnClickListener {
                            //izin iste
                            permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                        }
                    ).show()
                }else{
                    //izin iste
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }
            }else{
                //galeriye git
                val intentToGalery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGalery)
            }


        }else{

            if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)){
                    Snackbar.make(view,"Galeriye ulaşmamız için izin vermen gerekiyor",Snackbar.LENGTH_INDEFINITE).setAction(
                        "İzin ver",
                        View.OnClickListener {
                            //izin iste
                            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        }
                    ).show()
                }else{
                    //izin iste
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }else{
                //galeriye git
                val intentToGalery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGalery)
            }

        }

    }

   private  fun registerLauncher(){

        activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            if(result.resultCode==AppCompatActivity.RESULT_OK){
                val intent =result.data
                if(intent!=null){
                    secilenGorsel= intent.data
                    try{
                        if(Build.VERSION.SDK_INT>=28){
                            val source=ImageDecoder.createSource(requireActivity().contentResolver,secilenGorsel!!)
                            secilenBitmap=ImageDecoder.decodeBitmap(source)
                            binding.imageView.setImageBitmap(secilenBitmap)
                        }
                        else{
                            secilenBitmap=MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,secilenGorsel)
                            binding.imageView.setImageBitmap(secilenBitmap)
                        }

                    }catch (e:Exception){
                       println(e.localizedMessage)
                    }

                }
            }
        }


        permissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()){result->
            if(result){
                val intentToGalery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGalery)


            }else{
                Toast.makeText(requireContext(),"İzin verilmedi",Toast.LENGTH_LONG).show()
            }
        }
    }
    fun kaydet(view:View){


    }
    fun sil(view:View){


    }
}