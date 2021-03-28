package com.ml.hostverifier

import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

object CustomMatchers {
    private fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: Description?) {
                description?.appendText("has item at position $position: ")
            }

            override fun matchesSafely(item: RecyclerView): Boolean {
                val viewHolder = item.findViewHolderForAdapterPosition(position)
                return itemMatcher.matches(viewHolder?.itemView)
            }
        }
    }

    fun atPositionOnView(
        position: Int,
        itemMatcher: Matcher<View>,
        targetViewId: Int
    ): Matcher<View> {

        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has view id $itemMatcher at position $position")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                val targetView = viewHolder!!.itemView.findViewById<View>(targetViewId)
                return itemMatcher.matches(targetView)
            }
        }
    }
}