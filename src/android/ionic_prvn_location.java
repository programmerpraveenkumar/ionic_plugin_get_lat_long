package com.praveen.location;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
/**
 * This class echoes a string called from JavaScript.
 */
public class ionic_prvn_location extends CordovaPlugin {
private Context context= null;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        context=this.cordova.getActivity().getApplicationContext();
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }else if("get_location_values".equals(action)){
          JSONObject loc_res = new JSONObject();
          getLocationValues loc_val = new getLocationValues(context);
          if(loc_val.canGetLocation){
            loc_res.put("status","1");
            loc_res.put("message",loc_val.getLocationStatus());
            loc_res.put("lat",""+loc_val.latitude);
            loc_res.put("lang",""+loc_val.longitude);
            callbackContext.success(loc_res);
          }else{
            loc_res.put("status","0");
            loc_res.put("message","Error while getting location");
            loc_res.put("full_message","Error "+loc_val.getLocationStatus());
            callbackContext.error(loc_res);
          }
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    
}
