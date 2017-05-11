package com.lombrinus.apps.googlecseimageexample;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by antonio.hormigo on 24/1/17.
 */

public class ImageDialogFragment extends DialogFragment {

    private String urlimage;

    public static ImageDialogFragment newInstance(String url) {

        ImageDialogFragment fragment = new ImageDialogFragment();
        fragment.setUrlimage(url);
        return fragment;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_image, container, false);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Picasso.with(getActivity()).load(urlimage).resize(width, 0).into((ImageView) rootView.findViewById(R.id.imageView));

        return rootView;
    }

}