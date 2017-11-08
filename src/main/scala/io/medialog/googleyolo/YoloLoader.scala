package io.medialog.googleyolo

import context._
import settings._
import org.scalajs.dom
import org.scalajs.dom.raw.Window

import scala.scalajs.js

/**
  * The main point-of-entry for the Google YOLO library.
  *
  * The library itself requires a global function titled 'onGoogleYoloLoad' that accepts
  * a single callback. This is abstracted away into a single 'setYoloFunction' method
  * that does the dirty work for you.
  *
  * The library also is not currently available via NPM or similar, so it must be loaded
  * dynamically. The 'loadScript' method takes care of that by appending the JS remote source
  * to your document body.
  */
object YoloLoader {

  @js.native
  private trait GoogleYoloWindow extends Window {
    var onGoogleYoloLoad: js.Function1[GoogleYolo, Any] = js.native
    var googleYoloSettings: GoogleYoloSettings = js.native
    var googleYoloObject: GoogleYolo = js.native
  }

  private val window: GoogleYoloWindow =
    dom.window.asInstanceOf[GoogleYoloWindow]

  /**
    * Sets a global callback function for the Google YOLO library once it is loaded
    * @param f Your application's callback function acting on a [[io.medialog.googleyolo.GoogleYolo]] instance
    */
  def setYoloFunction(f: js.Function1[GoogleYolo, Any]): Unit =
    window.onGoogleYoloLoad = f

  private def makeSettings(clientId: String,
                           context: YoloContext): GoogleYoloSettings = {

    val contextStr = context match {
      case SignInContext   => "signIn"
      case SignUpContext   => "signUp"
      case ContinueContext => "continue"
    }

    js.Dynamic
      .literal(
        supportedAuthMethods = js.Array("https://accounts.google.com"),
        supportedIdTokenProviders = js.Array(
          js.Dynamic
            .literal(
              uri = "https://accounts.google.com",
              clientId = clientId
            )
            .asInstanceOf[SupportedIdTokenProvider]
        ),
        context = contextStr
      )
      .asInstanceOf[GoogleYoloSettings]

  }

  /**
    * Makes a [[io.medialog.googleyolo.settings.GoogleYoloSettings]] instance suitable for the [[io.medialog.googleyolo.GoogleYolo]] 'hint' method
    * @param clientId The application's unique Google Client ID
    * @param context The context in which the authentication request is to be made
    * @return A pre-formatted [[io.medialog.googleyolo.settings.GoogleYoloHintSettings]] instance with your context/clientId
    */
  def makeHintSettings(
      clientId: String,
      context: YoloHintContext = SignInContext): GoogleYoloHintSettings = {

    makeSettings(clientId, context).asInstanceOf[GoogleYoloHintSettings]

  }

  /**
    * Makes a [[io.medialog.googleyolo.settings.GoogleYoloSettings]] instance suitable for the [[io.medialog.googleyolo.GoogleYolo]] 'retrieve' method
    * @param clientId The application's unique Google Client ID
    * @param context The context in which the authentication request is to be made
    * @return A pre-formatted [[io.medialog.googleyolo.settings.GoogleYoloRetrieveSettings]] instance with your context/clientId
    */
  def makeRetrieveSettings(
      clientId: String,
      context: YoloContext = SignInContext): GoogleYoloRetrieveSettings = {

    val settings = makeSettings(clientId, context)
    settings.supportedAuthMethods.push("googleyolo://id-and-password")

    settings.asInstanceOf[GoogleYoloRetrieveSettings]
  }

  /**
    * Dynamically loads the Google YOLO script.
    */
  def loadScript(): Unit = {
    val newScript = dom.document.createElement("script")
    newScript.setAttribute("src", "https://smartlock.google.com/client")
    dom.document.body.appendChild(newScript)
  }

}
