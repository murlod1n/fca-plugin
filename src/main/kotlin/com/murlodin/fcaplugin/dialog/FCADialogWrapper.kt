package com.murlodin.fcaplugin.dialog

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.layout.ValidationInfoBuilder
import javax.swing.JComponent

class FCADialogWrapper(private val action: AnActionEvent) : DialogWrapper(action.project) {

    private var name: String = ""

    init {
        super.init()
    }

    fun getFeatureName(): String {
        return name
    }

    override fun createCenterPanel(): JComponent {

        val selected = PlatformDataKeys.VIRTUAL_FILE.getData(action.dataContext)

        val nameValidator: ValidationInfoBuilder.(JBTextField) -> ValidationInfo? = {

            var isSameName = false

            if (selected != null) {
                for(child in selected.children) {
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
                textField()
                    .focused()
                    .validationOnApply(
                        nameValidator
                    )
                    .onChanged {
                        name = it.text
                    }
            }
        }
    }

}
