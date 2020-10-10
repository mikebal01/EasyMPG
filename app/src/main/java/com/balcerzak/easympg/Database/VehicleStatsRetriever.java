package com.balcerzak.easympg.Database;

import android.content.Context;

import com.balcerzak.easympg.Fillup.FillUpInfoStruct;

import java.util.ArrayList;

public class VehicleStatsRetriever {

    private final FillUpAdmin _fillUpAdmin;
    private final int _vehiclePk;
    private double _bestMPG;
    private double _worstMPG;
    private double _averageMPG;

    public VehicleStatsRetriever(Context context, int vehiclePk){
        _fillUpAdmin = new FillUpAdmin(context);
        _vehiclePk = vehiclePk;
    }

    public double getBestMPG(){
        return _bestMPG;
    }

    public double getWorstMPG(){
        return _worstMPG;
    }

    public double getAverageMPG(){
        return _averageMPG;
    }
    public double getTotalCost(){
        double totalCost = 0.0;
        totalCost += getTotalFillUpCost();
       //TODO totalCost += _maintenanceAdmin.getTotalCostForVehicle(vehiclePK);
        return totalCost;
    }

    public double getTotalFillUpCost(){
        return  _fillUpAdmin.getTotalCostForVehicle(_vehiclePk);
    }

    public int getFillCount(){
        return _fillUpAdmin.getFillUpCount(_vehiclePk);
    }

    public double getAverageFillPrice(){
        return _fillUpAdmin.getAverageFillPrice(_vehiclePk);
    }

    public void calculateFuelStatsForVehicle(){
        ArrayList<FillUpInfoStruct> fillUpsForVehicle = _fillUpAdmin.getFillUpsForVehicle(_vehiclePk);
        int totalDistance = 0;
        double totalUnits = 0.0;
        for(int i = 0; i + 1 < fillUpsForVehicle.size(); i++){
            if(!fillUpsForVehicle.get(i).isPartialFillUp() || !fillUpsForVehicle.get(i).isMissedPreviousFillUp()) {
                double currentTotalDistance = fillUpsForVehicle.get(i).getOdometer() - fillUpsForVehicle.get(i + 1).getOdometer();
                double currentTotalUnits = fillUpsForVehicle.get(i).getUnitsInDefault();
                totalDistance += currentTotalDistance;
                totalUnits += currentTotalUnits;

                double currentMPG = currentTotalDistance/currentTotalUnits;
                if(currentMPG > _bestMPG){
                    _bestMPG = currentMPG;
                } if(_worstMPG == 0.0 || currentMPG < _worstMPG){
                    _worstMPG = currentMPG;
                }
            }
        }
        _averageMPG = totalDistance/totalUnits;
    }

}
