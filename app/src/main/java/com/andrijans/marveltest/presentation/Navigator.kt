package com.andrijans.marveltest.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.andrijans.marveltest.R
import com.andrijans.marveltest.framework.api.entity.Character
import com.andrijans.marveltest.presentation.details.DetailsActivity

/**
 * Created by andrijanstankovic on 04/04/2018.
 */
class Navigator {
    private fun startActivity(context: Context,intent: Intent,shouldFinish:Boolean){
        val activity:Activity=context as Activity
        activity.startActivity(intent)
        if (shouldFinish) activity.finish()
    }

    fun navigateToDetailsScreen(context: Context,character: Character,shouldFinish: Boolean){
        startActivity(context,DetailsActivity.getCallingIntent(context,character),shouldFinish)
    }

    fun openWebAddress(context: Context, url: String) {
        val intent = CustomTabsIntent.Builder()
                .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setCloseButtonIcon(BitmapFactory.decodeResource(context.resources, R.drawable.icon_back))
                .build()
        intent.launchUrl(context, Uri.parse(url))

    }

}