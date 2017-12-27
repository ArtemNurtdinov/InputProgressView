# InputProgressView

![alt tag](https://i.imgur.com/Pw6X2Ro.gif)

Examples:

![alt tag](https://i.imgur.com/kUcFTo0.gif)

![alt tag](https://i.imgur.com/icTpROc.gif)

Simple library for showing the input progress

### Connecting

You can add next dependency in your project:

`com.nefrit:inputprogressview:0.1.5`

For example, your gradle script will contains such dependencies: 
```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:20.0.0'
    compile 'com.nefrit:inputprogressview:0.1.5'
}
```

Connecting via downloading 
----------

You can add the library to your project using Gradle.
1) Copy the **InputProgressView** directory to your project's directory;
2) Find the **settings.gradle** file. Most likely, it contains something like that:

```gradle
include ':app'
```

Edit the line this way:

```gradle
include ':app', ':inputprogressview'
```

3) Add this line to your dependencies in your app's gradle:

```gradle
compile project(':inputprogressview')
```

### Usage

Add `InputProgressView` to your layout:
```xml
<com.nefrit.inputprogressview.gui.InputProgressView
        android:id="@+id/input_progress"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"/>
```

If you want, you can create your custom drawables for each progress state:

For example, `background_filled.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <solid android:color="#C2185B"/>
</shape>
```

and `background_empty.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <stroke
        android:width="2dp"
        android:color="#C2185B"/>
       
    <solid android:color="@android:color/transparent" />
</shape>
```

Then add to `InputProgressView`:
```xml
<com.nefrit.inputprogressview.gui.InputProgressView
        android:id="@+id/input_progress"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        app:background_empty="@drawable/background_ipv_empty"
        app:background_filled="@drawable/background_ipv_filled"/>
```

You can set max progress to view by adding `app:max_progress:"5"`

Take a look at the [sample project](sample) for more information.