object Compose {

    /**Using the BOM ensures that the versions of any Compose libraries in your app are compatible,
     *  but the BOM doesn't actually add those Compose libraries to your app
     *  The Compose Bill of Materials (BOM) lets you manage all of your Compose library versions by
     *  specifying only the BOM’s version. The BOM itself has links to the stable versions of the
     *  different Compose libraries, in such a way that they work well together*/
    private const val bomVersion = "2022.12.00"
    const val bom = "androidx.compose:compose-bom:$bomVersion"

    const val composeUi =  "androidx.compose.ui:ui"

    /**Material design*/
    const val composeMaterial = "androidx.compose.material:material"

    /**Android Studio Preview support*/
    const val composePreview = "androidx.compose.ui:ui-tooling-preview"
    const val previewDebugImplementation =  "androidx.compose.ui:ui-tooling"

    /**UI Tests*/
    const val androidTestImplementation = "androidx.compose.ui:ui-test-junit4"
    const val testDebugImplementation = "androidx.compose.ui:ui-test-manifest"

    /**Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)*/
    const val materialIconsCore = "androidx.compose.material:material-icons-core"
    /**Optional - Add full set of material icons*/
    const val materialIconsExtended = "androidx.compose.material:material-icons-extended"
    /**Optional - Add window size utils*/
    const val materialWindowsClass = "androidx.compose.material3:material3-window-size-class"

    /**Optional - Integration with activities*/
    const val composeActivityIntegration = "androidx.activity:activity-compose:1.5.1"
    /**Optional - Integration with ViewModels*/
    const val composeViewModelIntegration = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
    /**Optional - Integration with LiveData*/
    const val composeLiveDataIntegration = "androidx.compose.runtime:runtime-livedata"
}