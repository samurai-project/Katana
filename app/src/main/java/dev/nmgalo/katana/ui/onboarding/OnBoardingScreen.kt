package dev.nmgalo.katana.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dev.nmgalo.katana.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val onBoardingItemSize = OnBoardingTab.values().size

    Scaffold { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            HorizontalPager(
                modifier = modifier.weight(weight = 1f),
                state = pagerState,
                count = onBoardingItemSize
            ) { page ->
                OnBoardingPagerItem(page)
            }
            OnBoardingControls(
                isLastItem = pagerState.currentPage + 1 == onBoardingItemSize,
                onSkip = viewModel::disableOnBoardingScreen,
                onNext = {
                    scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                }
            )
        }
    }
}

@Composable
fun OnBoardingPagerItem(page: Int) {
    val item = OnBoardingTab.values()[page]
    Column(modifier = Modifier.padding(20.dp)) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(item.image),
            contentDescription = "OnBoarding Image"
        )

        Text(
            text = stringResource(id = R.string.step_number, page + 1),
            style = MaterialTheme.typography.labelSmall
        )

        Text(text = stringResource(id = item.body), style = MaterialTheme.typography.displayLarge)
    }
}

@Composable
fun OnBoardingControls(
    modifier: Modifier = Modifier,
    isLastItem: Boolean,
    onSkip: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = stringResource(id = R.string.skip),
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.clickable { onSkip.invoke() }
        )
        OutlinedButton(
            modifier = modifier
                .size(100.dp)
                .clip(CircleShape)
                .align(Alignment.Bottom),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            onClick = {
                if (isLastItem) onSkip.invoke() else onNext.invoke()
            }
        ) {
            val iconModifier = modifier.fillMaxSize(fraction = 0.75F)
            if (isLastItem) Icon(Icons.Filled.Close, "Close", modifier = iconModifier)
            else Icon(Icons.AutoMirrored.Filled.ArrowRightAlt, "Next", modifier = iconModifier)
        }
    }
}
