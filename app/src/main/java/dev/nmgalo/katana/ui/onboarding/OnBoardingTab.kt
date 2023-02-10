package dev.nmgalo.katana.ui.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.nmgalo.katana.R

enum class OnBoardingTab(@StringRes val body: Int, @DrawableRes val image: Int) {
    First(body = R.string.onb_community, image = R.drawable.onboarding_illustation_first),
    Second(body = R.string.onb_stay_in_touch, image = R.drawable.onboarding_illustation_secong),
    Third(body = R.string.onb_personal_desk, image = R.drawable.onboarding_illustation_third),
}
