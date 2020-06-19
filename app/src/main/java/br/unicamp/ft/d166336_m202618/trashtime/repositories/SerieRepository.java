package br.unicamp.ft.d166336_m202618.trashtime.repositories;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

        import java.util.ArrayList;

        import br.unicamp.ft.d166336_m202618.trashtime.database.SeriesMigration;
        import br.unicamp.ft.d166336_m202618.trashtime.models.Serie;

public class SerieRepository {

    private SeriesMigration databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public SerieRepository(Context context) {

        databaseHelper = new SeriesMigration(context);

        sqLiteDatabase = databaseHelper.getReadableDatabase();
    }

    public void destroy () {

        sqLiteDatabase.close();

        databaseHelper.close();
    }

    public void insertOrChangeData (Serie serie) {

        int id = serie.getId();

        if (id == 0) {
            create(serie);
        } else {
            //update(serie);
        }

    }

    public ArrayList<Serie> loadSeries () {
        String sql = "Select * from series";

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        ArrayList<Serie> series = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(0);

                String name = cursor.getString(1);

                String image = cursor.getString(2);

                float grade = cursor.getFloat(3);

                int tmdb_code = cursor.getInt(4);

                series.add(new Serie(id, tmdb_code, name, image, grade));
            } while (cursor.moveToNext());

            return series;
        }

        return new ArrayList<>();
    }

    private void create (Serie serie) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("name", serie.getName());
        contentValues.put("image", serie.getImage());
        contentValues.put("grade", serie.getGrade());
        contentValues.put("tmdb_code", serie.getTmdb_code());

        sqLiteDatabase.insertOrThrow("series", null, contentValues);
    }

}
