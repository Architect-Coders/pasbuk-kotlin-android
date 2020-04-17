package dev.claucookielabs.pasbuk.common.presentation.utils

import android.graphics.Color
import java.util.regex.Matcher
import java.util.regex.Pattern

class RGBColorParser {

    fun parseAndGetDarkerColor(
        colorString: String?,
        colorFactor: Float,
        label: Boolean
    ): Int {
        var color = Color.parseColor(DEFAULT_BG_HEX_COLOR)
        if (colorString != null && colorString.isNotEmpty()) {
            // Added pattern to cover rgb(X,X,X) cases and rgba(X,X,X,X)
            val patternRgb =
                Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)")
            val patternRgba =
                Pattern.compile("rgba *\\( *([0-9]+), *([0-9]+), *([0-9]+), *([0-9]+) *\\)")
            val matcherRgb = patternRgb.matcher(colorString)
            val matcherRgba = patternRgba.matcher(colorString)
            var generalMatcher: Matcher? = null
            if (matcherRgb.matches()) {
                generalMatcher = matcherRgb
            } else if (matcherRgba.matches()) {
                generalMatcher = matcherRgba
            }
            if (generalMatcher != null) {
                val r = Integer.valueOf(generalMatcher.group(1) ?: "")
                val g = Integer.valueOf(generalMatcher.group(2) ?: "")
                val b = Integer.valueOf(generalMatcher.group(3) ?: "")
                val hsv = FloatArray(3)
                Color.RGBToHSV(r, g, b, hsv)
                if (!label) {
                    // If background is not black, then apply a little change
                    if (!(hsv[0] == 0f && hsv[1] == 0f && hsv[2] == 0f)) {
                        hsv[2] = hsv[2] + colorFactor
                    }
                } else {
                    // If background is too dark, then the label will not be seen
                    // make the label lighter if V value is under 35
                    if (hsv[2] < 0.4f && hsv[1] > 0.7f) {
                        hsv[1] = 0.4f // saturation
                        hsv[2] = 1.0f // value
                    } else {
                        hsv[2] = hsv[2] + colorFactor
                    }
                }
                color = Color.HSVToColor(hsv)
            } else {
                // Hex color?
                color = try {
                    Color.parseColor(colorString)
                } catch (e: IllegalArgumentException) {
                    Color.parseColor(DEFAULT_BG_HEX_COLOR)
                }
            }
        }
        return color
    }
}

private const val COLOR_BACKGROUND_DARKER_FACTOR = -0.61f
private const val COLOR_LABEL_DARKER_FACTOR = -0.4f
private const val COLOR_BACKGROUND_LIGHTER_FACTOR = 0.10f
private const val COLOR_EQUAL_FACTOR = 0
private const val BLACK_RGB_COLOR = "rgb(0,0,0)"
private const val DEFAULT_BG_HEX_COLOR = "#333333"
private const val DEFAULT_TEXT_RGB_COLOR = "rgb(255, 255, 255)"
