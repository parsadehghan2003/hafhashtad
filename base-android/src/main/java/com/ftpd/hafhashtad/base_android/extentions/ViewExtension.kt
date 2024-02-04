package com.ftpd.hafhashtad.base_android.extentions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.text.PrecomputedTextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.ftpd.hafhashtad.base_android.util.LayoutHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Field


fun TextView.setTextAsync(text: String) {
    val textView = this
    CoroutineScope(Dispatchers.Main).launch {
        val params = TextViewCompat.getTextMetricsParams(textView)
        val precomputedText = withContext(Dispatchers.Default) {
            PrecomputedTextCompat.create(text, params)
        }
        TextViewCompat.setPrecomputedText(textView, precomputedText)
    }
}

fun AppCompatTextView.setTextAsync(text: String) {
    val textView = this
    CoroutineScope(Dispatchers.Main).launch {
        val params = TextViewCompat.getTextMetricsParams(textView)
        val precomputedText = withContext(Dispatchers.Default) {
            PrecomputedTextCompat.create(text, params)
        }
        TextViewCompat.setPrecomputedText(textView, precomputedText)
    }
}

fun Fragment.textView(
    gravity: Int = Gravity.CENTER,
    id: Int = View.NO_ID,
    text: String? = null,
    textSize: Float = 0F,
    textSizeUnit: Int = TypedValue.COMPLEX_UNIT_DIP,
    lineSpacing: LineSpacing = LineSpacing(add = 0.0F, mult = 1.0F),
    maxLine: Int = Integer.MAX_VALUE,
    isSingleLine: Boolean = false,
    ellipsize: TextUtils.TruncateAt? = null,
    backGround: Drawable? = null,
    textColor: Int = Color.BLACK,
    visibility: Int = View.VISIBLE
): TextView {
    return createTextView(
        requireContext(),
        gravity,
        id,
        textSize,
        textSizeUnit,
        lineSpacing,
        maxLine,
        isSingleLine,
        ellipsize,
        backGround,
        text,
        textColor
    )
}

data class LineSpacing(val add: Float = 0.0F, val mult: Float = 1.0F)

fun Activity.textView(
    gravity: Int = Gravity.CENTER,
    id: Int = View.NO_ID,
    text: String? = null,
    textSize: Float = 0F,
    textSizeUnit: Int = TypedValue.COMPLEX_UNIT_DIP,
    lineSpacing: LineSpacing = LineSpacing(add = 0.0F, mult = 1.0F),
    maxLine: Int = Integer.MAX_VALUE,
    isSingleLine: Boolean = false,
    ellipsize: TextUtils.TruncateAt? = null,
    backGround: Drawable? = null,
    textColor: Int = Color.BLACK
): TextView {
    return createTextView(
        this,
        gravity,
        id,
        textSize,
        textSizeUnit,
        lineSpacing,
        maxLine,
        isSingleLine,
        ellipsize,
        backGround,
        text,
        textColor
    )
}

fun Activity.getStatusBarHeight(): Int {
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId)
    else Rect().apply { window.decorView.getWindowVisibleDisplayFrame(this) }.top
}

fun Activity.switchStatusBarIconLight(isLight: Boolean, statusBarColor: Int) {

    window.statusBarColor = statusBarColor

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.setSystemBarsAppearance(
            if (isLight) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )

        WindowInsetsControllerCompat(
            window,
            window.decorView
        ).isAppearanceLightStatusBars =
            isLight

        ViewCompat.getWindowInsetsController(window.decorView)?.apply {
            isAppearanceLightStatusBars = isLight
        }

        WindowCompat.getInsetsController(
            window,
            window.decorView
        )?.isAppearanceLightStatusBars = isLight

        window.decorView.systemUiVisibility =
            if (isLight) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0 // Deprecated in API level 30 // but only works than the above

    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (isLight) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0 // Deprecated in API level 30
    }

}

fun View.textView(
    context: Context,
    gravity: Int = Gravity.CENTER,
    id: Int = View.NO_ID,
    text: String? = null,
    textSize: Float = 0F,
    textSizeUnit: Int = TypedValue.COMPLEX_UNIT_SP,
    lineSpacing: LineSpacing = LineSpacing(add = 0.0F, mult = 1.0F),
    maxLine: Int = Integer.MAX_VALUE,
    isSingleLine: Boolean = false,
    ellipsize: TextUtils.TruncateAt? = null,
    backGround: Drawable? = null,
    textColor: Int = Color.BLACK
): TextView {
    return createTextView(
        context,
        gravity,
        id,
        textSize,
        textSizeUnit,
        lineSpacing,
        maxLine,
        isSingleLine,
        ellipsize,
        backGround,
        text,
        textColor
    )
}


fun Fragment.imageView(
    id: Int = View.NO_ID,
    resId: Int = 0
): ImageView {
    return createImageView(requireContext(), id, resId)
}


fun Activity.imageView(
    id: Int = View.NO_ID,
    resId: Int = 0
): ImageView {
    return createImageView(this, id, resId)
}

fun Fragment.button(
    id: Int = View.NO_ID,
    text: String? = null,
    backGround: Drawable? = null,
    isEnabled: Boolean = true,
    textColor: Int = Color.WHITE
): Button {
    return createButton(requireContext(), id, backGround, text, isEnabled, textColor)
}


fun Activity.button(
    id: Int = View.NO_ID,
    text: String? = null,
    backGround: Drawable? = null,
    isEnabled: Boolean = true,
    textColor: Int = Color.WHITE
): Button {
    return createButton(this, id, backGround, text, isEnabled, textColor)
}


fun Fragment.progressBar(
    id: Int = View.NO_ID,
    isEnabled: Boolean = true,
    visibility: Int = View.VISIBLE
): ProgressBar {
    return createProgressBar(requireContext(), id, isEnabled, visibility)
}


fun Activity.progressBar(
    id: Int = View.NO_ID,
    isEnabled: Boolean = true,
    visibility: Int = View.VISIBLE
): ProgressBar {
    return createProgressBar(this, id, isEnabled, visibility)
}

fun Fragment.frameLayout(id: Int = View.NO_ID, backGround: Drawable? = null): FrameLayout {
    return createFrameLayout(requireContext(), id, backGround)
}

fun Activity.frameLayout(id: Int = View.NO_ID, backGround: Drawable? = null): FrameLayout {
    return createFrameLayout(this, id, backGround)
}


fun Fragment.linearLayout(
    id: Int = View.NO_ID,
    orientation: Int = 1,
    gravity: Int = Gravity.CENTER
): LinearLayout {
    return createLinearLayout(requireContext(), id, orientation, gravity)
}

fun Activity.linearLayout(
    id: Int = View.NO_ID,
    orientation: Int = 1,
    gravity: Int = Gravity.CENTER
): LinearLayout {
    return createLinearLayout(this, id, orientation, gravity)
}

fun Fragment.editText(
    gravity: Int = Gravity.CENTER,
    hint: String = "",
    backGround: Drawable? = null,
    maxLine: Int = 1,
    isSingleLine: Boolean = true,
    maxWidth: Int = 0,
    inputType: Int,
    id: Int = View.NO_ID
): EditText {
    return createEditText(
        requireContext(),
        gravity,
        hint,
        backGround,
        maxLine,
        isSingleLine,
        maxWidth,
        inputType,
        id
    )
}


fun Activity.editText(
    gravity: Int = Gravity.CENTER,
    hint: String = "",
    backGround: Drawable? = null,
    maxLine: Int = 1,
    isSingleLine: Boolean = true,
    maxWidth: Int = 0,
    inputType: Int,
    id: Int = View.NO_ID
): EditText {
    return createEditText(
        this,
        gravity,
        hint,
        backGround,
        maxLine,
        isSingleLine,
        maxWidth,
        inputType,
        id
    )
}


val Fragment.wrapContent: Int
    get() = -2

val View.wrapContent: Int
    get() = -2


val Fragment.matchParent: Int
    get() = -1

val View.matchParent: Int
    get() = -1

val Activity.wrapContent: Int
    get() = -2

val Activity.matchParent: Int
    get() = -1

fun Fragment.createFrame(
    width: Int = 0,
    height: Int = 0,
    gravity: Int = 0,
    leftMargin: Int = 0,
    topMargin: Int = 0,
    rightMargin: Int = 0,
    bottomMargin: Int = 0
): FrameLayout.LayoutParams {
    return LayoutHelper.createFrame(
        width,
        height,
        gravity,
        leftMargin,
        topMargin,
        rightMargin,
        bottomMargin
    )
}

fun createFrame(
    width: Int = 0,
    height: Int = 0,
    gravity: Int = 0,
    leftMargin: Int = 0,
    topMargin: Int = 0,
    rightMargin: Int = 0,
    bottomMargin: Int = 0
): FrameLayout.LayoutParams {
    return LayoutHelper.createFrame(
        width,
        height,
        gravity,
        leftMargin,
        topMargin,
        rightMargin,
        bottomMargin
    )
}


fun Fragment.addView(
    parentView: View,
    childView: View,
    layoutParams: ViewGroup.LayoutParams
) {
    when (parentView) {
        is FrameLayout -> parentView.addView(childView, layoutParams)


        is LinearLayout -> parentView.addView(childView, layoutParams)


    }

}

fun Activity.addView(
    parentView: View,
    childView: View,
    layoutParams: ViewGroup.LayoutParams
) {
    when (parentView) {
        is FrameLayout -> parentView.addView(childView, layoutParams)


        is LinearLayout -> parentView.addView(childView, layoutParams)


    }

}

fun Fragment.addView(
    parentView: View,
    values: Map<View, ViewGroup.LayoutParams>
) {
    values.forEach {
        when (parentView) {
            is FrameLayout -> {
                parentView.addView(it.key, it.value)
            }

            is LinearLayout -> {
                parentView.addView(it.key, it.value)
            }

        }


    }

}

fun View.addView(
    parentView: View,
    childView: View,
    layoutParams: ViewGroup.LayoutParams
) {
    when (parentView) {

        is FrameLayout -> parentView.addView(childView, layoutParams)

        is LinearLayout -> parentView.addView(childView, layoutParams)

    }
}

fun View.addView(
    parentView: View,
    values: Map<View, ViewGroup.LayoutParams>
) {
    values.forEach {
        when (parentView) {
            is FrameLayout -> {
                parentView.addView(it.key, it.value)
            }

            is LinearLayout -> {
                parentView.addView(it.key, it.value)
            }

        }


    }

}

fun Fragment.createLinear(
    width: Int = 0,
    height: Int = 0,
    weight: Float = 0F,
    gravity: Int = -1,
    leftMargin: Int = 0,
    topMargin: Int = 0,
    rightMargin: Int = 0,
    bottomMargin: Int = 0
): LinearLayout.LayoutParams {
    return LayoutHelper.createLinear(
        width,
        height,
        weight,
        gravity,
        leftMargin,
        topMargin,
        rightMargin,
        bottomMargin
    )
}

fun Activity.createLinear(
    width: Int = 0,
    height: Int = 0,
    weight: Float = 0F,
    gravity: Int = -1,
    leftMargin: Int = 0,
    topMargin: Int = 0,
    rightMargin: Int = 0,
    bottomMargin: Int = 0
): LinearLayout.LayoutParams {
    return LayoutHelper.createLinear(
        width,
        height,
        weight,
        gravity,
        leftMargin,
        topMargin,
        rightMargin,
        bottomMargin
    )
}

fun View.createLinear(
    width: Int = 0,
    height: Int = 0,
    weight: Float = 0F,
    gravity: Int = -1,
    leftMargin: Int = 0,
    topMargin: Int = 0,
    rightMargin: Int = 0,
    bottomMargin: Int = 0
): LinearLayout.LayoutParams {
    return LayoutHelper.createLinear(
        width,
        height,
        weight,
        gravity,
        leftMargin,
        topMargin,
        rightMargin,
        bottomMargin
    )
}

fun createFrameLayout(
    context: Context,
    id: Int,
    backGround: Drawable?
): FrameLayout {
    return FrameLayout(context).apply {
        this.id = id
        this.background = background
    }
}


fun createLinearLayout(
    context: Context,
    id: Int,
    orientation: Int,
    gravity: Int
): LinearLayout {
    return LinearLayout(context).apply {
        this.id = id
        this.orientation = orientation
        this.gravity = gravity
    }
}


fun createButton(
    context: Context,
    id: Int,
    background: Drawable?,
    text: String?,
    isEnabled: Boolean,
    textColor: Int
): Button {
    return Button(context).apply {
        this.id = id
        this.text = text
        this.background = background
        this.isEnabled = isEnabled
        this.setTextColor(textColor)
    }
}


fun createProgressBar(
    context: Context,
    id: Int,
    isEnabled: Boolean,
    visibility: Int
): ProgressBar {
    return ProgressBar(context).apply {
        this.id = id
        this.isEnabled = isEnabled
        this.visibility = visibility
    }
}

fun createTextView(
    context: Context,
    gravity: Int,
    id: Int,
    textSize: Float,
    textSizeUnit: Int,
    lineSpacing: LineSpacing,
    maxLine: Int,
    isSingleLine: Boolean,
    ellipsize: TextUtils.TruncateAt?,
    backGround: Drawable?,
    text: String?,
    textColor: Int
): TextView {

    return TextView(context).apply {
        this.gravity = gravity
        this.id = id
        this.text = text
        this.background = backGround
        this.setTextSize(
            textSizeUnit, if (textSize == 0F) {
                this.textSize
            } else {
                textSize
            }
        )
        this.setLineSpacing(lineSpacing.add, lineSpacing.mult)
        this.maxLines = maxLine
        this.isSingleLine = isSingleLine
        this.ellipsize = ellipsize
        setTextColor(textColor)

    }

}


fun createEditText(
    context: Context,
    gravity: Int,
    hint: String,
    backGround: Drawable?,
    maxLine: Int,
    isSingleLine: Boolean,
    maxWidth: Int,
    inputType: Int,
    id: Int
): EditText {
    return EditText(context).apply {
        this.gravity = gravity
        this.hint = hint
        this.id = id
        this.background = backGround
        this.maxLines = maxLine
        this.isSingleLine = isSingleLine
        this.maxWidth = maxWidth
        this.inputType = inputType

    }
}


fun createImageView(context: Context, id: Int, resId: Int): ImageView {
    return ImageView(context).apply {
        this.id = id
        if (resId != 0) {
            background = AppCompatResources.getDrawable(context, resId)
        }
    }
}

fun View.addViewToConstraintLayout(
    parentView: ConstraintLayout,
    childView: View
) {
    parentView.addView(childView)
}

fun View.addViewToConstraintLayout(
    parentView: ConstraintLayout,
    childViews: List<View>
) {
    childViews.forEach {
        parentView.addView(it)
    }
}

fun Fragment.addViewToConstraintLayout(
    parentView: ConstraintLayout,
    childView: View
) {
    parentView.addView(childView)
}

fun Fragment.addViewToConstraintLayout(
    parentView: ConstraintLayout,
    childViews: List<View>
) {
    childViews.forEach {
        parentView.addView(it)
    }
}


fun Fragment.addConstraintSet(
    childViewId: Int,
    height: Int,
    width: Int,
    topToTop: Int? = null,
    topToBottom: Int? = null,
    bottomToTop: Int? = null,
    bottomToBottom: Int? = null,
    startToStart: Int? = null,
    startToEnd: Int? = null,
    endToEnd: Int? = null,
    endToStart: Int? = null,
    marginTop: Int = 0,
    marginBottom: Int = 0,
    marginStart: Int = 0,
    marginEnd: Int = 0,
    horizontalBias: Float = 0.5f,
    verticalBias: Float = 0.5f,
    verticalWeight: Float = ConstraintSet.UNSET.toFloat(),
    horizontalWeight: Float = 0f,
    parentView: ConstraintLayout
) {
    val set = ConstraintSet()
    set.constrainHeight(childViewId, height)
    set.constrainWidth(childViewId, width)
    set.setHorizontalBias(childViewId, horizontalBias)
    set.setVerticalBias(childViewId, verticalBias)
    set.setVerticalWeight(childViewId,verticalWeight)
    topToTop?.let {
        set.connect(
            childViewId,
            ConstraintSet.TOP,
            topToTop,
            ConstraintSet.TOP,
            marginTop
        )
    }
    if (topToBottom != null) {
        set.connect(childViewId, ConstraintSet.TOP, topToBottom, ConstraintSet.BOTTOM, marginTop)
    }
    if (bottomToTop != null) {
        set.connect(childViewId, ConstraintSet.BOTTOM, bottomToTop, ConstraintSet.TOP, marginBottom)
    }
    if (bottomToBottom != null) {
        set.connect(
            childViewId,
            ConstraintSet.BOTTOM,
            bottomToBottom,
            ConstraintSet.BOTTOM,
            marginBottom
        )
    }
    if (startToStart != null) {
        set.connect(
            childViewId,
            ConstraintSet.START,
            startToStart,
            ConstraintSet.START,
            marginStart
        )
    }
    if (startToEnd != null) {
        set.connect(childViewId, ConstraintSet.START, startToEnd, ConstraintSet.END, marginStart)
    }
    if (endToEnd != null) {
        set.connect(childViewId, ConstraintSet.END, endToEnd, ConstraintSet.END, marginEnd)
    }
    if (endToStart != null) {
        set.connect(childViewId, ConstraintSet.END, endToStart, ConstraintSet.START, marginEnd)
    }

    set.applyTo(parentView)

}

fun Activity.addViewToConstraintLayout(
    parentView: ConstraintLayout,
    childView: View
) {
    parentView.addView(childView)
}

fun Activity.addViewToConstraintLayout(
    parentView: ConstraintLayout,
    childViews: List<View>
) {
    childViews.forEach {
        parentView.addView(it)
    }
}


fun Activity.addConstraintSet(
    childViewId: Int,
    height: Int,
    width: Int,
    topToTop: Int? = null,
    topToBottom: Int? = null,
    bottomToTop: Int? = null,
    bottomToBottom: Int? = null,
    startToStart: Int? = null,
    startToEnd: Int? = null,
    endToEnd: Int? = null,
    endToStart: Int? = null,
    marginTop: Int = 0,
    marginBottom: Int = 0,
    marginStart: Int = 0,
    marginEnd: Int = 0,
    parentView: ConstraintLayout
) {
    val set = ConstraintSet()
    set.constrainHeight(childViewId, height)
    set.constrainWidth(childViewId, width)
    topToTop?.let {
        set.connect(
            childViewId,
            ConstraintSet.TOP,
            topToTop,
            ConstraintSet.TOP,
            marginTop
        )
    }
    if (topToBottom != null) {
        set.connect(childViewId, ConstraintSet.TOP, topToBottom, ConstraintSet.BOTTOM, marginTop)
    }
    if (bottomToTop != null) {
        set.connect(childViewId, ConstraintSet.BOTTOM, bottomToTop, ConstraintSet.TOP, marginBottom)
    }
    if (bottomToBottom != null) {
        set.connect(
            childViewId,
            ConstraintSet.BOTTOM,
            bottomToBottom,
            ConstraintSet.BOTTOM,
            marginBottom
        )
    }
    if (startToStart != null) {
        set.connect(
            childViewId,
            ConstraintSet.START,
            startToStart,
            ConstraintSet.START,
            marginStart
        )
    }
    if (startToEnd != null) {
        set.connect(childViewId, ConstraintSet.START, startToEnd, ConstraintSet.END, marginStart)
    }
    if (endToEnd != null) {
        set.connect(childViewId, ConstraintSet.END, endToEnd, ConstraintSet.END, marginEnd)
    }
    if (endToStart != null) {
        set.connect(childViewId, ConstraintSet.END, endToStart, ConstraintSet.START, marginEnd)
    }

    set.applyTo(parentView)

}

fun Activity.addConstraintSet(
    childViewId: Int,
    height: Int,
    width: Int,
    topToTop: Int? = null,
    topToBottom: Int? = null,
    bottomToTop: Int? = null,
    bottomToBottom: Int? = null,
    startToStart: Int? = null,
    startToEnd: Int? = null,
    endToEnd: Int? = null,
    endToStart: Int? = null,
    marginTop: Int = 0,
    marginBottom: Int = 0,
    marginStart: Int = 0,
    marginEnd: Int = 0,
    parentView: ConstraintLayout,
    horizontalWeight: Float = 1f,
    defaultWidth: Int = ConstraintSet.MATCH_CONSTRAINT_WRAP,
    chainStyle: Int? = ConstraintSet.CHAIN_PACKED,
    leftChainViewId: Int? = null,
    rightChainViewId: Int? = null,
    horizontalBias: Float = 0f,
    constraintedWidth: Boolean? = null
) {
    val set = ConstraintSet()
    set.clone(parentView)
    set.constrainHeight(childViewId, height)
    set.constrainWidth(childViewId, width)
    topToTop?.let {
        set.connect(
            childViewId,
            ConstraintSet.TOP,
            topToTop,
            ConstraintSet.TOP
        )
    }
    if (topToBottom != null) {
        set.connect(childViewId, ConstraintSet.TOP, topToBottom, ConstraintSet.BOTTOM, marginTop)
    }
    if (bottomToTop != null) {
        set.connect(childViewId, ConstraintSet.BOTTOM, bottomToTop, ConstraintSet.TOP)
    }
    if (bottomToBottom != null) {
        set.connect(
            childViewId,
            ConstraintSet.BOTTOM,
            bottomToBottom,
            ConstraintSet.BOTTOM
        )
    }
    if (startToStart != null) {
        set.connect(
            childViewId,
            ConstraintSet.START,
            startToStart,
            ConstraintSet.START)
    }
    if (startToEnd != null) {
        set.connect(childViewId, ConstraintSet.START, startToEnd, ConstraintSet.END)
    }
    if (endToEnd != null) {
        set.connect(childViewId, ConstraintSet.END, endToEnd, ConstraintSet.END)
    }
    if (endToStart != null) {
        set.connect(childViewId, ConstraintSet.END, endToStart, ConstraintSet.START)
    }
    constraintedWidth?.let {
        set.constrainedWidth(childViewId, it)
    }
    if (chainStyle != null && leftChainViewId != null && rightChainViewId != null) {
        set.createHorizontalChain(
            leftChainViewId, ConstraintSet.LEFT,
            rightChainViewId, ConstraintSet.RIGHT,
            intArrayOf(
                leftChainViewId,
                rightChainViewId
            ),
            null,
            chainStyle
        )
    }

    set.setMargin(childViewId, ConstraintSet.START, marginStart)
    set.setMargin(childViewId, ConstraintSet.TOP, marginTop)
    set.setMargin(childViewId, ConstraintSet.END, marginEnd)
    set.setMargin(childViewId, ConstraintSet.BOTTOM, marginBottom)

    set.setHorizontalBias(childViewId, horizontalBias)

    set.applyTo(parentView)

}
var statusBarHeight = 0
var displaySize: Point = Point()

private var mAttachInfoField: Field? = null
private var mStableInsetsField: Field? = null

var density = 1f
var displayMetrics = DisplayMetrics()
var usingKeyboardInput = false
@SuppressLint("SoonBlockedPrivateApi")
fun View.getViewInset(): Int {
    if (Build.VERSION.SDK_INT < 21 || this.height == displaySize.y || this.height == displaySize.y - statusBarHeight) {
        return 0
    } else if (Build.VERSION.SDK_INT > 28) {
        return this.rootWindowInsets.stableInsetBottom
    }
    try {
        if (mAttachInfoField == null) {
            mAttachInfoField = View::class.java.getDeclaredField("mAttachInfo")
            mAttachInfoField?.isAccessible = true
        }
        val mAttachInfo: Any? = mAttachInfoField!!.get(this)
        if (mStableInsetsField == null) {
            mStableInsetsField = mAttachInfo?.javaClass?.getDeclaredField("mStableInsets")
            mStableInsetsField?.run { isAccessible = true }
        }
        val insets = mStableInsetsField?.get(mAttachInfo) as Rect
        return insets.bottom
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return 0
}

fun Fragment.onBackPressed(backPressed : () -> Unit) {
    val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backPressed()
            }
        }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
}

fun View.addConstraintSet(
    childViewId: Int,
    height: Int,
    width: Int,
    topToTop: Int? = null,
    topToBottom: Int? = null,
    bottomToTop: Int? = null,
    bottomToBottom: Int? = null,
    startToStart: Int? = null,
    startToEnd: Int? = null,
    endToEnd: Int? = null,
    endToStart: Int? = null,
    marginTop: Int = 0,
    marginBottom: Int = 0,
    marginStart: Int = 0,
    marginEnd: Int = 0,
    parentView: ConstraintLayout,
    horizontalWeight: Float = 1f,
    defaultWidth: Int = ConstraintSet.MATCH_CONSTRAINT_WRAP,
    chainStyle: Int = ConstraintSet.CHAIN_PACKED,
    horizontalBias: Float = 0.5f,
    verticalBias: Float = 0.5f
) {
    val set = ConstraintSet()
    set.constrainHeight(childViewId, height)
    set.constrainWidth(childViewId, width)
    set.setHorizontalBias(childViewId, horizontalBias)
    set.setVerticalBias(childViewId, verticalBias)


    topToTop?.let {
        set.connect(
            childViewId,
            ConstraintSet.TOP,
            topToTop,
            ConstraintSet.TOP,
            marginTop
        )
    }
    if (topToBottom != null) {
        set.connect(childViewId, ConstraintSet.TOP, topToBottom, ConstraintSet.BOTTOM, marginTop)
    }
    if (bottomToTop != null) {
        set.connect(childViewId, ConstraintSet.BOTTOM, bottomToTop, ConstraintSet.TOP, marginBottom)
    }
    if (bottomToBottom != null) {
        set.connect(
            childViewId,
            ConstraintSet.BOTTOM,
            bottomToBottom,
            ConstraintSet.BOTTOM,
            marginBottom
        )
    }
    if (startToStart != null) {
        set.connect(
            childViewId,
            ConstraintSet.START,
            startToStart,
            ConstraintSet.START,
            marginStart
        )
    }
    if (startToEnd != null) {
        set.connect(childViewId, ConstraintSet.START, startToEnd, ConstraintSet.END, marginStart)
    }
    if (endToEnd != null) {
        set.connect(childViewId, ConstraintSet.END, endToEnd, ConstraintSet.END, marginEnd)
    }
    if (endToStart != null) {
        set.connect(childViewId, ConstraintSet.END, endToStart, ConstraintSet.START, marginEnd)
    }

    set.applyTo(parentView)


}

fun Fragment.showSnackBar(text: String) {
    Snackbar.make(
        requireView(),
        text,
        Snackbar.LENGTH_SHORT
    ).show()
}




fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)