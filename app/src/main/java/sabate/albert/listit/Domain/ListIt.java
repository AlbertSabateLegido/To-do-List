package sabate.albert.listit.Domain;

import android.app.Application;
import android.content.Context;

public class ListIt extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ListIt.context = getApplicationContext();
    }

    public static Context getContext() {
        return ListIt.context;
    }
}
