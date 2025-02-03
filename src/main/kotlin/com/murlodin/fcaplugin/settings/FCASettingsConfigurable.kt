package com.murlodin.fcaplugin.settings

import com.intellij.openapi.components.service
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.*
import com.intellij.ui.layout.ValidationInfoBuilder

internal class FCASettingsConfigurable(project: Project) :
    BoundConfigurable(displayName = "FCASettings") {

    private val fcaSettings = project.service<FCASettings>()

    private lateinit var dataSourcesFolderNameTextField: Cell<JBTextField>
    private lateinit var dataMappersFolderNameTextField: Cell<JBTextField>
    private lateinit var dataModelsFolderNameTextField: Cell<JBTextField>
    private lateinit var dataRepositoriesFolderNameTextField: Cell<JBTextField>

    private lateinit var domainModelsFolderNameTextField: Cell<JBTextField>
    private lateinit var domainUseCasesFolderNameTextField: Cell<JBTextField>
    private lateinit var domainRepositoriesFolderNameTextField: Cell<JBTextField>

    private lateinit var presentationsBlocFolderNameTextField: Cell<JBTextField>
    private lateinit var presentationsStateFolderNameTextField: Cell<JBTextField>
    private lateinit var presentationsPagesFolderNameTextField: Cell<JBTextField>
    private lateinit var presentationsWidgetsFolderNameTextField: Cell<JBTextField>

    override fun createPanel(): DialogPanel {

        val textValidator: ValidationInfoBuilder.(JBTextField) -> ValidationInfo? = { textField ->
            if (textField.text.isNullOrBlank()) {
                error("Поле не может быть пустым")
            } else {
                null
            }
        }

        return panel {
            group("FCA Settings") {
                row {
                    button("Default Settings") {

                        dataSourcesFolderNameTextField.text(FCASettingsState.DATA_SOURCE_FOLDER_NAME)
                        dataModelsFolderNameTextField.text(FCASettingsState.DATA_MODELS_FOLDER_NAME)
                        dataMappersFolderNameTextField.text(FCASettingsState.DATA_MAPPER_FOLDER_NAME)
                        dataRepositoriesFolderNameTextField.text(FCASettingsState.DATA_REPOSITORY_FOLDER_NAME)

                        domainModelsFolderNameTextField.text(FCASettingsState.DOMAIN_MODELS_FOLDER_NAME)
                        domainUseCasesFolderNameTextField.text(FCASettingsState.DOMAIN_USE_CASE_FOLDER_NAME)
                        domainRepositoriesFolderNameTextField.text(FCASettingsState.DOMAIN_REPOSITORY_FOLDER_NAME)

                        presentationsBlocFolderNameTextField.text(FCASettingsState.PRESENTATION_BLOC_FOLDER_NAME)
                        presentationsStateFolderNameTextField.text(FCASettingsState.PRESENTATION_STATE_FOLDER_NAME)
                        presentationsPagesFolderNameTextField.text(FCASettingsState.PRESENTATION_PAGE_FOLDER_NAME)
                        presentationsWidgetsFolderNameTextField.text(FCASettingsState.PRESENTATION_WIDGET_FOLDER_NAME)

                    }
                }
                group("Folder Names") {
                    row { label("data") }
                    row {
                        dataSourcesFolderNameTextField = textField()
                            .bindText(
                                { fcaSettings.state.dataSourcesFolderName ?: "" },
                                { value -> fcaSettings.state.dataSourcesFolderName = value }
                            )
                            .validationOnInput(textValidator)
                            .label("    |----")
                    }
                    row {
                        dataRepositoriesFolderNameTextField = textField()
                            .bindText(
                                { fcaSettings.state.dataRepositoriesFolderName ?: "" },
                                { value -> fcaSettings.state.dataRepositoriesFolderName = value }
                            )
                            .validationOnInput(textValidator)
                            .label("    |----")
                    }
                    row {
                        dataModelsFolderNameTextField = textField()
                            .bindText(
                                { fcaSettings.state.dataModelsFolderName ?: "" },
                                { value -> fcaSettings.state.dataModelsFolderName = value }
                            )
                            .validationOnInput(textValidator)
                            .label("    |----")
                            .enabledIf(
                                checkBox("")
                                    .bindSelected(
                                        { fcaSettings.state.isCreateDataModelsTemplates },
                                        { value -> fcaSettings.state.isCreateDataModelsTemplates = value }
                                    )
                                    .selected
                            )
                    }
                    row {
                        dataMappersFolderNameTextField = textField()
                            .bindText(
                                { fcaSettings.state.dataMappersFolderName ?: "" },
                                { value -> fcaSettings.state.dataMappersFolderName = value }
                            )
                            .validationOnInput(textValidator)
                            .label("    |----")
                            .enabledIf(
                                checkBox("")
                                    .bindSelected(
                                        { fcaSettings.state.isCreateDataMapperTemplates },
                                        { value -> fcaSettings.state.isCreateDataMapperTemplates = value }
                                    )
                                    .selected
                            )

                    }
                    row { label("domain") }
                    row {
                        domainModelsFolderNameTextField = textField()
                            .bindText(
                                { fcaSettings.state.domainModelsFolderName ?: "" },
                                { value -> fcaSettings.state.domainModelsFolderName = value }
                            )
                            .validationOnInput(textValidator)
                            .label("    |----")
                    }
                    row {
                        domainUseCasesFolderNameTextField = textField()
                            .bindText(
                                { fcaSettings.state.domainUseCasesFolderName ?: "" },
                                { value -> fcaSettings.state.domainUseCasesFolderName = value }
                            )
                            .validationOnInput(textValidator)
                            .label("    |----")
                    }
                    row {
                        domainRepositoriesFolderNameTextField = textField()
                            .bindText(
                                { fcaSettings.state.domainRepositoriesFolderName ?: "" },
                                { value -> fcaSettings.state.domainRepositoriesFolderName = value }
                            )
                            .validationOnInput(textValidator)
                            .label("    |----")
                    }
                    row {
                        label("presentation")
                    }
                    var isCreateBloc = fcaSettings.state.isCreatePresentationBlocTemplates
                    buttonsGroup {
                        row {
                            presentationsBlocFolderNameTextField = textField()
                                .bindText(
                                    { fcaSettings.state.presentationBlocFolderName ?: "" },
                                    { value -> fcaSettings.state.presentationBlocFolderName = value }
                                )
                                .label("    |----")
                                .enabledIf(
                                    radioButton(
                                    "",
                                        true
                                    )
                                    .selected(fcaSettings.state.isCreateDataMapperTemplates)
                                    .selected
                                )
                        }
                        row {
                            presentationsStateFolderNameTextField = textField()
                                .bindText(
                                    { fcaSettings.state.presentationStateFolderName ?: "" },
                                    { value -> fcaSettings.state.presentationStateFolderName = value }
                                )
                                .label("    |----")
                                .enabledIf(
                                    radioButton(
                                        "",
                                        false
                                    )
                                    .selected(!fcaSettings.state.isCreateDataMapperTemplates)
                                    .selected
                                )
                        }
                    }.bind(
                        { fcaSettings.state.isCreatePresentationBlocTemplates },
                        { value -> fcaSettings.state.isCreatePresentationBlocTemplates = value }
                    )
                    row {
                        presentationsPagesFolderNameTextField = textField()
                            .bindText(
                                { fcaSettings.state.presentationPagesFolderName ?: "" },
                                { value -> fcaSettings.state.presentationPagesFolderName = value }
                            )
                            .validationOnInput(textValidator)
                            .label("    |----")
                    }
                    row {
                        presentationsWidgetsFolderNameTextField = textField()
                            .bindText(
                                { fcaSettings.state.presentationWidgetsFolderName ?: "" },
                                { value -> fcaSettings.state.presentationWidgetsFolderName = value }
                            )
                            .validationOnInput(textValidator)
                            .label("    |----")
                            .enabledIf(checkBox("")
                                .bindSelected(
                                    { fcaSettings.state.isCreatePresentationWidgetsTemplates },
                                    { value -> fcaSettings.state.isCreatePresentationWidgetsTemplates = value }
                                )
                                .selected
                            )
                    }
                }
            }

        }
    }

    override fun apply() {
        if(
            dataSourcesFolderNameTextField.component.text.isNullOrBlank() ||
            dataModelsFolderNameTextField.component.text.isNullOrBlank() ||
            dataMappersFolderNameTextField.component.text.isNullOrBlank() ||
            dataRepositoriesFolderNameTextField.component.text.isNullOrBlank() ||
            domainModelsFolderNameTextField.component.text.isNullOrBlank() ||
            domainUseCasesFolderNameTextField.component.text.isNullOrBlank() ||
            domainRepositoriesFolderNameTextField.component.text.isNullOrBlank() ||
            presentationsBlocFolderNameTextField.component.text.isNullOrBlank() ||
            presentationsStateFolderNameTextField.component.text.isNullOrBlank() ||
            presentationsPagesFolderNameTextField.component.text.isNullOrBlank() ||
            presentationsWidgetsFolderNameTextField.component.text.isNullOrBlank()
        ) {
            throw ConfigurationException("Fields cannot be empty")
        }
        super.apply()
    }

}