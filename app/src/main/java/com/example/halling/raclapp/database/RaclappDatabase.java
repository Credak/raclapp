package com.example.halling.raclapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.example.halling.raclapp.database.dao.UserDAO;
import com.example.halling.raclapp.database.entity.User;

import java.util.List;

@Database(entities = {User.class}, version = 2)
public abstract class RaclappDatabase extends RoomDatabase {
    @VisibleForTesting
    private static final String DATABASE_NAME = "raclapp-database";
    private static volatile RaclappDatabase INSTANCE;
    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    public static RaclappDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RaclappDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RaclappDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback).build();
                    INSTANCE.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDAO userDAO;

        PopulateDbAsync(RaclappDatabase db) {
            userDAO = db.userDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            userDAO.deleteAllUser();
            userDAO.insertAllUser(DataGenerator.generateUser());
            return null;
        }
    }
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            this.setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        isDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return isDatabaseCreated;
    }

    public abstract UserDAO userDAO();
}
