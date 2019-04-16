package com.example.subrata.firstapplication.barCodeScanner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.example.subrata.firstapplication.R;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class BarCodeScanner extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    private BarcodeReader barcodeReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code_scanner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize the barcode reader
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);


    }

    @Override
    public void onScanned(Barcode barcode) {
        //single barcode scanned
        barcodeReader.playBeep();
        Log.e("BarCodeReader", "Bar code read");
        Toast.makeText(this, ""+barcode.displayValue, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {
        //multiple barcode scanned
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
        //Barcode scanned for bitmap image
    }

    @Override
    public void onScanError(String errorMessage) {
        //scan error
    }

    @Override
    public void onCameraPermissionDenied() {
        //camera permission denied
        Toast.makeText(this, "Camera Permission Denied!", Toast.LENGTH_SHORT).show();
    }
}
