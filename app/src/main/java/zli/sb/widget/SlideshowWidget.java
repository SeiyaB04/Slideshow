package zli.sb.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import zli.sb.slideshow.MainActivity;
import zli.sb.slideshow.PictureActivity;
import zli.sb.slideshow.R;

/**
 * Implementation of App Widget functionality.
 */
public class SlideshowWidget extends AppWidgetProvider {
    private static int i = 0;
    private static final String MyOnClick = "buttonOnclick";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.i("LOG_TAG", "in onUpdate");
        
        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int appWidgetId : appWidgetIds) {

            // Create an Intent to launch ExampleActivity
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.slideshow_widget);

            /*views.setOnClickPendingIntent(R.id.button
                    , getPendingSelfIntent(context, MyOnClick));

             */

            views.setOnClickPendingIntent(R.id.widgetImageView, pendingIntent);



            // Tell the AppWidgetManager to perform an update on the current app widget
            //appWidgetManager.updateAppWidget(appWidgetId, views);
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /*
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("LOG_TAG", "in onReceive");
        if (MyOnClick.equals(intent.getAction())){
            //your onClick action is here
            super.onReceive(context, intent);

            Bundle extras = intent.getExtras();
            int[] newIds = extras.getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS);
            onUpdate(context, AppWidgetManager.getInstance(context), newIds);

            Toast.makeText(context, "Clicked!!", Toast.LENGTH_SHORT).show();
        }
    }

     */

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Log.i("LOG_TAG", "in updateAppWidget");

        //CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.slideshow_widget);
        //views.setTextViewText(R.id.picture, widgetText);



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Log.i("LOG_TAG", "Before image uri");
        Log.i("LOG_TAG", preferences.getString("image", null));
        if (preferences.getString("image", null) == null) {
            i = 0;
        } else {
            String imageEncoded = preferences.getString("image", null);
            Log.i("LOG_TAG", "in else");
            Uri myUri = Uri.parse(imageEncoded);
            views.setImageViewUri(R.id.widgetImageView, myUri);
            appWidgetManager.updateAppWidget(appWidgetId, views);

            i++;
        }

        // Instruct the widget manager to update the widget
        //appWidgetManager.updateAppWidget(appWidgetId, views);
    }
/*
    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

 */


}