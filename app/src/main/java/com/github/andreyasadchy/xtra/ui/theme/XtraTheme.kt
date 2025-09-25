package com.github.andreyasadchy.xtra.ui.theme

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.github.andreyasadchy.xtra.util.C
import com.github.andreyasadchy.xtra.util.prefs

/**
 * Jetpack Compose theme that integrates with existing Xtra Material 3 theme preferences
 */
@Composable
fun XtraTheme(
    context: Context = LocalContext.current,
    content: @Composable () -> Unit
) {
    val prefs = remember { context.prefs() }
    val useMaterial3 = remember { prefs.getBoolean(C.UI_THEME_MATERIAL3, true) }
    val followSystem = remember { prefs.getBoolean(C.UI_THEME_FOLLOW_SYSTEM, false) }
    
    val themeId = remember {
        if (followSystem) {
            when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> prefs.getString(C.UI_THEME_DARK_ON, "0")!!
                else -> prefs.getString(C.UI_THEME_DARK_OFF, "2")!!
            }
        } else {
            prefs.getString(C.THEME, "0")!!
        }
    }
    
    val colorScheme = when {
        useMaterial3 && listOf("4", "6", "5").contains(themeId) -> {
            // Use dynamic colors when available (Material You)
            when (themeId) {
                "5" -> dynamicLightColorScheme(context)
                else -> dynamicDarkColorScheme(context)
            }
        }
        themeId == "5" || themeId == "2" -> {
            // Light themes
            lightColorScheme()
        }
        else -> {
            // Dark themes (including amoled)
            darkColorScheme()
        }
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = XtraTypography,
        content = content
    )
}

/**
 * Composable that can be used inside existing View-based UI to provide Compose theming
 */
@Composable
fun XtraComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val prefs = remember { context.prefs() }
    val useMaterial3 = remember { prefs.getBoolean(C.UI_THEME_MATERIAL3, true) }
    
    val colorScheme = when {
        useMaterial3 -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = XtraTypography,
        content = content
    )
}