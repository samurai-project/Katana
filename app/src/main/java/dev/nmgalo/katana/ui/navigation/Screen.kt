package dev.nmgalo.katana.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.nmgalo.katana.R

enum class Screen(val route: String, @StringRes val title: Int, @DrawableRes val icon: Int) {
    Wall("wall", R.string.wall, R.drawable.ic_wall),
    Profile("profile", R.string.profile, R.drawable.ic_account)
}
