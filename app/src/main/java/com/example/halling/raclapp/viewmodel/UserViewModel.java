package com.example.halling.raclapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import com.example.halling.raclapp.database.entity.User;
import com.example.halling.raclapp.database.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    private MediatorLiveData<List<User>> allUser;


    public UserViewModel(@NonNull Application application) {
        super(application);

        allUser = new MediatorLiveData<>();

        allUser.setValue(null);

        userRepository = new UserRepository(application);
        LiveData<List<User>> userList = userRepository.getAllUser();

        allUser.addSource(userList, allUser::setValue);
    }

    public LiveData<List<User>> getUsers(){
        return userRepository.getAllUser();
    }

    public LiveData<User> getUserName(String username){
        return userRepository.getUserName(username);
    }

    public LiveData<User> getValidLogin(String username, String password){return userRepository.getValidLogin(username, password);}

    public void inserUser(User user){
        userRepository.insert(user);
    }
}
