package com.elixer.puck

import com.elixer.puck.Utils.Configuration.Edges

class Utils {

    /**
     * Define behaviour of the Composable
     * //TODO: Add a gravity type, where composable gets "attracted" to certain positions
     */
    sealed class Behaviour() {
        object FreeForm : Behaviour()
        data class Sticky(var config: Configuration = Edges) : Behaviour()
        data class Gravity(var circle: Circle) : Behaviour()
    }

    sealed class Configuration() {
        object Edges : Configuration()
        object Corners : Configuration()
        object VerticalEdges : Configuration()
        object HorizontalEdges : Configuration()
    }

    sealed class Edge() {
        object Top : Edge()
        object Bottom : Edge()
        object Right : Edge()
        object Left : Edge()
    }
}