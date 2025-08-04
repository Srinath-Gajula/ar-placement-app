# AR Placement App for Android

## 🎯 Overview
This is a minimal ARCore-based Android app that allows users to:
- Select from a list of 3 drills
- View drill details including image, description, and tips
- Enter an AR view and tap on the ground to place a colored 3D object (cube or cone)

## 🛠️ How to Run the App

### Requirements:
- Android Studio (Electric Eel or later recommended)
- Android device with **ARCore support**
- Minimum SDK: **API Level 24 (Android 7.0)**

### Steps:
1. Clone or unzip the project:
git clone https://github.com/Srinath-Gajula/ar-placement-app.git

pgsql
Copy
Edit
2. Open in Android Studio.
3. Build the project and connect a physical ARCore-compatible Android device.
4. Run the app on the device.
5. Select a drill, then tap **Start AR Drill** to enter AR view.
6. Tap on a detected plane to place a 3D object.

> ⚠️ NOTE: AR features **will not work** in an emulator. Use a physical device.

## 📦 Dependencies

| Library     | Purpose                        |
|-------------|--------------------------------|
| [SceneView](https://github.com/SceneView/sceneview-android) | ARCore + Sceneform alternative for modern AR apps |
| ARCore      | Used internally via SceneView |
| Coil        | For loading drill images       |
| Jetpack Compose | Modern Android UI toolkit  |
| Navigation Compose | In-app navigation       |

## 📱 Permissions

Ensure the following permissions are declared in `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-feature android:name="android.hardware.camera.ar" android:required="true" />
