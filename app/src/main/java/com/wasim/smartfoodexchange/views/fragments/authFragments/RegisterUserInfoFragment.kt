package com.wasim.smartfoodexchange.views.fragments.authFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseFragments
import com.wasim.smartfoodexchange.databinding.FragmentRegisterUserInfoBinding
import com.wasim.smartfoodexchange.databinding.FragmentSplashBinding
import com.wasim.smartfoodexchange.models.RegisterModel
import com.wasim.smartfoodexchange.utils.*
import com.wasim.smartfoodexchange.utils.source.addUser
import com.wasim.smartfoodexchange.viewModels.BaseViewModel
import io.paperdb.Paper


class RegisterUserInfoFragment : BaseFragments<BaseViewModel>() {
    //    override val layoutId: Int
//        get() = R.layout.fragment_register_user_info
    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java

    lateinit var binding: FragmentRegisterUserInfoBinding
    private var gender = "null"

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

        val name = getStringArgument(NAME)
        val email = getStringArgument(EMAIL)
        val pass = getStringArgument(PASSWORD)
        val confirmPass = getStringArgument(CONFIRMPASSWORD)

        binding.btnRegister.singleClick {
            if (isValidate()) {
                if (binding.rgGender.checkedRadioButtonId == -1) {
                    showToast("Please Select Gender")
                } else {
                    val registerUSer = RegisterModel(
                        name!!,
                        email!!,
                        pass!!,
                        confirmPass!!,
                        binding.edHeight.getString(),
                        binding.edWeight.getString(),
                        binding.edAge.getString(),
                        gender
                    )
                    addUser(registerUSer)
                    currentActivity().replaceFragmentInAuth(R.id.action_registerUserInfoFragment_to_splashFragment)
                }
            }

        }
        binding.btnBack.singleClick {
            currentActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.rgGender.setOnCheckedChangeListener { group, checkID ->
            val rbID = group.checkedRadioButtonId
            val selectedBtn: View = group.findViewById(rbID)
            val position = group.indexOfChild(selectedBtn)

            if (position == 0) {
                gender = "Male"
            } else {
                gender = "Female"
            }
        }
    }

    private fun isValidate() = when {
        binding.edHeight.isEmpty() -> {
            binding.edHeight.error = getString(R.string.fill_your_data)
            false
        }
        binding.edWeight.isEmpty() -> {
            binding.edWeight.error = getString(R.string.fill_your_data)
            false
        }
        binding.edAge.isEmpty() -> {
            binding.edAge.error = getString(R.string.fill_your_data)
            false
        }
        else -> true

    }

}