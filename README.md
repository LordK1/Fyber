### Android Developer Challenge
This is challenge of Fyber employment for the Android Developer job opportunity, and I just trying to solve this challenge to show some part of my abilities and skills as Senior Android Developer.
The Architecture of Application is MVP (Model - View - Presenter) or DataBinding also announced by [Google](https://developer.android.com/topic/libraries/data-binding/index.html).


## Tasks: (Passed)
* Create a form asking for the variables (Uid, API key, appid, pub0) => [GetParameterDialogFragment.java](./app/src/main/java/com/k1/fyber/GetParameterDialogFragment.java)
* Make the request to the API passing the params and the authentication hash => [MainActivity.java](./app/src/main/java/com/k1/fyber/MainActivity.java#get)
* Get the result from the response. [OffersFragment.java](./app/src/main/java/com/k1/fyber/OffersFragment.java)
* Check the returned hash to check that it’s a real response (check signature)[MainActivity#OffersDataCallback](./app/src/main/java/com/k1/fyber/OffersFragment.java#OffersDataCallback)
* Render the offers in a view. [MainActivity#OffersDataCallback](./app/src/main/java/com/k1/fyber/OffersFragment.java#OffersDataCallback)
    - If we have offers there we render them (title, teaser, thumbnail hires and payout) 
    - If we have no offers there we render a message like ‘No offers’
* Create functional and unit tests (choose your tool) [MainActivityRobolectricTest.java](./app/src/test/java/com/k1/fyber/MainActivityRobolectricTest.java)
* Check Fyber Mobile Offer API Documentation at http://developer.fyber.com/content/android/offer-wall/offer-api/ 
* Create a github repository and send us the URL with the solution of the challenge [Fyber](https://github.com/LordK1/Fyber) 

## CAUTION
Please, Notice it's really experimental project, maybe not convenient for real production project, and I just try to pass tasks of this challenge !!!
Maybe there are a lot of better solutions for some parts, whatever some parts are useless or unimportant but used to because I want to showcasing my skills and experiences in Android Development!!
  
 


## Requirements & Installation
You can download APK (app/app-release.apk) file from this [link](./app/app-release.apk),
 
Or make it from source as below :

    git clone git@bitbucket.org:LordK1/Fyber.git 
    cd Fyber
    ./gradlew clean
    ./gradlew packageDebug
    adb install app/build/outputs/apk/app-debug.apk
    
## Features
this is a list of used libraries/third parties to make an optimized solution:

- [Retrofit 2](http://square.github.io/retrofit/)
- [Picasso](http://square.github.io/picasso/)
- [GSON](https://github.com/google/gson)
- [RecyclerView,CardView](https://developer.android.com/training/material/lists-cards.html)
- [AppCompat](https://developer.android.com/topic/libraries/support-library/features.html)
- [DataBinding](https://developer.android.com/topic/libraries/data-binding/index.html)
- [Parceler](https://github.com/johncarl81/parceler)
- [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/)
- [Robolectric](http://robolectric.org/)
 
 
### Challenge Contents 
The whole idea of this challenge is to use the Fyber Mobile Offers API (JSON) and render the results of the response in a native Android application. 
Action Steps 

1. Create a form asking for the variable params (uid, API Key, appid, pub0) 
2. Make the request to the API passing the params and the authentication hash  
3. Get the result from the response. 
4. Check the returned hash to check that it’s a real response (check signature) 
5. Render the offers in a view.  
    A. If we have offers there we render them (title, teaser, thumbnail hires and payout) 
    B. If we have no offers there we render a message like ‘No offers’  
6. Create functional and unit tests (choose your tool) 
7. Check Fyber Mobile Offer API Documentation at http://developer.fyber.com/content/android/offer-wall/offer-api/ 
8. Create a github repository and send us the URL with the solution of the challenge 
 
Params to be used 
format: json 
appid: Application ID, provided as simple data   
uid: User ID, provided as simple data   
device_id: use Android advertising identifier   
locale: provided as simple data 
ip: provided as simple data 
offer_types: 112 
 
Sample app data:
appid: 2070 
uid: spiderman 
locale: ‘DE’ 
ip: ‘109.235.143.113’ 
API Key: 1c915e3b5d42d05136185030892fbb846c278927

### It's my pleasure to hear your feedback ASAP:")


