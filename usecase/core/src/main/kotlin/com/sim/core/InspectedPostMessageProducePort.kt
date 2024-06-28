package com.sim.core

import com.sim.domain.inspectedpost.InspectedPost

interface InspectedPostMessageProducePort {

    fun sendCreateMessage(inspectedPost: InspectedPost)
    fun sendUpdateMessage(inspectedPost: InspectedPost)
    fun sendDeleteMessage(id: String)
}