package dev.claucookielabs.pasbuk.passlist.presentation.ui.renderers

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import dev.claucookielabs.pasbuk.R
import dev.claucookielabs.pasbuk.common.domain.model.Passbook
import dev.claucookielabs.pasbuk.common.presentation.utils.loadImage
import dev.claucookielabs.pasbuk.common.presentation.utils.show

class CouponRowRenderer: PassbookRowRenderer() {

    override fun renderBackground(passbook: Passbook, backgroundView: CardView) {
        backgroundView.setCardBackgroundColor(
            ResourcesCompat.getColor(
                backgroundView.resources,
                R.color.colorAccent,
                backgroundView.context.theme
            )
        )
    }

    override fun renderTitle(passbook: Passbook, titleView: TextView) {
        titleView.text = passbook.logoText ?: ""
    }

    override fun renderImage(passbook: Passbook, imageView: ImageView) {
        if (passbook.hasNoLogos()) {
            imageView.show(false)
        } else {
            imageView.loadImage(passbook.logoImage ?: passbook.iconImage ?: "")
        }
    }

    override fun renderFields(passbook: Passbook, headersView: LinearLayout) {
        passbook.primaryFields.forEach { header ->
            val layoutParams = createHeaderLayoutParams(headersView.context)
            val headerText = createHeaderTextView(headersView.context, header.label, header.value)

            headersView.addView(
                headerText,
                layoutParams
            )
        }
    }
}
