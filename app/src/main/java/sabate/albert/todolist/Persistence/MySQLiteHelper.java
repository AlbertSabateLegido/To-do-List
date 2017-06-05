package sabate.albert.todolist.Persistence;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sabate.albert.todolist.Domain.ToDoList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_TAG = "tag";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CREATION_DATE = "date";
    public static final String COLUMN_DONE = "done";

    private static final String DATABASE_NAME = "tags.db";
    private static final int DATABASE_VERSION = 2;

    /* Database creation sql statement */
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_TAG + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_CREATION_DATE + " TEXT NOT NULL, "
            + COLUMN_DONE + " BOOLEAN NOT NULL "
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

    public String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public Date stringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }
}
