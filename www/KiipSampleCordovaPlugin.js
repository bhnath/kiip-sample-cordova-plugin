var hnSDK = {
	initSDK: function(success, error) {
		cordova.exec(success, error, 'KiipSampleCordovaPlugin', 'initSDK', []);
	},
	showHackerNews: function(success, error) {
		cordova.exec(success, error, 'KiipSampleCordovaPlugin', 'showHackerNews', []);
	}
}
module.exports =  hnSDK;
window.hnSDK = hnSDK;