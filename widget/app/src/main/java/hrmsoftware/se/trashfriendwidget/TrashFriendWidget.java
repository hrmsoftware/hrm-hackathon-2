package hrmsoftware.se.trashfriendwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
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

    private static int status = 1;
    private static Status.Weather weather = Status.Weather.SUNNY;

    static void updateAppWidget(Context context,
                                AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.trash_friend_widget);

        if (status == 1) {
            views.setImageViewResource(R.id.status, R.drawable.imagefiles_seal_circle_green);
        }
        else if (status == 2) {
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

        private String url = "http://172.20.10.87:8080/trashme/";

        public StatusFetcher() {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        @Override
        public void run() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
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

