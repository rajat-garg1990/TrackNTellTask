package com.application.trackntelltask.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class ViewPagerAdapter(private val context: Context, private val images:ArrayList<String>) : PagerAdapter() {
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        Picasso.get().load(images[position]).fit().into(imageView)
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}