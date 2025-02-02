package com.murlodin.fcaplugin.settings

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.SimplePersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project


@Service(Service.Level.PROJECT)
@State(
    name = "com.murlodin.fcapluginFCASettings",
    storages = [Storage("FCASettingsPlugin.xml")],
)
class FCASettings(internal val project: Project) :
    SimplePersistentStateComponent<FCASettingsState>(FCASettingsState()) {

    override fun noStateLoaded() {
        loadState(FCASettingsState())
    }

}