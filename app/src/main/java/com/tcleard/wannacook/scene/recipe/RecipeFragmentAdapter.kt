package com.tcleard.wannacook.scene.recipe

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class RecipeFragmentAdapter(
        supportFragmentManager: FragmentManager
) : FragmentStatePagerAdapter(supportFragmentManager) {

    private val fragments = arrayListOf<ARecipeFragment<*>>()

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    fun setFragments(fragments: List<ARecipeFragment<*>>) {
        this.fragments.clear()
        this.fragments.addAll(fragments)
        notifyDataSetChanged()
    }

}