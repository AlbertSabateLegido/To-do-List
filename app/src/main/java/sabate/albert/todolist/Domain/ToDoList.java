package sabate.albert.todolist.Domain;

import android.app.Application;
import android.content.Context;

public class ToDoList extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ToDoList.context = getApplicationContext();
    }

    public static Context getContext() {
        return ToDoList.context;
    }
}
