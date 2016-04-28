package hrmsoftware.se.trashfriendwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.widget.RemoteViews;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The trash friend widget main entry point.
 */
public class TrashFriendWidget extends AppWidgetProvider {

    private static String WEBSITE_URL = "http://172.20.10.105:8100";
    private static String SERVICE_URL = "http://172.20.10.105:8080/trashme/";
    private static int status = 0;
    private static Status.Weather weather = Status.Weather.SUNNY;

    static void updateAppWidget(Context context,
                                AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.trash_friend_widget);

        if (status == 0) {
            views.setImageViewResource(R.id.status, R.drawable.imagefiles_seal_circle_green);
        }
        else if (status == 1) {
            views.setImageViewResource(R.id.status, R.drawable.imagefiles_seal_circle_yellow);
        }
        else {
            views.setImageViewResource(R.id.status, R.drawable.imagefiles_seal_circle_red);
        }

        if (weather.equals(Status.Weather.SUNNY)) {
            views.setImageViewResource(R.id.weather, R.drawable.imagefiles_simple_weather_icons_sunny);
        }
        else if (weather.equals(Status.Weather.CLOUD)) {
            views.setImageViewResource(R.id.weather, R.drawable.imagefiles_simple_weather_icons_cloudy);
        }
        else if (weather.equals(Status.Weather.SUNANDCLOUD)) {
            views.setImageViewResource(R.id.weather, R.drawable.imagefiles_simple_weather_icons_partly_cloudy);
        }
        else {
            views.setImageViewResource(R.id.weather, R.drawable.imagefiles_simple_weather_icons_rain);
        }

        Uri uri = Uri.parse(WEBSITE_URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        views.setOnClickPendingIntent(R.id.widget_layout,
                PendingIntent.getActivity(context, appWidgetId, intent, 0));

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        updateStatus();

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        updateStatus();
    }

    private void updateStatus() {
        new Handler(Looper.getMainLooper()).post(new StatusFetcher());
    }

    /**
     * Fetches status updates from the trash friend service.
     */
    class StatusFetcher implements Runnable {

        public StatusFetcher() {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        @Override
        public void run() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SERVICE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            TrashFriendService service = retrofit.create(TrashFriendService.class);

            try {
                Response<Status> response = service.now().execute();

                if (response.isSuccessful()) {
                    status = response.body().getBusyStatus();
                    weather = response.body().getWeather();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

