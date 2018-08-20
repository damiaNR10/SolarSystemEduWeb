package com.example.damia.solarsystemeduweb;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class SolarObjectsFragment extends Fragment implements SolarObjectsAdapter.SolarObjectClickedListener {


    public static final String OBJECTS_KEY = "objects";
    @BindView(R.id.objectsRecyclerView)
    RecyclerView objectsRecyclerView;
    Unbinder unbinder;

    public SolarObjectsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_solar_objects, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SolarObject[] objects = (SolarObject[]) getArguments().getSerializable(OBJECTS_KEY);

        objectsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        SolarObjectsAdapter adapter = new SolarObjectsAdapter(objects);
        adapter.setSolarObjectClickedListener(this);
        objectsRecyclerView.setAdapter(adapter);

    }


    public static SolarObjectsFragment newInstance(SolarObject[] objects) {
        SolarObjectsFragment fragment = new SolarObjectsFragment();
        Bundle args = new Bundle();
        args.putSerializable(OBJECTS_KEY, objects);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
    }

    @Override
    public void solarObjectClicked(SolarObject solarObject) {
        Log.d(SolarObjectsFragment.class.getSimpleName(), "Clicked: " + solarObject.getName());

        SolarObjectActivity.start(getActivity(), solarObject);
    }
}
