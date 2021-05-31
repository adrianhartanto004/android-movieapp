package com.adrian.abstraction.extension

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.hideKeyboardWhenScreenClicked(ev: MotionEvent) {
    val v = currentFocus
    if (v != null && (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) && v is EditText &&
        !v.javaClass.name.startsWith("android.webkit.")
    ) {
        val sourceCoordinates = IntArray(2)
        v.getLocationOnScreen(sourceCoordinates)
        val x: Float = ev.rawX + v.left - sourceCoordinates[0]
        val y: Float = ev.rawY + v.top - sourceCoordinates[1]
        if (x < v.left || x > v.right || y < v.top || y > v.bottom) {
            hideKeyboard(this)
        }
    }
}

private fun hideKeyboard(activity: Activity) {
    if (activity.window != null) {
        activity.window.decorView
        val imm: InputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
    }
}

fun Context.showLongToast(message: String, context: Context = applicationContext) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Context.showShortToast(message: String, context: Context = applicationContext) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showLongToast(
    message: String,
    context: Context = requireContext().applicationContext
) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showShortToast(
    message: String,
    context: Context = requireContext().applicationContext
) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}