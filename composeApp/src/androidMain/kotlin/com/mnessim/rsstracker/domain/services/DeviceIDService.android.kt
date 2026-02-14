package com.mnessim.rsstracker.domain.services

import java.util.UUID

actual class DeviceIDService {
    actual fun getDeviceId(): String {
        return UUID.randomUUID().toString()
    }

}