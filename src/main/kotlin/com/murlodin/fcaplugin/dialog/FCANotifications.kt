package com.murlodin.fcaplugin.dialog

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.project.Project

class FCANotifications {
    companion object {
        fun error(project: Project?, content: String) =
            Notifications.Bus.notify(
                Notification(
                    "FCAGenerator",
                    "Clean Architecture Feature Generator Error",
                    content,
                    NotificationType.ERROR
                ), project
            )
    }
}