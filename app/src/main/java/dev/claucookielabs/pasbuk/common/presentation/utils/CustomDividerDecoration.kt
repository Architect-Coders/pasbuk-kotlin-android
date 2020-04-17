package dev.claucookielabs.pasbuk.common.presentation.utils

import android.content.Context
import android.graphics.Canvas
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class CustomDividerDecoration(context: Context, resId: Int) : ItemDecoration() {
    private val divider = ContextCompat.getDrawable(context, resId)!!

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left: Int = parent.getPaddingLeft()
        val right: Int = parent.getWidth() - parent.getPaddingRight()
        val childCount: Int = parent.getChildCount()
        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val params =
                child.getLayoutParams() as RecyclerView.LayoutParams
            val top: Int = child.getBottom() + params.bottomMargin
            val bottom = top + divider.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
}
