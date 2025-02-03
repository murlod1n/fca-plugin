package com.murlodin.fcaplugin.settings

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.SimplePersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage


@Service(Service.Level.PROJECT)
@State(
    name = "com.murlodin.fcapluginFCASettings",
    storages = [Storage("FCASettingsPlugin.xml")],
)
class FCASettings :
    SimplePersistentStateComponent<FCASettingsState>(FCASettingsState()) {

    override fun noStateLoaded() {
        loadState(FCASettingsState())
    }

}