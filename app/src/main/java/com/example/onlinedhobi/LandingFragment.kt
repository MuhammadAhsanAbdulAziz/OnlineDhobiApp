package com.example.onlinedhobi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.onlinedhobi.adapter.ViewPagerAdapter
import com.example.onlinedhobi.databinding.FragmentLandingBinding
import com.example.onlinedhobi.util.UtilManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LandingFragment : Fragment() {

    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding!!
    private var titlelist = mutableListOf<String>()
    private var deslist = mutableListOf<String>()
    private var imglist = mutableListOf<Int>()
    @Inject
    lateinit var utilManager: UtilManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        if(utilManager.getLanding() == "Yes"){
            findNavController().navigate(R.id.action_landingFragment_to_loginFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postToList()

        binding.viewpager.adapter = ViewPagerAdapter(titlelist,deslist,imglist)
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.indicator.setViewPager(binding.viewpager)

        binding.landingbtn.setOnClickListener {
            findNavController().navigate(R.id.action_landingFragment_to_loginFragment)
            utilManager.saveLanding()
        }
    }


    private fun addtolist(titel:String,des:String,img:Int){
        titlelist.add(titel)
        deslist.add(des)
        imglist.add(img)
    }

    private fun postToList(){
        addtolist("Laundrey include Washing","Laundry storage tips to keep your family safe " +
                "from any detergent-related accidents",R.drawable.landingimg1)
        addtolist("it's not stuff for your washer","Too little can leave your clothing dirty and too" +
                "much can leave a residue",R.drawable.landingimg2)
        addtolist("Keeping helps machine","There is not a whole bunch of prep for drying. In most" +
                "cases, you just throw everything from the washer into the dryer",R.drawable.landingimg3)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}