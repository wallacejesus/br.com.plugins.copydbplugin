/**
 * Phonegap ClipboardManager plugin
 * Omer Saatcioglu 2011
 * Guillaume Charhon - Smart Mobile Software 2011
 * Jacob Robbins - Phonegap 2.0 port 2013
 * Guillaume Charhon - Smart Mobile Software - Phonegap 3.0 port - 2013
 */

var CopyDataBasePlugin = function () {};

CopyDataBasePlugin.prototype.begin = function(str, success, fail) {
    return cordova.exec(success, fail, "CopyDataBasePlugin", "begin", [str.database+".db", str.package_name]);
};

CopyDataBasePlugin.prototype.delete = function(str, success, fail) {
    return cordova.exec(success, fail, "CopyDataBasePlugin", "delete", [str.database+".db", str.package_name]);
};

CopyDataBasePlugin.install = function() {

    if (!window.plugins) {
        window.plugins = {}
    }
    window.plugins.copydatabase = new CopyDataBasePlugin();
    return window.plugins.copydatabase;
};
cordova.addConstructor(CopyDataBasePlugin.install);