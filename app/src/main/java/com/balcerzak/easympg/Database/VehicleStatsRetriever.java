package com.balcerzak.easympg.Database;

import android.content.Context;

public class VehicleStatsRetriever {

    private final FillUpAdmin _fillUpAdmin;

    public VehicleStatsRetriever(Context context){
        _fillUpAdmin = new FillUpAdmin(context);
    }

    public double getTotalCost(int vehiclePK){
        double totalCost = 0.0;
        totalCost += getTotalFillUpCost(vehiclePK);
       //TODO totalCost += _maintenanceAdmin.getTotalCostForVehicle(vehiclePK);
        return totalCost;
    }

    public double getTotalFillUpCost(int vehiclePk){
        return  _fillUpAdmin.getTotalCostForVehicle(vehiclePk);
    }

    public int getFillCount(int vehiclePk){
        return _fillUpAdmin.getFillUpCount(vehiclePk);
    }

    public double getAverageFillPrice(int vehiclePk){
        return _fillUpAdmin.getAverageFillPrice(vehiclePk);
    }

}
