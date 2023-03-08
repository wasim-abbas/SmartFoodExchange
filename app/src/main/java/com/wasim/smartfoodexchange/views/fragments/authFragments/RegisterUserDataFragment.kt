package com.wasim.smartfoodexchange.views.fragments.authFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseFragments
import com.wasim.smartfoodexchange.databinding.FragmentRegisterUserDataBinding
import com.wasim.smartfoodexchange.utils.*
import com.wasim.smartfoodexchange.viewModels.BaseViewModel
import java.util.jar.Attributes.Name


class RegisterUserDataFragment : BaseFragments<BaseViewModel>() {
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

            if (isValidate() as Boolean) {
                if (binding.edPassword.text.toString() != binding.edConfirmPass.text.toString()) {
                    showToast("Password does not match")
                } else {

                    val bundle = Bundle()
                    bundle.putString(NAME,  binding.edName.getString())
                    bundle.putString(EMAIL, binding.edEmail.getString())
                    bundle.putString(PASSWORD, binding.edPassword.getString())
                    bundle.putString(CONFIRMPASSWORD, binding.edConfirmPass.getString())
                    currentActivity().replaceFragmentInAuth(R.id.action_registerUserDataFragment_to_registerUserInfoFragment,bundle)
                }

            }
        }

        binding.btnBack.singleClick {
            currentActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun isValidate() = when {
        binding.edName.isEmpty() -> {
            binding.edName.error = getString(R.string.fill_your_data)
            false
        }
        binding.edEmail.isEmpty() -> {
            binding.edEmail.error = getString(R.string.fill_your_data)
            false
        }
        binding.edPassword.isEmpty() -> {
            binding.edPassword.error = getString(R.string.fill_your_data)
            false
        }
        binding.edConfirmPass.isEmpty() -> {
            binding.edConfirmPass.error = getString(R.string.fill_your_data)
        }
        else -> true

    }

}