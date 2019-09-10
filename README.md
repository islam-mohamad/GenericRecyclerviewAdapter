# GenericRecyclerviewAdapter

No need to create a new adapter for each recyclerView in your app any more.

## Installation

##### Step 1. Add the JitPack repository to your build file


```kotlin
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

##### Step 2. Add the dependency

```kotlin
dependencies {
	        implementation 'com.github.sal3awy:GenericRecyclerviewAdapter:1.0'
	}
  ```

##### Step 3. Enable Data Binding in your app gradle file.

```kotlin
  dataBinding {
        enabled = true
    }
```

## Usage


##### Step 1. Create interface for actions callbacks on the recyclerView item layout and make it extends `ItemRecyclerViewCallback`.

``` kotlin
interface UserCallBack : ItemRecyclerViewCallback {
    fun userClicked(user: User)
}
```
##### Step 2. make the view (Activity or Fragment) implement the interface which extends `ItemRecyclerViewCallback`
```kotlin
class MainActivity : AppCompatActivity(), UserCallBack {
   ...
}

```
##### Step 3. Setup the recylerView adapter
```kotlin
val adapter = GenericRecyclerViewAdapter(this, list, R.layout.item_user)
recyclerView.layoutManager = LinearLayoutManager(this)
recyclerView.adapter = adapter
adapter.notifyDataSetChanged()
```
##### Step 4. setup recyclerView item layout XML
in recyclerView item layout declare `model` data binding variable.
 
Optionally declare `callback` data binding variable of type any Interface but must extends `ItemRecyclerViewCallback`, and declare `isSelected` data binding variable of type Boolean for view states (like visibility, change colors or drawables based on boolean)

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout
		xmlns:android="http://schemas.android.com/apk/res/android"
		xmlns:tools="http://schemas.android.com/tools">
	<data>
		<variable
				name="callback"
				type="com.sal3awy.genericrecyclerviewadapter.UserCallBack"/>
		<variable
				name="model"
				type="com.sal3awy.genericrecyclerviewadapter.User"/>
               <import type="android.view.View" />
               <variable
                                name="isSelected"
                                type="Boolean" />
	</data>
	<TextView
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
                android:text="@{model.name}"
                android:visibility="@{isSelected ? View.VISIBLE: View.GONE}"
                android:onClick="@{()->callback.onUserClicked(model)}"/>
</layout>

```
##### Step 5. setup recyclerView item layout View states
use `adapter.setItemSelected(user, false)` for setting view state. If Second parameter is true you will enable multiple selection which mean save other views state.

## Hint
* Step five are optional in case there is no View states.
* Steps one and two are optional in case there is no actions callback, then declare the adapter instance like that
 `val adapter = GenericRecyclerViewAdapter(list = users, layoutId =  R.layout.item_user)`
  in Java you must make your view extend `ItemRecyclerViewCallback` and declare the adapter instance like that
 `GenericRecyclerViewAdapter adapter = new GenericRecyclerViewAdapter(this, users, R.layout.item_user)` .
