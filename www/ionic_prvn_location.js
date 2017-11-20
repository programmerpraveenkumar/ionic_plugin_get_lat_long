//var exec = require('cordova/exec');
function prvn_location(){

}

prvn_location.prototype.getLatAndLong= function (successCallback, errorCallback) {
  console.log("test callig main method")
  cordova.exec(successCallback, errorCallback, "ionic_prvn_location", "get_location_values", []);
};


prvn_location.install = function () {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.prvn_location = new prvn_location();
  return window.plugins.prvn_location;
};
cordova.addConstructor(prvn_location.install);
