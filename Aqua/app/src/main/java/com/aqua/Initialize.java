package com.aqua;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import com.aqua.data.oauth.OAuthUtil;

public class Initialize{
    public static void initializeOAuth(String authorize, String token, String clientId, String clientSecret, String redirect){
        OAuthUtil.initialize(authorize, token, clientId, clientSecret, redirect);
    }
    public static void getLocalUser (Context context){
        AccountManager am = AccountManager.get(context); // "this" references the current Context

        Account[] accounts = am.getAccountsByType("com.google");
    }
}
