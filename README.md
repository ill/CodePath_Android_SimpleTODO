# Pre-work - Pusheen's Todo

Pusheen's Todo is a Pusheen themed Todo list Android App.

Submitted by: Ilya Seletsky

Time spent: 4 hours spent in total

## User Stories

The following **required** functionality is completed:

* [*] User can **successfully add and remove items** from the todo list
* [*] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [*] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [*] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [*] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [*] Add support for completion due dates for todo items (and display within listview item)
* [*] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [*] Add support for selecting the priority of each todo item (and display in listview item)
* [*] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [*] List anything else that you can get done to improve the app functionality!
    Pusheen icon makes it all better.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/ill/CodePath_Android_SimpleTODO/blob/master/Recording.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:**

I have experience with iOS.  The way you launch activities with intents on Android is quite different.

On iOS you typically push a view controller onto the stack.  You set some data up by modifying the new instance of the view controller.  On Android, you pass these things in the intent as key value pairs.

To pass data back on iOS you'd use delegates or some other mechanism to pass back to the calling view controller.  On Android, you tell the activity to finish, and pass the data back with an inent.

So far, the interface builder on Android is nicer.  Our team refuses to even use the iOS interface builder and we do it all in code.  On Android, it's nice that youc an edit the xml to really make sure things are correct, whereas on iOS editing the xib file isn't as user friendly.  You're supposed to be able to do it all through the buggy UI of the interface builder.

It's also nice that there's a built in Array adapter for lists.  This makes handling simple cases like an array of some objects easy on Android.  On iOS there are all sorts of UITableViewDataSource methods that need to be implemented, making it cumbersome to do something very simple for beginners.  It's good for when things get complicated and you need the flexibility.  On Android, I'm sure the option is there to make things flexible and complicated as well, for when the time comes.

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:**

The adapter most likely handles taking the elements of the array of strings, and telling the list to display those elements.  On iOS, you'd have tableViewDataSource methods like number of rows in section, cellForRow at path, number of sections, etc...  On Android, you set per list, the instance of a data source object, and it's the Adapter object's job to tell the list these things.

According to the docs, getView takes a convertView that can be reused to display new data.  It's able to recycle an existing view as you scroll, just like on iOS.  This is better for performance and smoother scrolling since it doesn't have to keep reinstantiating a new view.  It can just reuse one that is no longer visible due to being scrolled past.

It's also possible to create a new view if that one isn't usable for that data item.  This could be useful if you have different kind of list cells for different elements in the list.


## Notes

Describe any challenges encountered while building the app.

## License

    Copyright 2017 Ilya Seletsky

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.