# scalajs-google-yolo
This is a facade/wrapper for Google's "Open YOLO" library in Scala.js.
It allows you to access the library without complicated manual workarounds and embed it anywhere in your web application code.

## Background
The original Google YOLO library is described [in this guide for one-tap sign-in][1] for websites using Google authentication.
You should read it and familiarize yourself with the concepts before using this library.
YOLO stands for "You Only Login Once" and is an emerging authentication standard for automatic social logins.

## Installation
Add the library to any of your Scala.js projects by adding this dependency to your `build.sbt`:
```
libraryDependencies += "io.medialog" %%% "scalajs-google-yolo" % "0.1.0"
```

## Implementation
The Google YOLO library has you writing a callback function in the global window object that their own library loads.
This is not the most straightforward design, so this facade attempts to abstract that as much as possible to reduce manual overhead.
It includes abstractions for the types of settings accepted by each method, the contexts in which requests can be made, as well as abstractions over loading the library itself.

## Basic Instructions
Import the `YoloLoader` object from `io.medialog.googleyolo`.
This is your main point of entry into the library and allows you to configure and load it at your convenience.

First, configure your settings for each of the authorization flows you wish to use, like so:
```
import io.medialog.googleyolo._
import io.medialog.googleyolo.context._

...

val hintSettings = YoloLoader.makeHintSettings("my-client-id", SignInContext)
val hintSettingsWithDefaultContext = YoloLoader.makeHintSettings("my-client-id")
val retrieveSettings = YoloLoader.makeRetrieveSettings("my-client-id", SignUpContext)
```

In each of these settings objects, a "context" is specified (listed in `io.medialog.googleyolo.context`).
This context mirrors the contexts presented in the aforementioned guide from Google on the original library, indicating your intentions with the login data.

Second, define a callback function that acts on a `GoogleYolo` object as defined in this facade:
```
val onGoogleYoloLoad: GoogleYolo => Any = { yolo =>
  // Define your logic here
}
```

Inside your callback function you can do whatever you'd like to trigger the methods on the `GoogleYolo` object being passed.
Here is an example showing using the `hint` method and handling the result as an implicit Future:
```
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.Thenable.Implicits._ // Import this to implicitly convert promises to futures

... inside your callback ...

val hintFuture = yolo.hint(hintSettings)
hintFuture.map { result =>
  // Handle your result here
}
```

Finally, you must set the global `onGoogleYoloLoad` function as this:
```
YoloLoader.setYoloFunction(yolo) // Sets global function handled by the Google YOLO library
```

## Loading the Google YOLO Library
The Google YOLO library, as described in Google's documentation, is a manually placed HTML script tag in your page.
It is currently not available through NPM or other means for bundling or manually initiating.
Thus, this library provides a more dynamic workaround: `YoloLoader` has a method to dynamically load the library whenever you choose, like so:
```
YoloLoader.loadScript() // Dynamically loads the library script.
```

This inserts the script tag for the library at the end of your document body.
You can use any method you wish, but it is preferred to use this method as it is more flexible and it handles accessing the DOM for you.

**Warning:** If you do use this method, note that it will reinsert the library every time you call it, so make sure it is only called once!
Preferably, you should load the script at the beginning of your program.
A future version of this facade library will look into providing a safer alternative.

## Contributions & Feedback
Any and all feedback, bug reports, and pull requests are welcome!
This facade is based on a very new and in-development library so please let me know if anything breaks for you or if you would like more functionality.
If anything breaks with the official Google YOLO library or it becomes bundleable, you are welcome to file an Issue to get it fixed.

## License
This Scala.js library is licensed under the MIT license as defined in the LICENSE file.
The Google YOLO library is currently licensed under the Apache License 2.0 (as specified [within the source code][2]).
Usage of this library in your projects means acceptance of the terms of both licenses.

[1]: https://developers.google.com/identity/one-tap/web/overview
[2]: https://smartlock.google.com/client