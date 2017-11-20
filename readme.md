## Add this permission in manifiest file
```
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```
#### Android Gradle dependency 
for permission 
```
compile 'com.android.support:support-v4:23.0.0'
```

##  Add this lines in js(ionic2 or anugular 2)
And here's some code! :+1:

declare var window: any;

```javascript
window.plugins.location.getLatAndLong(function(res){console.log(res)}, function(res){console.log(res)});
```

## ionic cordova for adding plugin
```
ionic cordova plugin add  ionic_pvn_location
```
