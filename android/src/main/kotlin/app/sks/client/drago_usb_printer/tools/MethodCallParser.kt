package app.sks.client.drago_usb_printer.tools

import android.hardware.usb.UsbDevice
import io.flutter.plugin.common.MethodCall


/// Author       : liyufeng
/// Date         : 14:27
/// Description  : 
object MethodCallParser {

    fun parseDevice(call: MethodCall): ExUsbDevice? {
        val vendorId = call.argument<Int>("vendorId")
        val productId = call.argument<Int>("productId")
        var usbDevice: ExUsbDevice? = null
        if (vendorId != null && productId != null) {
            val matchedDevice = UsbDeviceHelper.instance.matchUsbDevice(
                vendorId = vendorId,
                productId = productId,
            )
            matchedDevice?.let {
                usbDevice = ExUsbDevice(
                    deviceId = "$vendorId - $productId",
                    usbDevice = it,
                )
            }
        }
        return usbDevice
    }

    fun parseDeviceId(call: MethodCall): String {
        val vendorId = call.argument<Int>("vendorId")
        val productId = call.argument<Int>("productId")
        return "$vendorId - $productId"
    }

}

class ExUsbDevice(
    var deviceId: String,
    var usbDevice: UsbDevice
)