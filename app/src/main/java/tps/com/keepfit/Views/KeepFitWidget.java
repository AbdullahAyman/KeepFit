package tps.com.keepfit.Views;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.RemoteViews;

import tps.com.keepfit.R;
import tps.com.keepfit.utilities.KeepFitApp;

/**
 * Implementation of App Widget functionality.
 */
public class KeepFitWidget extends AppWidgetProvider {
    static int cardId = 0;

    public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context) {

        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h = (int) (newHeight * densityMultiplier);
        int w = (int) (h * photo.getWidth() / ((double) photo.getHeight()));

        photo = Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget_view);
        if (KeepFitApp.cardListOfData != null)
            if (cardId < KeepFitApp.cardListOfData.size()) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), (int) KeepFitApp.cardListOfData.get(cardId).getCardImage());
                views.setTextViewText(R.id.widget_card_name, KeepFitApp.cardListOfData.get(cardId).getCardName());
                views.setTextViewText(R.id.widget_card_duration, context.getResources().getString(R.string.labelDuration) + KeepFitApp.cardListOfData.get(cardId).getCardDuration());
                /*ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 10, bos);*/
                views.setImageViewBitmap(R.id.widget_img_card, scaleDownBitmap(bitmap, 150, context));
                cardId++;
            } else {
                cardId = 0;
                updateAppWidget(context, appWidgetManager, appWidgetId);
            }

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.rlt_widget, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
        if (KeepFitApp.cardListOfData != null)
            Log.i("updateWidget", KeepFitApp.cardListOfData.get(cardId).getCardName());
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
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


}

