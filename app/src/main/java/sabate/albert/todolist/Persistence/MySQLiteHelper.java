package sabate.albert.todolist.Persistence;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import sabate.albert.todolist.Domain.ToDoList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_TAG = "tag";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";

    private static final String DATABASE_NAME = "tags.db";
    private static final int DATABASE_VERSION = 1;

    /* Database creation sql statement */
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_TAG + "( "
            + COLUMN_ID + " TEXT PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL "
            + ");";

    public MySQLiteHelper() {
        super(ToDoList.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
        onCreate(database);
    }
}
