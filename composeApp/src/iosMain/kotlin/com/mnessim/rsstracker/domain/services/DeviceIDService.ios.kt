package com.mnessim.rsstracker.domain.services

import platform.Foundation.NSUUID

actual class DeviceIDService {
    actual fun getDeviceId(): String {
        return NSUUID().UUIDString
    }
}