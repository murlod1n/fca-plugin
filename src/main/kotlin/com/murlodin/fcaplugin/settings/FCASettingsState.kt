package com.murlodin.fcaplugin.settings

import com.intellij.openapi.components.BaseState

class FCASettingsState : BaseState() {

    var isCreatePresentationBlocTemplates by property(IS_CREATE_PRESENTATION_BLOC_FOLDER)
    var isCreateDataMapperTemplates by property(IS_CREATE_DATA_MAPPER_FOLDER)
    var isCreateDataModelsTemplates by property(IS_CREATE_DATA_MODELS_FOLDER)
    var isCreatePresentationWidgetsTemplates by property(IS_CREATE_PRESENTATION_WIDGET_FOLDER)

    var dataRepositoriesFolderName by string(DATA_REPOSITORY_FOLDER_NAME)
    var dataSourcesFolderName by string(DATA_SOURCE_FOLDER_NAME)
    var dataMappersFolderName by string(DATA_MAPPER_FOLDER_NAME)
    var dataModelsFolderName by string(DATA_MODELS_FOLDER_NAME)

    var domainRepositoriesFolderName by string(DOMAIN_REPOSITORY_FOLDER_NAME)
    var domainModelsFolderName by string(DOMAIN_MODELS_FOLDER_NAME)
    var domainUseCasesFolderName by string(DOMAIN_USE_CASE_FOLDER_NAME)

    var presentationBlocFolderName by string(PRESENTATION_BLOC_FOLDER_NAME)
    var presentationStateFolderName by string(PRESENTATION_STATE_FOLDER_NAME)
    var presentationPagesFolderName by string(PRESENTATION_PAGE_FOLDER_NAME)
    var presentationWidgetsFolderName by string(PRESENTATION_WIDGET_FOLDER_NAME)

    companion object DefaultFCASettingsProperties {
        const val IS_CREATE_DATA_MAPPER_FOLDER = false
        const val IS_CREATE_DATA_MODELS_FOLDER = false
        const val DATA_MODELS_FOLDER_NAME = "models"
        const val DATA_REPOSITORY_FOLDER_NAME = "repositories"
        const val DATA_SOURCE_FOLDER_NAME = "data_sources"
        const val DATA_MAPPER_FOLDER_NAME = "mapper"

        const val DOMAIN_MODELS_FOLDER_NAME = "entities"
        const val DOMAIN_REPOSITORY_FOLDER_NAME = "repositories"
        const val DOMAIN_USE_CASE_FOLDER_NAME = "use_cases"

        const val IS_CREATE_PRESENTATION_BLOC_FOLDER = false
        const val IS_CREATE_PRESENTATION_WIDGET_FOLDER = true
        const val PRESENTATION_BLOC_FOLDER_NAME = "bloc"
        const val PRESENTATION_STATE_FOLDER_NAME = "state"
        const val PRESENTATION_PAGE_FOLDER_NAME = "pages"
        const val PRESENTATION_WIDGET_FOLDER_NAME = "widget"
    }
}

