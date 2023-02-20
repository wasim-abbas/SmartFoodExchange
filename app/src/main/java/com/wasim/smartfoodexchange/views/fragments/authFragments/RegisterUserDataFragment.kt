package com.wasim.smartfoodexchange.views.fragments.authFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseFragments
import com.wasim.smartfoodexchange.databinding.FragmentRegisterUserDataBinding
import com.wasim.smartfoodexchange.utils.singleClick
import com.wasim.smartfoodexchange.viewModels.BaseViewModel


class RegisterUserDataFragment :BaseFragments<BaseViewModel>() {
    //override val layoutId: Int
      //  get() =R.layout.fragment_register_user_data
    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java
    private lateinit var binding: FragmentRegisterUserDataBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterUserDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeLiveData() {
    }

    override fun init() {

       binding.btnNext.singleClick {
            currentActivity().replaceFragmentInAuth(R.id.action_registerUserDataFragment_to_registerUserInfoFragment)
        }

       binding.btnBack.singleClick {
           currentActivity().onBackPressedDispatcher.onBackPressed()

       }
    }



}