package sabate.albert.listit.Persistence;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import sabate.albert.listit.Domain.ListIt;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_LIST_OBJECTS = "tag";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DONE = "done";

    private static final String DATABASE_NAME = "tags.db";
    private static final int DATABASE_VERSION = 2;

    /* Database creation sql statement */
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_LIST_OBJECTS + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_DONE + " BOOLEAN NOT NULL "
            + ");";

    public MySQLiteHelper() {
        super(ListIt.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST_OBJECTS);
        onCreate(database);
    }
}
