package hu.ait.mapsdemo

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MyLocationProvider.OnNewLocationAvailable {


    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        btnSatellite.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
        btnNormal.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }

        requestNeededPermission()
    }

    private fun requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this,
                    "I need it for location", Toast.LENGTH_SHORT).show()
            }

            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101)
        } else {
            startLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            101 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "ACCESS_FINE_LOCATION perm granted", Toast.LENGTH_SHORT).show()

                    startLocation()
                } else {
                    Toast.makeText(this, "ACCESS_FINE_LOCATION perm NOT granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }





    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.isTrafficEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true

        // Add a marker in Sydney and move the camera
        val hungary = LatLng(47.0, 19.0)
        mMap.addMarker(MarkerOptions().position(hungary).title("Marker in Hungary"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hungary))


        mMap.setOnMapClickListener {
            var newMarker = mMap.addMarker(
                MarkerOptions()
                    .position(it)
                    .title("My marker")
                    .snippet("long text of the marker")
            )

            newMarker.isDraggable = true

            //mMap.animateCamera(CameraUpdateFactory.newLatLng(it))

            val random = Random(System.currentTimeMillis())
            val cameraPostion = CameraPosition.Builder()
                .target(it)
                .zoom(10f+random.nextInt(5))
                .tilt(30f+random.nextInt(15))
                .bearing(45f+random.nextInt(45))
                .build()

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPostion))
        }
    }

    private lateinit var myLocationProvider: MyLocationProvider

    fun startLocation() {
        myLocationProvider = MyLocationProvider(this,
            this)
        myLocationProvider.startLocationMonitoring()
    }


    override fun onStop() {
        super.onStop()
        myLocationProvider.stopLocationMonitoring()
    }

    override fun onNewLocation(location: Location) {
        tvLocation.text = "${location.latitude}, ${location.longitude}"
    }

}
