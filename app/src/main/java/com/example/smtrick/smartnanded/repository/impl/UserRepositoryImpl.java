package com.example.smtrick.smartnanded.repository.impl;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.example.smtrick.smartnanded.Exception.ExceptionUtil;
import com.example.smtrick.smartnanded.Models.User;
import com.example.smtrick.smartnanded.callback.CallBack;
import com.example.smtrick.smartnanded.constants.Constant;
import com.example.smtrick.smartnanded.repository.FirebaseTemplateRepository;
import com.example.smtrick.smartnanded.repository.UserRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static com.example.smtrick.smartnanded.constants.Constant.EMAIL_POSTFIX;


public class UserRepositoryImpl extends FirebaseTemplateRepository implements UserRepository {
    private Activity _activity;

    public UserRepositoryImpl(final Activity activity) {
        _activity = activity;
    }

    public UserRepositoryImpl() {

    }

    @Override
    public void signIn(final String mobileNumber, final String password, final CallBack callback) {
        Constant.AUTH.signInWithEmailAndPassword(mobileNumber + EMAIL_POSTFIX, password)
                .addOnCompleteListener(_activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callback.onSuccess(task);
                        } else {
                            callback.onError(task);
                        }
                    }
                });
    }


    @Override
    public void readUserByUserId(String userId, final CallBack callBack) {
        final Query query = Constant.USER_TABLE_REF.child(userId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    if (dataSnapshot.getValue() != null) {
                        try {
                            if (dataSnapshot.hasChildren()) {
                                callBack.onSuccess(dataSnapshot.getValue(User.class));
                            } else {
                                callBack.onError(null);
                            }
                        } catch (Exception e) {
                            ExceptionUtil.logException(e);
                        }
                    } else
                        callBack.onError(null);
                } else
                    callBack.onError(null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callBack.onError(databaseError);
            }
        });
    }

    @Override
    public void createUser(final User userModel, final CallBack callback) {
        Constant.AUTH.createUserWithEmailAndPassword(userModel.getMobileNumber() + EMAIL_POSTFIX, userModel.getPassword())
                .addOnCompleteListener(_activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final FirebaseUser firebaseUser = Constant.AUTH.getCurrentUser();
                            if (firebaseUser == null) {
                                callback.onError(task);
                                return;
                            }
                            userModel.setUserId(firebaseUser.getUid());
                            //userModel.setPassword(null);
                            // FireBase Create User
                            Map userMap = userModel.toMap();
                            // userMap.put("imeiNumber", REGISTRATION_CONSTANT);
                            createUserData(userModel, callback);
                        } else {
                            callback.onError(task);
                        }
                    }
                });
    }

    @Override
    public void createUserData(User user, final CallBack callBack) {
        DatabaseReference databaseReference = Constant.USER_TABLE_REF.child(user.getUserId());
        fireBaseCreate(databaseReference, user, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                callBack.onSuccess(object);
            }

            @Override
            public void onError(Object object) {
                callBack.onError(object);
            }
        });
    }

}