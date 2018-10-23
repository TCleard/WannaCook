package com.tcleard.wannacook.ui.widget

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import com.tcleard.wannacook.ui.controller.AFragment

class FixViewPager : ViewPager {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(position: Int) {
                (adapter as? Adapter<*>)?.onFragmentSelected(position)
            }

        })
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    fun goToNext() {
        if (currentItem < adapter?.count ?: 0) {
            currentItem += 1
        }
    }

    fun goToPrevious() {
        if (currentItem > 0) {
            currentItem -= 1
        }
    }

    abstract class Adapter<F : AFragment<*>>(
            fm: FragmentManager
    ) : FragmentStatePagerAdapter(fm) {

        var onFragmentSelected: ((F) -> Unit)? = null

        protected val fragments = arrayListOf<F>()

        fun setFragments(fragments: List<F>) {
            this.fragments.clear()
            this.fragments.addAll(fragments)
            notifyDataSetChanged()
            onFragmentSelected(0)
        }

        fun getFragment(position: Int): F = fragments[position]

        override fun getItem(position: Int): Fragment = getFragment(position)

        override fun getCount(): Int = fragments.size

        open fun onFragmentSelected(position: Int) {
            onFragmentSelected?.invoke(fragments[position])
        }

    }

}