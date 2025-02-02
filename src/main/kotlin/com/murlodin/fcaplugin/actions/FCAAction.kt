package com.murlodin.fcaplugin.actions

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.command.WriteCommandAction
import com.murlodin.fcaplugin.dialog.FCADialogWrapper
import com.murlodin.fcaplugin.generator.FCAGenerator

class FCAAction : AnAction() {

    override fun actionPerformed(actionEvent: AnActionEvent) {

        val dialog = FCADialogWrapper(actionEvent)

        if (dialog.showAndGet()) {
            val featureName = dialog.getFeatureName()

            generateFeature(actionEvent.dataContext, featureName)
        }
    }

    private fun generateFeature(dataContext: DataContext, featureName: String) {

        val project = CommonDataKeys.PROJECT.getData(dataContext) ?: return
        val selected = PlatformDataKeys.VIRTUAL_FILE.getData(dataContext) ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            FCAGenerator().createMainFolder(
                project,
                selected,
                featureName
            );
        }

    }

}