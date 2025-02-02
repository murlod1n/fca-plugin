package com.murlodin.fcaplugin.generator

import ai.grazie.utils.capitalize
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.writeText
import com.murlodin.fcaplugin.dialog.FCANotifications
import com.murlodin.fcaplugin.settings.FCASettings
import com.murlodin.fcaplugin.settings.FCASettingsState
import java.io.IOException

class FCAGenerator {

    fun createMainFolder(
        project: Project?,
        folder: VirtualFile,
        featureNameInput: String
    ): Boolean {
        try {

            val featureName = featureNameInput.lowercase()

            val featureFolder = folder.createChildDirectory(this, featureName)

            val fcaSettings = project?.service<FCASettings>()?.state ?: FCASettingsState()


            val dataFolder = featureFolder.createChildDirectory(this, "data")
            dataFolder.createChildDirectory(this, fcaSettings.dataSourcesFolderName ?: FCASettingsState.DATA_SOURCE_FOLDER_NAME)
            val dataRepositoryFolder = dataFolder.createChildDirectory(this, fcaSettings.dataRepositoriesFolderName ?: FCASettingsState.DATA_REPOSITORY_FOLDER_NAME)
            val repositoryImplFile = dataRepositoryFolder.createChildData(this, "${featureName}_repository_impl.dart")
            repositoryImplFile.writeText(Templates.REPOSITORY_IMPL_TEMPLATE.replace("{name}", featureName).replace("{capitalize_name}", featureName.capitalize()))
            if(fcaSettings.isCreateDataModelsTemplates) {
                dataFolder.createChildDirectory(this, fcaSettings.dataModelsFolderName ?:  FCASettingsState.DATA_MODELS_FOLDER_NAME)
            }
            if(fcaSettings.isCreateDataMapperTemplates) {
                val dataMapperFolder = dataFolder.createChildDirectory(this, fcaSettings.dataMappersFolderName ?:  FCASettingsState.DATA_MAPPER_FOLDER_NAME)
                dataMapperFolder.createChildData(this, "${featureName}_dto_mapper.dart")
            }

            val domainFolder = featureFolder.createChildDirectory(this, "domain")
            val domainRepositoryFolder = domainFolder.createChildDirectory(this, fcaSettings.domainRepositoriesFolderName ?:  FCASettingsState.DOMAIN_REPOSITORY_FOLDER_NAME)
            val domainRepositoryFile = domainRepositoryFolder.createChildData(this, "${featureName}_repository.dart")
            domainRepositoryFile.writeText(Templates.REPOSITORY_TEMPLATE.replace("{capitalize_name}", featureName.capitalize()))
            domainFolder.createChildDirectory(this, fcaSettings.domainModelsFolderName ?:  FCASettingsState.DOMAIN_MODELS_FOLDER_NAME)
            domainFolder.createChildDirectory(this, fcaSettings.domainUseCasesFolderName ?: FCASettingsState.DOMAIN_USE_CASE_FOLDER_NAME)

            val presentationFolder = featureFolder.createChildDirectory(this, "presentation")
            if (fcaSettings.isCreatePresentationBlocTemplates) {
                val blocFolder = presentationFolder.createChildDirectory(this, fcaSettings.presentationBlocFolderName ?: FCASettingsState.PRESENTATION_BLOC_FOLDER_NAME)
                val blocFile = blocFolder.createChildData(this, "${featureName}_bloc.dart")
                val blocEventFile = blocFolder.createChildData(this, "${featureName}_event.dart")
                val blocStateFile = blocFolder.createChildData(this, "${featureName}_state.dart")
                blocFile.writeText( Templates.BLOC_TEMPLATE.replace("{name}", featureName).replace("{capitalize_name}", featureName.capitalize()))
                blocEventFile.writeText( Templates.BLOC_EVENT_TEMPLATE.replace("{name}", featureName).replace("{capitalize_name}", featureName.capitalize()))
                blocStateFile.writeText( Templates.BLOC_STATE_TEMPLATE.replace("{name}", featureName).replace("{capitalize_name}", featureName.capitalize()))
            } else {
                presentationFolder.createChildDirectory(this, fcaSettings.presentationStateFolderName ?: FCASettingsState.PRESENTATION_STATE_FOLDER_NAME)
            }

            presentationFolder.createChildDirectory(this, fcaSettings.presentationPagesFolderName ?: FCASettingsState.PRESENTATION_PAGE_FOLDER_NAME)
            if (fcaSettings.isCreatePresentationWidgetsTemplates) {
                presentationFolder.createChildDirectory(this, fcaSettings.presentationWidgetsFolderName ?: FCASettingsState.PRESENTATION_WIDGET_FOLDER_NAME)
            }

            return true
        } catch (e: IOException) {
            FCANotifications.error(project, "Couldn't create ${featureNameInput} directory")
            e.printStackTrace()
            return false
        }
    }

}