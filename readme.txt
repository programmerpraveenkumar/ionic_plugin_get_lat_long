add this permission in manifiest file
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

declare var window: any;


window.plugins.location.getLatAndLong(function(res){console.log(res)}, function(res){console.log(res)});