var options = {
    enableHighAccuracy: true,
    timeout: 5000,
    maximumAge: 0
};
function success(pos) {
    var crd = pos.coords;

    console.log('Your current position is:');
    console.log('Latitude : ' + crd.latitude);
    console.log('Longitude: ' + crd.longitude);
    console.log('More or less ' + crd.accuracy + ' meters.');

    $jq.get('NearbyTheatres', {
        Latitude: crd.latitude,
        Longitude: crd.longitude,
        Radius: 10
    }).done(function (data) {
        document.getElementById("nearbyTheatres").innerHTML = data;
    });
}
function error(err) {
    console.warn('ERROR(' + err.code + '): ' + err.message);
}
navigator.geolocation.getCurrentPosition(success, error, options);
