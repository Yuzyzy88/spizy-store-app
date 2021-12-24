package com.example.listreminder_mid;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapterFragment extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public LoginAdapterFragment(FragmentManager fragmentmanager, Context context, int totalTabs) {
        super(fragmentmanager);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                login_fragment login_fragment = new login_fragment();
                return login_fragment;
            case 1:
                SignUp_fragment SignUp_fragment = new SignUp_fragment();
                return SignUp_fragment;
        }
        return null;
    }
}
