package com.sim.autoinspectionworker

import org.junit.jupiter.api.Test
import org.springframework.modulith.core.ApplicationModules
import org.springframework.modulith.docs.Documenter


class ModuleTest {
    private val modules = ApplicationModules.of("com.sim")

    @Test
    fun writeDocumentationSnippets(){
        Documenter(modules)
            .writeModulesAsPlantUml()
            .writeIndividualModulesAsPlantUml()
    }

}