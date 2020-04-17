package dev.claucookielabs.pasbuk.passlist.presentation.ui.renderers

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.scale
import dev.claucookielabs.pasbuk.R
import dev.claucookielabs.pasbuk.common.domain.model.InfoField
import dev.claucookielabs.pasbuk.common.domain.model.Passbook

abstract class PassbookRowRenderer {

    abstract fun renderBackground(passbook: Passbook, backgroundView: CardView)
    abstract fun renderTitle(passbook: Passbook, titleView: TextView)
    abstract fun renderImage(passbook: Passbook, imageView: ImageView)
    abstract fun renderFields(passbook: Passbook, headersView: LinearLayout)

    protected fun createHeaderLayoutParams(context: Context): ViewGroup.LayoutParams {
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.marginStart =
            context.resources.getDimensionPixelSize(R.dimen.regular_spacing)
        return layoutParams
    }

    protected fun createHeaderTextView(context: Context, label: String, value: String): TextView {
        val headerText = TextView(
            context,
            null,
            0,
            R.style.Body1
        )
        headerText.gravity = Gravity.END
        headerText.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
        headerText.typeface = ResourcesCompat.getFont(
            context,
            R.font.product_sans
        )
        headerText.text = buildSpannedString {
            bold { appendln(label) }
            scale(SCALE_FACTOR) {
                append(value)
            }
        }
        return headerText
    }
}


private const val SCALE_FACTOR = 1.3f
