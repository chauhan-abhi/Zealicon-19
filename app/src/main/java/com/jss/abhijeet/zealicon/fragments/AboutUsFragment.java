package com.jss.abhijeet.zealicon.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jss.abhijeet.zealicon.R;


public class AboutUsFragment extends Fragment {

    private Context context;
    private ImageView fbImage;
    private ImageView instaImage;
    private ImageView twitterImage;
    private ImageView youtubeImage;

    public static String FACEBOOK_URL = "https://www.facebook.com/zealicon/";
    public static String FACEBOOK_PAGE_ID = "zealicon";

    public static AboutUsFragment newInstance() {
        AboutUsFragment fragment = new AboutUsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getContext();

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        fbImage = view.findViewById(R.id.fb_icon);
        instaImage = view.findViewById(R.id.insta_icon);
        youtubeImage = view.findViewById(R.id.youtube_icon);
        twitterImage = view.findViewById(R.id.twitter_icon);

        fbImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fbUrl = getFacebookPageURL(context);
                Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fbUrl));
                startActivity(fbIntent);
            }
        });

        instaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scheme = "http://instagram.com/_u/zealicon";
                String path = "https://instagram.com/zealicon";
                String nomPackageInfo = "com.instagram.android";
                Intent instaIntent;
                try {
                    getActivity().getPackageManager().getPackageInfo(nomPackageInfo, 0);
                    instaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(scheme));
                } catch (Exception e) {
                    instaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
                }
                getActivity().startActivity(instaIntent);

            }
        });

        youtubeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://https://youtu.be/lJIlIQYAvmc"));
                startActivity(intent);
            }
        });

        twitterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + FACEBOOK_PAGE_ID)));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + FACEBOOK_PAGE_ID)));
                }
            }
        });

        return view;
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }


}
