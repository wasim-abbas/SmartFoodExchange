package com.wasim.smartfoodexchange.views.fragments.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseFragments
import com.wasim.smartfoodexchange.databinding.FragmentFoddDeatailBinding
import com.wasim.smartfoodexchange.databinding.FragmentMealPlanBinding
import com.wasim.smartfoodexchange.viewModels.BaseViewModel


class MealPlanFragment : BaseFragments<BaseViewModel>() {
    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java
    private lateinit var binding: FragmentMealPlanBinding

    override fun observeLiveData() {
    }

    override fun init() {
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealPlanBinding.inflate(inflater, container, false)
        return binding.root
    }


}