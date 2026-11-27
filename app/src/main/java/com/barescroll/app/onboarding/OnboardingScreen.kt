package com.barescroll.app.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.barescroll.app.ui.theme.BareBlack
import com.barescroll.app.ui.theme.BareGrey
import com.barescroll.app.ui.theme.BareGreyDark
import com.barescroll.app.ui.theme.BareGreyLight
import com.barescroll.app.ui.theme.BareGreySurface
import com.barescroll.app.ui.theme.BareWhite
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * First-run onboarding, screens 1 and 2 from SCREENS_AND_FLOWS.md:
 *  1. Time used today, and marking which apps are distracting.
 *  2. The year at this pace, as a labelled 60-square projection grid.
 *
 * All numbers here are sample values, labelled on screen per the honesty
 * principle in PRODUCT_OVERVIEW.md. Real numbers come from local usage
 * events once usage access is granted.
 */
@Composable
fun OnboardingScreen(onFinished: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val scope = rememberCoroutineScope()

    // Sample values, replaced by real usage events later.
    val sampleMinutesToday = 192 // 3h 12m
    val projectedDaysPerYear = (sampleMinutesToday / 60.0 * 365 / 24).roundToInt() // ~48

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BareBlack)
            .padding(horizontal = 24.dp),
    ) {
        Spacer(Modifier.height(28.dp))
        Text(
            text = "Bare Scroll",
            style = MaterialTheme.typography.bodyMedium,
            color = BareGrey,
        )
        Spacer(Modifier.height(16.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
        ) { page ->
            when (page) {
                0 -> TimeUsedPage(minutesToday = sampleMinutesToday)
                else -> YearPage(projectedDays = projectedDaysPerYear)
            }
        }

        PageDots(
            count = 2,
            selected = pagerState.currentPage,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Spacer(Modifier.height(16.dp))

        val isLast = pagerState.currentPage == 1
        Button(
            onClick = {
                if (isLast) {
                    onFinished()
                } else {
                    scope.launch { pagerState.animateScrollToPage(1) }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(26.dp), // pill buttons per visual direction
            colors = ButtonDefaults.buttonColors(
                containerColor = BareWhite,
                contentColor = BareBlack,
            ),
        ) {
            Text(
                text = if (isLast) "Start blocking" else "Continue",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        Spacer(Modifier.height(24.dp))
    }
}

/** Welcome 1: time used, plus marking distracting apps. */
@Composable
private fun TimeUsedPage(minutesToday: Int) {
    // Sample apps shown on first run; the real list comes from installed
    // apps and usage events. Distracting apps render monochrome in-app.
    val apps = listOf("Instagram", "YouTube", "Snapchat")
    val marked = remember { mutableStateMapOf("Instagram" to true, "YouTube" to true) }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = formatMinutes(minutesToday),
            style = MaterialTheme.typography.displayLarge,
            color = BareWhite,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "given to short feeds today",
            style = MaterialTheme.typography.bodyLarge,
            color = BareGreyLight,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Sample numbers. Real ones appear here once usage access is on.",
            style = MaterialTheme.typography.labelSmall,
            color = BareGrey,
        )
        Spacer(Modifier.height(28.dp))
        HorizontalDivider(color = BareGreyDark)
        Spacer(Modifier.height(20.dp))
        Text(
            text = "Which of these eat your time?",
            style = MaterialTheme.typography.headlineMedium,
            color = BareWhite,
        )
        Spacer(Modifier.height(16.dp))
        apps.forEach { name ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Placeholder mark; real monochrome app icons come from the
                // Simple Icons set noted in TECH_STACK.md.
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(BareGreySurface)
                        .border(1.dp, BareGreyDark, RoundedCornerShape(8.dp)),
                )
                Spacer(Modifier.size(14.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = BareWhite,
                    modifier = Modifier.weight(1f),
                )
                Switch(
                    checked = marked[name] == true,
                    onCheckedChange = { marked[name] = it },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = BareWhite,
                        checkedThumbColor = BareBlack,
                        uncheckedTrackColor = BareGreyDark,
                        uncheckedThumbColor = BareGreyLight,
                        uncheckedBorderColor = BareGreyDark,
                    ),
                )
            }
        }
    }
}

/** Welcome 2: the year at this pace, as a 60-square grid. */
@Composable
private fun YearPage(projectedDays: Int) {
    val totalSquares = 60
    val filledSquares = (projectedDays / 365.0 * totalSquares).roundToInt()

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = "$projectedDays days",
            style = MaterialTheme.typography.displayLarge,
            color = BareWhite,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "a year to short feeds at this pace",
            style = MaterialTheme.typography.bodyLarge,
            color = BareGreyLight,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Projection from today's sample. Each square is about 6 days.",
            style = MaterialTheme.typography.labelSmall,
            color = BareGrey,
        )
        Spacer(Modifier.height(32.dp))

        val columns = 10
        val rows = totalSquares / columns
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(rows) { row ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(columns) { col ->
                        val index = row * columns + col
                        val filled = index < filledSquares
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (filled) BareWhite else BareGreySurface)
                                .then(
                                    if (filled) Modifier
                                    else Modifier.border(1.dp, BareGreyDark, RoundedCornerShape(4.dp)),
                                ),
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(32.dp))
        Text(
            text = "Bare Scroll blocks the feed, not the app. You keep messages, search and the creators you chose.",
            style = MaterialTheme.typography.bodyMedium,
            color = BareGreyLight,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
private fun PageDots(count: Int, selected: Int, modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(count) { index ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(if (index == selected) BareWhite else BareGreyDark),
            )
        }
    }
}

private fun formatMinutes(minutes: Int): String {
    val h = minutes / 60
    val m = minutes % 60
    return if (h > 0) "${h}h ${m}m" else "${m}m"
}
