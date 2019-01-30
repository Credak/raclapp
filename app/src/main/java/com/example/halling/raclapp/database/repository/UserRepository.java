package com.example.halling.raclapp.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import com.example.halling.raclapp.database.RaclappDatabase;
import com.example.halling.raclapp.database.dao.UserDAO;
import com.example.halling.raclapp.database.entity.User;

import java.util.Collections;
import java.util.List;

public class UserRepository {

    private MediatorLiveData<List<User>> userMediatorLiveData;
    private RaclappDatabase raclappDatabase;
    public UserRepository(Application application) {
        this.raclappDatabase = RaclappDatabase.getDatabase(application);

        userMediatorLiveData = new MediatorLiveData<>();

        userMediatorLiveData.addSource(raclappDatabase.userDAO().getAllUser(), userList -> {
            if (raclappDatabase.getDatabaseCreated().getValue() != null) {
                userMediatorLiveData.postValue(userList);
            }
        });
     }
    public LiveData<List<User>> getAllUser() {
        return userMediatorLiveData;
    }

    public LiveData<User> getUserName(String username) {
        return raclappDatabase.userDAO().getUsername(username);
    }

    public LiveData<User> getValidLogin(String username, String password) {
        return raclappDatabase.userDAO().getLogin(username, password);
    }

    public void insert (User user) {
        new insertAsyncTask(raclappDatabase.userDAO()).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDAO mAsyncTaskDao;

        insertAsyncTask(UserDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insertAllUser(Collections.singletonList(params[0]));
            return null;
        }
    }
}
