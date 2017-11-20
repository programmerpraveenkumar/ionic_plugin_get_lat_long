package com.praveen.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.ActivityCompat;
import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by praveen on 16-Nov-17.
 */

public class getLocationValues implements LocationListener {
  private Context context = null;
  boolean checkGPS = false;
  boolean checkNetwork = false;
  public  boolean canGetLocation = false;
  String location_values = "";
  String error_status = "";
  Location lastLocation;
  public  double latitude;
  public  double longitude;
  protected LocationManager locationManager;

  public getLocationValues(Context c) {
    this.context = c;
    getLocationValues();
  }
  public String getLocationStatus(){
    return this.location_values;
  }

  void getLocationValues() {
    locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
    // getting network status
    checkNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    // getting GPS status
    checkGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    this.getLatAndLang(LocationManager.NETWORK_PROVIDER);
    if(this.canGetLocation == false){
      //this.getLatAndLang(LocationManager.GPS_PROVIDER);
      location_values = "Network lat and lang failed ";
      //location_values = "GPS lat and lang "+this.latitude+" "+this.longitude;
      Log.i("praveen",location_values );
    }else{
      location_values = "network lat and lang "+this.latitude+" "+this.longitude;
      Log.i("praveen",location_values);
    }
  }

  void getLatAndLang(String provider) {
    if(this.getPermission()){
      try{

        if(locationManager != null){
          this.canGetLocation = true;
          lastLocation = locationManager.getLastKnownLocation(provider);
          if(lastLocation !=null){
            latitude = lastLocation.getLatitude();
            longitude = lastLocation.getLongitude();
          }
          else{
            locationManager.requestLocationUpdates(provider, 0, 0, this);
            //LocationRequest.create();
            latitude = lastLocation.getLatitude();
            longitude = lastLocation.getLongitude();
          }
          if(latitude == 0.0 || longitude == 0.0){
            this.canGetLocation = false;
            error_status = "Error lat and land is null 0.0 0.0";
          }
        }else{
          this.canGetLocation = false;
          error_status = "Error locationManager is null";
        }

      }catch (SecurityException e){
        this.canGetLocation = false;
        error_status = "Error (security Exception)"+e.getMessage();
      }catch (Exception e) {
        this.canGetLocation = false;
        error_status = "Error (Main Exception)"+e.getMessage();
      }
    }

  }



  @Override
  public void onLocationChanged(Location location) {

  }

  @Override
  public void onStatusChanged(String s, int i, Bundle bundle) {

  }

  @Override
  public void onProviderEnabled(String s) {

  }

  @Override
  public void onProviderDisabled(String s) {

  }
  private Boolean getPermission(){
    if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      // TODO: Consider calling
      //    ActivityCompat#requestPermissions
      // here to request the missing permissions, and then overriding
      //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
      //                                          int[] grantResults)
      // to handle the case where the user grants the permission. See the documentation
      // for ActivityCompat#requestPermissions for more details.
      return false;
    }
    return true;
  }
}
