package hu.aut.android.kotlinbeacondemo

import android.Manifest
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.RemoteException
import android.text.TextUtils
import com.livinglifetechway.quickpermissions.annotations.WithPermissions
import kotlinx.android.synthetic.main.activity_main.*
import org.altbeacon.beacon.*

class MainActivity : AppCompatActivity(), BeaconConsumer {

    private lateinit var beaconManager: BeaconManager

    companion object {
        const val RUUVI_LAYOUT = "m:0-2=0499,i:4-19,i:20-21,i:22-23,p:24-24" // TBD
        const val IBEACON_LAYOUT = "m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"
        const val ALTBEACON_LAYOUT = BeaconParser.ALTBEACON_LAYOUT
        const val EDDYSTONE_UID_LAYOUT = BeaconParser.EDDYSTONE_UID_LAYOUT
        const val EDDYSTONE_URL_LAYOUT = BeaconParser.EDDYSTONE_URL_LAYOUT
        const val EDDYSTONE_TLM_LAYOUT = BeaconParser.EDDYSTONE_TLM_LAYOUT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beaconManager = BeaconManager.getInstanceForApplication(this)

        // Add all the beacon types we want to discover
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout(IBEACON_LAYOUT))
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout(EDDYSTONE_UID_LAYOUT))
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout(EDDYSTONE_URL_LAYOUT))
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout(EDDYSTONE_TLM_LAYOUT))
        beaconManager.bind(this)

        btnScan.setOnClickListener {
            startMonitoringBeacons()
        }
    }


    @WithPermissions(
        permissions = [Manifest.permission.ACCESS_COARSE_LOCATION]
    )
    private fun startMonitoringBeacons() {
        beaconManager.bind(this)
    }


    override fun onBeaconServiceConnect() {

        beaconManager.addRangeNotifier { beacons, region ->
            var sumText = ""
            for (beacon in beacons) {
                if (beacon.bluetoothAddress == "CC:F3:37:1D:6A:C9") {
                    tvRestaurantDistance.text = "${beacon.distance} m"

                    if (beacon.distance<1) {
                        containerRestaurant.setBackgroundColor(Color.GREEN)
                    } else {
                        containerRestaurant.setBackgroundColor(Color.RED)
                    }
                } else if (beacon.bluetoothAddress == "CF:C6:B0:98:8F:7C") {
                    tvLibraryDistance.text = "${beacon.distance} m"

                    if (beacon.distance<1) {
                        containerLibrary.setBackgroundColor(Color.GREEN)
                    } else {
                        containerLibrary.setBackgroundColor(Color.RED)
                    }
                }

                sumText += "Addr: ${beacon.bluetoothAddress} Name: ${beacon.bluetoothName} Distance: ${beacon.distance}"
                sumText += "\n"
            }


            if (!TextUtils.isEmpty(sumText)) {
                tvStatus.text = sumText
            }
        }

        beaconManager.addMonitorNotifier(object : MonitorNotifier {
            override fun didEnterRegion(region: Region) {
                tvStatus.text = "didEnterRegion ${region.bluetoothAddress} ${region.uniqueId}"
            }

            override fun didExitRegion(region: Region) {
                tvStatus.text = "didExitRegion ${region.bluetoothAddress} ${region.uniqueId}"
            }

            override fun didDetermineStateForRegion(state: Int, region: Region) {
                tvStatus.text =
                        "state switch: $state ${region.bluetoothAddress} ${region.uniqueId}"
            }
        })

        try {
            //beaconManager.startMonitoringBeaconsInRegion(Region("f7826da6-4fa2-4e98-8024-bc5b71e0893e", null, null, null))
            beaconManager.startMonitoringBeaconsInRegion(Region("myRangingUniqueId", null, null, null))
            beaconManager.startRangingBeaconsInRegion(Region("myRangingUniqueId", null, null, null))
        } catch (e: Exception) {
            tvStatus.text = e.message
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beaconManager.unbind(this)
    }
}
