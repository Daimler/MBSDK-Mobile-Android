package com.daimler.mbcarkit.business.model.vehicle

enum class HybridWarningState {
    NONE,
    SEEK_SERVICE_WITHOUT_ENGINGE_STOP,
    HIGH_VOLTAGE_POWERNET_FAULT,
    POWER_TRAIN_FAULT,
    STARTER_BATTERY,
    STOP_VEHICLE_CHARGE_BATTERY,
    PLUGIN_ONLY_EMODE_POSSIBLE,
    PLUGIN_VEHICLE_STILL_ACTIVE,
    POWER_REDUCE,
    STOP_ENGINE_OFF,
    UNKNOWN
}
