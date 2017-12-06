package crepe.dan.moovie.view

/**
 * A wrapper class to hold view state and its corresponding message
 */

data class ViewStateResource(val state: ViewState, val message: String? = null)