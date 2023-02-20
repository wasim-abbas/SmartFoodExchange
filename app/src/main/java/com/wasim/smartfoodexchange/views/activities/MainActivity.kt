package com.wasim.smartfoodexchange.views.activities

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.wasim.smartfoodexchange.R
import com.wasim.smartfoodexchange.base.BaseActivity
import com.wasim.smartfoodexchange.databinding.ActivityMainBinding
import com.wasim.smartfoodexchange.utils.gone
import com.wasim.smartfoodexchange.utils.singleClick
import com.wasim.smartfoodexchange.utils.visible

/**
 * @author Wasim Abbas
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun attachViewMode() {
    }
}