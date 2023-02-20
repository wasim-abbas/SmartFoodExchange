package com.wasim.smartfoodexchange.views.fragments.authFragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseFragments
import com.wasim.smartfoodexchange.databinding.FragmentSplashBinding
import com.wasim.smartfoodexchange.utils.singleClick
import com.wasim.smartfoodexchange.viewModels.BaseViewModel


class SplashFragment : BaseFragments<BaseViewModel>() {
//    override val layoutId: Int
//        get() = R.layout.fragment_splash
    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeLiveData() {
    }

    override fun init() {

       binding.loginBtn.singleClick {
            currentActivity().replaceFragmentInAuth(R.id.action_splashFragment_to_loginFragment)
        }
        binding.registerBtn.singleClick {
            currentActivity().replaceFragmentInAuth(R.id.action_splashFragment_to_registerUserDataFragment)
        }
    }

}