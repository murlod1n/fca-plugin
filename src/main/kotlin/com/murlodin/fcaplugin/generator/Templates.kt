package com.murlodin.fcaplugin.generator

class Templates {
    companion object {

        const val REPOSITORY_IMPL_TEMPLATE = "import \"../../domain/repositories/{name}_repository.dart\";\n\nclass {capitalize_name}RepositoryImpl implements {capitalize_name}Repository {}\n"
        const val REPOSITORY_TEMPLATE = "abstract interface class {capitalize_name}Repository {}\n"
        const val BLOC_TEMPLATE =
            "import \"package:bloc/bloc.dart\";\n\n" +
            "part \"{name}_event.dart\";\n" +
            "part \"{name}_state.dart\";\n\n" +
            "class {capitalize_name}Bloc extends Bloc<{capitalize_name}Event, {capitalize_name}State> {\n" +
            "  {capitalize_name}Bloc() : super({capitalize_name}State()) {\n" +
            "    on<{capitalize_name}Event>((event, emit) {});\n" +
            "  }\n" +
            "}"
        const val BLOC_EVENT_TEMPLATE =
            "part of \"{name}_bloc.dart\";\n\n" +
            "sealed class {capitalize_name}Event {}\n"
        const val BLOC_STATE_TEMPLATE =
            "part of \"{name}_bloc.dart\"; \n\n" +
            "enum {capitalize_name}StateStatus {\n  init,\n  loading,\n  success,\n  error\n}\n\n\n" +
            "extension {capitalize_name}StateStatusCheck on {capitalize_name}StateStatus {\n" +
            "  bool get isInit => this == {capitalize_name}StateStatus.init;\n" +
            "  bool get isLoading => this == {capitalize_name}StateStatus.loading;\n" +
            "  bool get isSuccess => this == {capitalize_name}StateStatus.success;\n" +
            "  bool get isError => this == {capitalize_name}StateStatus.error;\n}\n\n\n" +
            "class {capitalize_name}State {\n" +
            "{capitalize_name}State({\n    this.status = {capitalize_name}StateStatus.init,\n  });\n\n" +
            "  final {capitalize_name}StateStatus status;\n\n" +
            "  {capitalize_name}State copyWith({\n    {capitalize_name}StateStatus? status,\n  }) {\n" +
            "    return {capitalize_name}State(\n      status: status ?? this.status,\n    );\n  }\n\n}"

    }


}
