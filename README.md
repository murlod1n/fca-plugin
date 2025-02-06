# FCA - Flutter Clean Architecture
A plugin for <b>Android Studio</b> that allows you to create a folder structure for a <b>Flutter Clean Architecture Clear Feature</b>

### Links
[FCA Plugin in JetBrains Marketplace](#https://plugins.jetbrains.com/plugin/26454-fca)

### Feature tree
One possible data structure might look like this:

```
feature_name
├── data
│   ├── data_sources
│   ├── models
│   ├── mappers
│   │   └── feature_name_dto_mapper.dart
│   └── repositories
│       └── feature_name_repositopry_impl.dart    
├── domain
│   ├── entities
│   ├── use_cases
│   └── repositories
│       └── feature_name_repositopry.dart    
└── data
    ├── state
    ├── screens
    └── widgets
```

### Plugin Settings
You can disable and rename folders.
![Plugin Settings!](/assets/images/plugin_settings.png)

### Create New Feature
To create a feature, just right-click on the desired folder and select the `New` menu item and select `Add FCA Feature`

![Add feature!](/assets/images/add_feature.png)