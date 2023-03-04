package main

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

class MainFX : Application(){

    override fun start(primaryStage: Stage?) {
        try{
            val loaderMenu = FXMLLoader(
                javaClass.getResource("/fxml/principal.fxml"))
            val root: AnchorPane? = loaderMenu.load()
            val scene = Scene(root)
            primaryStage?.title = "APP"
            primaryStage?.scene = scene
            primaryStage?.show()
            primaryStage?.isResizable = false
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}


fun main(args: Array<String>) {
    Application.launch(MainFX::class.java, *args)
}