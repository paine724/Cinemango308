<%-- 
    Document   : map
    Created on : Feb 27, 2015, 7:21:08 PM
    Author     : Andrew
--%>

<jsp:include page="header.jsp"></jsp:include>
<link href="bootstrap/css/global-bundle.min.css" rel="stylesheet">
<div class="container">
    <h2>Map</h2>
<div id="wrapper" class="small-12 large-5 columns">
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <article>

    </article>
<script>
function success(position) {
  var mapcanvas = document.createElement('div');
  mapcanvas.id = 'mapcontainer';
  mapcanvas.style.height = '400px';
  mapcanvas.style.width = '600px';

  document.querySelector('article').appendChild(mapcanvas);

  var coords = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
  
  var options = {
    zoom: 15,
    center: coords,
    mapTypeControl: false,
    navigationControlOptions: {
    	style: google.maps.NavigationControlStyle.SMALL
    },
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  var map = new google.maps.Map(document.getElementById("mapcontainer"), options);

  var marker = new google.maps.Marker({
      position: coords,
      map: map,
      title:"You are here!"
  });
}

if (navigator.geolocation) {
  navigator.geolocation.getCurrentPosition(success);
} else {
  error('Geo Location is not supported');
}

</script>
</div>
    <div class="small-12 large-5 columns">
        <form method="get">
            <label>Your Location:<input type="text" name="origin"><br>
            <label>Destination: <input type="hidden" name="destination" value="11790">Stonybrook<br>
            <input type="submit">
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>