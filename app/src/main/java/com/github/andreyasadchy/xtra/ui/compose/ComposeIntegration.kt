package com.github.andreyasadchy.xtra.ui.compose

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.github.andreyasadchy.xtra.ui.theme.XtraComposeTheme

/**
 * Helper class to easily integrate Compose content into existing View-based layouts
 */
class XtraComposeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ComposeView(context, attrs, defStyleAttr) {

    init {
        // Use DisposeOnViewTreeLifecycleDestroyed to ensure proper cleanup
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    }

    /**
     * Set Compose content with XtraTheme applied
     */
    fun setXtraContent(content: @Composable () -> Unit) {
        setContent {
            XtraComposeTheme {
                content()
            }
        }
    }
}

/**
 * Extension function to easily add Compose content to existing ViewGroups
 */
fun ViewGroup.addComposeView(content: @Composable () -> Unit): XtraComposeView {
    return XtraComposeView(context).apply {
        setXtraContent(content)
        addView(this)
    }
}

/**
 * Extension function to create a ComposeView for use in existing layouts
 */
fun Context.createXtraComposeView(content: @Composable () -> Unit): XtraComposeView {
    return XtraComposeView(this).apply {
        setXtraContent(content)
    }
}