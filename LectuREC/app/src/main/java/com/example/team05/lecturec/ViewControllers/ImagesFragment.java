package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.example.team05.lecturec.CustomExtensions.ImageAdapter;
import com.example.team05.lecturec.DataTypes.Image;
import com.example.team05.lecturec.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ImagesFragment.OnImagesFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ImagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImagesFragment extends Fragment {

    private OnImagesFragmentInteractionListener mListener;

    private FrameLayout fragmentLayout;
    private GridView gridView;


    private ArrayList<Image> images;


    public static ImagesFragment newInstance(String param1, String param2) {
        ImagesFragment fragment = new ImagesFragment();

        return fragment;
    }

    public ImagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle passedBundle = getArguments();
        images = (ArrayList<Image>)passedBundle.get("imageList");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentLayout = (FrameLayout) inflater.inflate(
                R.layout.fragment_images, container, false);

        gridView = (GridView) fragmentLayout.findViewById(R.id.imageGridView);

        ImageAdapter imageAdapater = new ImageAdapter(getActivity().getApplicationContext(), R.layout.gridview_item, images);



        gridView.setAdapter(imageAdapater);
        gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        gridView.setMultiChoiceModeListener(new MultiChoiceModeListener());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // Send intent to SingleViewActivity
                Intent i = new Intent((getActivity().getApplicationContext()), SingleViewActivity.class);
                // Pass image index
                i.putExtra("imageFilePath", images.get(position).getFile());
                startActivity(i);
            }
        });

        return fragmentLayout;



    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnImagesFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //Checking if item is checked
    public class CheckableLayout extends FrameLayout implements Checkable {
        private boolean mChecked;

        public CheckableLayout(Context context) {
            super(context);
        }

        @SuppressWarnings("deprecation")
        public void setChecked(boolean checked) {
            mChecked = checked;
            setBackgroundDrawable(checked ? getResources().getDrawable(
                    R.drawable.gradient) : null);
        }

        public boolean isChecked() {
            return mChecked;
        }

        public void toggle() {
            setChecked(!mChecked);
        }

    }

    public class MultiChoiceModeListener implements
            GridView.MultiChoiceModeListener {
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Select Items");
            mode.setSubtitle("One item selected");
            mode.getMenuInflater().inflate(R.menu.menu_selectedsession, menu);

            return true;
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return true;
        }

        public void onDestroyActionMode(ActionMode mode) {
        }

        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            int selectCount = gridView.getCheckedItemCount();
            switch (selectCount) {
                case 1:
                    mode.setSubtitle("One item selected");
                    break;
                default:
                    mode.setSubtitle("" + selectCount + " items selected");
                    break;
            }
        }

    }

    public interface OnImagesFragmentInteractionListener {
        // TODO: Update argument type and name
        public void OnImagesFragmentInteractionListener(Uri uri);
    }

}
