package com.tcleard.wannacook.scene.edit

import android.support.v4.app.FragmentManager
import com.tcleard.wannacook.ui.widget.FixViewPager

class EditRecipeFragmentAdapter(
        supportFragmentManager: FragmentManager
) : FixViewPager.Adapter<AEditRecipeFragment<*>>(supportFragmentManager) {

    override fun onFragmentSelected(position: Int) {
        super.onFragmentSelected(position)
        for (i in 0 until count) {
            fragments[i].isSelected = i == position
        }
    }

}