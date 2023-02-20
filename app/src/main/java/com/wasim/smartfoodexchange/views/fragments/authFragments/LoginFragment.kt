package com.wasim.smartfoodexchange.views.fragments.authFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseFragments
import com.wasim.smartfoodexchange.databinding.FragmentLoginBinding
import com.wasim.smartfoodexchange.databinding.FragmentSplashBinding
import com.wasim.smartfoodexchange.utils.singleClick
import com.wasim.smartfoodexchange.viewModels.BaseViewModel
import com.wasim.smartfoodexchange.views.activities.MainActivity


class LoginFragment : BaseFragments<BaseViewModel>() {
    //    override val layoutId: Int
//        get() = R.layout.fragment_login
    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java
    lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun observeLiveData() {
    }

    override fun init() {

        binding.loginBtn.singleClick {
            startActivity(Intent(currentActivity(),MainActivity::class.java))
            currentActivity().finish()
        }
    }

}