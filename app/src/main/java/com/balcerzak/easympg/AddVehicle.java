package com.balcerzak.easympg;

import android.view.View;

public class AddVehicle extends VehicleBaseActivity{

    public void saveVehicleClicked(View v) {
        String  newAlbumID;
/*        if (hasRequiredFields()) {
            Records newRecord = new Records();
            newRecord.set_albumname(albumName.getText().toString());

            if (!albumYear.getText().toString().equals(""))
                newRecord.set_releaseyear(albumYear.getText().toString());
            else
                newRecord.set_releaseyear("");
            newRecord.set_bandname(albumBand.getText().toString());
            newRecord.set_genre(genres);

            if(albumCover != null)
                newRecord.set_hasimage("true");
            else
                newRecord.set_hasimage("false");

            if(!notes.getText().toString().equals(""))
                newRecord.set_notes(notes.getText().toString());
            else
                newRecord.set_notes("");

            newRecord.set_size(_recordSize.getSelectedItem().toString());

            MyDBHandler dbHandler = new MyDBHandler(getApplicationContext(), null, null, 1);
            if(editCall.equals("-1")) {
                newAlbumID = dbHandler.addRecord(newRecord, dbTableReferenced , dbTableReferenced + "genres", false);
            }
            else{
                newAlbumID = editCall;
                newRecord.set_id(editCall);
                dbHandler.updateRecord(newRecord, dbTableReferenced);
            }
            if(albumCover != null) {
                ImageManager imageManager = new ImageManager();
                imageManager.saveImageToFile(albumCover, dbTableReferenced + newAlbumID);
            }*/
            finish();
        }
}

