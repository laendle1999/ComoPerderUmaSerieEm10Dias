package br.unicamp.ft.d166336_m202618.trashtime.repositories;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.util.Log;

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

    public int insertOrChangeData (Serie serie) {

        int id = serie.getId();

        if (id == 0) {
            return create(serie);
        } else {
            return update(serie);
        }
    }

    public void removeData (int id) {

        String whereClause = "id = ?";
        String[] whereArgs = new String[] {Integer.toString(id)};

        sqLiteDatabase.delete("series", whereClause, whereArgs);
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

    public ArrayList<Serie> seriesWithNextEp () {
        String sql = "Select * from series where next_ep IS NOT NULL";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        ArrayList<Serie> series = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(0);

                String name = cursor.getString(1);

                String image = cursor.getString(2);

                float grade = cursor.getFloat(3);

                int tmdb_code = cursor.getInt(4);

                String date = cursor.getString(5);

                Serie serie =  new Serie(id, tmdb_code, name, image, grade);
                
                serie.setDate(date, "yyyy-MM-dd");

                series.add(serie);
            } while (cursor.moveToNext());

            return series;
        }

        return new ArrayList<>();
    }

    public Serie find(int id) {
        String sql = "Select * from series where id = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{id + ""});

        if (cursor.moveToFirst()) {

            String name = cursor.getString(1);

            String image = cursor.getString(2);

            float grade = cursor.getFloat(3);

            int tmdb_code = cursor.getInt(4);

            String date = cursor.getString(5);

            Serie serie =  new Serie(id, tmdb_code, name, image, grade);

            serie.setDate(date, "yyyy-MM-dd");

            return serie;
        }

        return null;
    }

    public Serie findByCode(int code) {
        String sql = "Select * from series where tmdb_code = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{code + ""});

        if (cursor.moveToFirst()) {

            int id = cursor.getInt(0);

            String name = cursor.getString(1);

            String image = cursor.getString(2);

            float grade = cursor.getFloat(3);

            int tmdb_code = cursor.getInt(4);

            String date = cursor.getString(5);

            Serie serie =  new Serie(id, tmdb_code, name, image, grade);

            serie.setDate(date, "yyyy-MM-dd");

            return serie;
        }

        return null;
    }

    private int create (Serie serie) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("name", serie.getName());
        contentValues.put("image", serie.getImage());
        contentValues.put("grade", serie.getGrade());
        contentValues.put("tmdb_code", serie.getTmdb_code());

        if (serie.getDate() != null) {
            contentValues.put("next_ep", serie.getFormattedDate("yyyy-MM-dd"));
        }


        return (int)sqLiteDatabase.insertOrThrow("series", null, contentValues);
    }

    private int update (Serie serie) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("name", serie.getName());
        contentValues.put("image", serie.getImage());
        contentValues.put("grade", serie.getGrade());
        contentValues.put("tmdb_code", serie.getTmdb_code());

        if (serie.getDate() != null) {
            contentValues.put("next_ep", serie.getFormattedDate("yyyy-MM-dd"));
        } else {
            contentValues.put("next_ep", (byte[]) null);
        }

        String whereClause = "id = ?";
        String[] whereArgs = new String[] {Integer.toString(serie.getId())};

        return sqLiteDatabase.update("series", contentValues, whereClause, whereArgs);
    }

}
