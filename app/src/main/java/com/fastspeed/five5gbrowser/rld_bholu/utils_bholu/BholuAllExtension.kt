package com.fastspeed.five5gbrowser.rld_bholu.utils_bholu

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor

fun Context.getMyDrawable(id : Int) : Drawable?{

    return  ContextCompat.getDrawable(this, id)
}
fun View.beInvisibleIf(beInvisible: Boolean) = if (beInvisible) beInvisiblewer() else beVisiblewer()

fun View.beVisibleIf(beVisible: Boolean) = if (beVisible) beVisiblewer() else beGone()

fun View.beGoneIf(beGone: Boolean) = beVisibleIf(!beGone)

fun View.beInvisiblewer() {
    visibility = View.INVISIBLE
}
fun View.beGone(){
    visibility = View.GONE
}
fun View.beVisiblewer(){
    visibility = View.VISIBLE
}
fun View.isVisible() = visibility == View.VISIBLE

fun View.isInvisible() = visibility == View.INVISIBLE

fun View.isGone() = visibility == View.GONE

fun Context.getColorMy(@ColorRes color: Int): Int{
    return ContextCompat.getColor(this, color)
}

fun TextView.setDrawableColor(@ColorRes color: Int) {
    compoundDrawables.filterNotNull().forEach {
        it.colorFilter = PorterDuffColorFilter(getColor(this.context, color), PorterDuff.Mode.SRC_IN)
    }
}

fun TextView.setTexttColor(@ColorRes color: Int) {
    setTextColor(ContextCompat.getColor(this.context, color))
}
fun TextView.setTexttColorN( color: Int) {
    setTextColor(color)
}
fun TextView.setDrawableColorN( color: Int) {
    compoundDrawables.filterNotNull().forEach {
        it.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
    }
}


fun ImageView.loadImagesWithGlideExtDraw(drawable: Int) {
    setImageResource(drawable)
}

fun Int.dp(): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()
}

fun Context.themeColornew(@AttrRes attrRes: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute (attrRes, typedValue, true)
    return typedValue.data
}

inline fun <reified T : Activity> Context.Navigate(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply(block))
}


fun Activity.mtoast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message, duration).show()
}