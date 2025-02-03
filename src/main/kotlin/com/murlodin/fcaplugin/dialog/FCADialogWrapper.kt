package com.murlodin.fcaplugin.dialog

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.layout.ValidationInfoBuilder
import javax.swing.JComponent

class FCADialogWrapper(private val action: AnActionEvent) : DialogWrapper(action.project) {

    private lateinit var nameTextField: Cell<JBTextField>

    init {
        title = "Create FCA Feature"
        super.init()
    }

    fun getNameTextFieldValue() : String = nameTextField.component.text

    override fun createCenterPanel(): JComponent {

        val selectedFolder = PlatformDataKeys.VIRTUAL_FILE.getData(action.dataContext)

        val nameValidator: ValidationInfoBuilder.(JBTextField) -> ValidationInfo? = {

            var isSameName = false

            if (selectedFolder != null) {
                for(child in selectedFolder.children) {
                    if (it.text == child.name && child.isDirectory) {
                        isSameName = true
                        break
                    }
                }
            }

            when {
                it.text.isNullOrBlank() -> ValidationInfo("Please enter a name")
                isSameName -> ValidationInfo("A folder with that name already exists. ")
                else -> null
            }
        }

        return panel {
            row {
                label("Feature name")
            }
            row {
                nameTextField = textField()
                    .focused()
                    .validationOnApply(
                        nameValidator
                    )
            }
        }
    }

}
