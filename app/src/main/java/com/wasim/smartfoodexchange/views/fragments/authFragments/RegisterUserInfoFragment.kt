package com.wasim.smartfoodexchange.views.fragments.authFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseFragments
import com.wasim.smartfoodexchange.databinding.FragmentRegisterUserInfoBinding
import com.wasim.smartfoodexchange.databinding.FragmentSplashBinding
import com.wasim.smartfoodexchange.utils.singleClick
import com.wasim.smartfoodexchange.viewModels.BaseViewModel


class RegisterUserInfoFragment : BaseFragments<BaseViewModel>() {
//    override val layoutId: Int
//        get() = R.layout.fragment_register_user_info
    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java

    lateinit var binding: FragmentRegisterUserInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun observeLiveData() {
    }

    override fun init() {
        binding.btnRegister.singleClick {
            currentActivity().replaceFragmentInAuth(R.id.action_registerUserInfoFragment_to_splashFragment)
        }
        binding.btnBack.singleClick {
            currentActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

}