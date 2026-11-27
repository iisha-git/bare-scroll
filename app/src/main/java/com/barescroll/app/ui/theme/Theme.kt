package com.barescroll.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Visual direction (SCREENS_AND_FLOWS.md): flat black, white and grey only,
// clean sans type, no glow, no gradients.
val BareBlack = Color(0xFF000000)
val BareWhite = Color(0xFFFFFFFF)
val BareGreyLight = Color(0xFFBDBDBD)
val BareGrey = Color(0xFF8A8A8A)
val BareGreyDark = Color(0xFF3A3A3A)
val BareGreySurface = Color(0xFF141414)

// TECH_STACK.md names Noto Sans. Until font assets are bundled we use the
// system sans, which keeps the same flat, plain look.
private val BareTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 64.sp,
        letterSpacing = (-1.5).sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
)

private val BareColorScheme = darkColorScheme(
    primary = BareWhite,
    onPrimary = BareBlack,
    background = BareBlack,
    onBackground = BareWhite,
    surface = BareBlack,
    onSurface = BareWhite,
    surfaceVariant = BareGreySurface,
    onSurfaceVariant = BareGreyLight,
    outline = BareGreyDark,
    secondary = BareGrey,
    onSecondary = BareBlack,
)

@Composable
fun BareScrollTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = BareColorScheme,
        typography = BareTypography,
        content = content,
    )
}
