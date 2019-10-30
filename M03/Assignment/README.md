# Android Analytics / Reading List

## Introduction

Reading List is an application that lets you save a list of books and allow you to mark whether you have or have not read them. Since this application will use persistence, the books you create and check as read or not will be saved between launches of the application.

We're going to update this application to add some analytics events so we can monitor how users are interacting with it.

## Step 1 - Clone and build the **PROVIDED** Reading List repo
1. First, make sure the app builds and runs.  
2. Open AVD settings for your emulator and make sure it is set to "cold boot" in settings
3. Delete any existing versions of this app from your emulator    
(_Note: this is to ensure your application will properly register with Firebase on start_)

## Step 2 - Create an application in Firebase Console
1. Log in to Firebase Console (http://console.firebase.google.com)
2. Create a new project with the package name `com.lambdaschool.readinglist` and the name "Reading List"

## Step 3 - Configure your application for Firebase Analytics
1. Download the `google-services.json` file from Step 2 and move it into the root `app` folder
2. Copy and paste the Gradle config to the appropriate files
3. Sync and rebuild your project to ensure it still builds and runs

## Step 4 - Add screen view events to your Activities
1. Add a screen view for `MainActivity`
2. Add a screen view for `EditBookActivity`

## Step 5 - Add events when books are added
1. Log an event whenever a book is added called "book_added"
2. Add `title`, `reasonToRead`, and `isHasBeenRead` to your events

## Step 6 - Test
Check the Firebase console to see that your events are being properly logged

## Stretch Goal - DRY out the analytics code
Build a nice architecture for removing any repetitive Analytics code from your application
