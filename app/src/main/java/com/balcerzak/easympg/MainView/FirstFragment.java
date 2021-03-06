package com.balcerzak.easympg.MainView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.balcerzak.easympg.Database.VehicleStatsRetriever;
import com.balcerzak.easympg.R;

import java.util.Objects;

public class FirstFragment extends Fragment{

    private int _vehicleID;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(_vehicleID != -1){
            populateUI();
        }

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

    }

    private void populateUI(){
        VehicleStatsRetriever vehicleStatsRetriever = new VehicleStatsRetriever(getContext(), _vehicleID);
        TextView totalCost = Objects.requireNonNull(getView()).findViewById(R.id.textViewCostVariable);
        totalCost.setText(String.valueOf(vehicleStatsRetriever.getTotalCost()));
        TextView fillCount = getView().findViewById(R.id.textViewFillCount);
        fillCount.setText(String.valueOf(vehicleStatsRetriever.getFillCount()));
        TextView averageCost = getView().findViewById(R.id.textViewAverageCost);
        averageCost.setText(String.valueOf(vehicleStatsRetriever.getAverageFillPrice()));
        TextView averageMPG = getView().findViewById(R.id.textViewAverageMPG);
        vehicleStatsRetriever.calculateFuelStatsForVehicle();
        averageMPG.setText(String.valueOf(vehicleStatsRetriever.getAverageMPG()));
        TextView bestMPG = getView().findViewById(R.id.textViewBestMPG);
        bestMPG.setText(String.valueOf(vehicleStatsRetriever.getBestMPG()));
        TextView worstMPG = getView().findViewById(R.id.textViewWorstMPG);
        worstMPG.setText(String.valueOf(vehicleStatsRetriever.getWorstMPG()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;
        _vehicleID = activity.getVehiclePk();
    }
}