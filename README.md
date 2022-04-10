# GroupProject
Original App Design Project 
===

# Favourites & Friends

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Tracks what genre of book, movies etc. an individual prefers, gives the user recommendation based on their preferred genres, and shows them users in their location with similar interests. Could be potentially used for chatting and meeting new friends with similar tastes in your area.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Social Networking/Personalized reccommendation
- **Mobile:** Andrioid
- **Story:** An app that can recommend books, movies, etc based on the users’ favorite genres. Help them make connections in their location with those with similar interests.
- **Market:** This app is aiming to build connections between members in the same location based on interest in similar genres. People can be organized by location and their interests.
- **Habit:** This app could be used as frequently as the user wanted depending on how active their social life is, and what exactly they’re looking for interms of new recommendations.
- **Scope:** First, we will show the user recommendations based on their preferred genre and then show them people in their area with similar interests. This app could potentially be used for messaging and meeting new people in you area.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Users should be able to create a profile. 
* Users should be able to add and edit the information on their profile (location, favourite genres, context etc.)
* Users should be able to log in and log out.
* Users can select their favorite genres.
* Users can view the list of recommendations based on their selected/favourite genres.
* Users may receive notification on a daily or weekly basis with new recommendations.  
* Users should be able to see others in their location with similar interests.

**Optional Nice-to-have Stories**

* Users can send messages to people who have the tastes with them.
* Users also can organize virtual and offline events to meet up.

### 2. Screen Archetypes

* Log in screen
   * User create a new profile
   * User can log in and log out 
* Registration screen
   * User can add their profile information, select their favourite genres
* Profile screen/ Setting 
   * Edit and view their profile information and settings
 * Feed with recommendations, people nearby
   * Users should be able to see a list of recommendations based on their interests
   * Users should be able to see the list of people nearby with their similar interests
   

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Profile/Settings

**Optional Nice-to-have **

* Chat -> Jumps to Chat

**Flow Navigation** (Screen to Screen)

* Forced Log-in -> Account creation if no log in is available
* Registration screen --> new users can select their favourite genres, or update their previous choices 
* Recommendation Feed ->Feed for recommendations (books, movies etc.), ->Feed for people in your area 

## Wireframes
<img src="https://github.com/CodePath-Android-Pod23/GroupProject/blob/main/Wireframe/Wireframe.jpg" width=800>

### [BONUS] Digital Wireframes & Mockups
<img src="https://github.com/CodePath-Android-Pod23/GroupProject/blob/main/Artboard/ArtBoard.jpg" width=800>

### [BONUS] Interactive Prototype
<img src="https://github.com/CodePath-Android-Pod23/GroupProject/blob/main/app_prototype.gif" width=200>

## Schema 
[This section will be completed in Unit 9]
### Models

Users:

<table>
<thead>
  <tr>
    <th>Property</th>
    <th>Type</th>
    <th>Description</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Full Name</td>
    <td>String</td>
    <td>Name of the User</td>
  </tr>
  <tr>
    <td>Location</td>
    <td>String</td>
    <td>Name of the City</td>
  </tr>
  <tr>
    <td>Friends</td>
    <td>List of Pointers to Users</td>
    <td>List of Friends</td>
  </tr>
  <tr>
    <td>Recommendations</td>
    <td>List of Pointers to Item Objects</td>
    <td>List of Recommendations by the User</td>
  </tr>
  <tr>
    <td>Genres</td>
    <td>Array of String</td>
    <td>List of Genres Subscribed by the User</td>
  </tr>
  <tr>
    <td>Username</td>
    <td>String</td>
    <td>Username of the User</td>
  </tr>
  <tr>
    <td>Emails</td>
    <td>String</td>
    <td>Email of the Use</td>
  </tr>
  <tr>
    <td>Phone Number</td>
    <td>String</td>
    <td>Phone Number of the User</td>
  </tr>
  <tr>
    <td>Password</td>
    <td>String</td>
    <td>Password of the User</td>
  </tr>
</tbody>
</table>

Items:

<table>
<thead>
  <tr>
    <th>Property</th>
    <th>Type</th>
    <th>Description</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Item Id</td>
    <td>String</td>
    <td>Unique id for the Item (default field)</td>
  </tr>
  <tr>
    <td>Item Type</td>
    <td>String</td>
    <td>What is the item type? (movies or books)</td>
  </tr>
  <tr>
    <td>Title</td>
    <td>String</td>
    <td>Title of the Book/Movie</td>
  </tr>
  <tr>
    <td>Genre</td>
    <td>Array of String</td>
    <td>Genres related to the Item</td>
  </tr>
  <tr>
    <td>Synopsis</td>
    <td>String</td>
    <td>Short Description of the item</td>
  </tr>
  <tr>
    <td>Image</td>
    <td>File</td>
    <td>Image that defines the Item</td>
  </tr>
  <tr>
    <td>Recommended by</td>
    <td>Pointer to User</td>
    <td>Recommenders</td>
  </tr>
  <tr>
    <td>No. of Recommendations</td>
    <td>Number</td>
    <td>number of recommendations for the item</td>
  </tr>
  <tr>
    <td>Links</td>
    <td>Array of String</td>
    <td>Links to the item on store pages</td>
  </tr>
</tbody>
</table>

### Networking

<ul>
  <li> Login Screen
    <ul>
      <li> (Read/GET) Query logged in user object </li>
    </ul>
  </li>
  
  <li> Profile
    <ul>
      <li> (Update/PUT) Create new User Object </li>
      <li> (Update/PUT) Update user object </li>
      <li> (Read/GET) (Read/GET) Query Details of a User Object </li>
    </ul>
  </li>
  
  <li> Recommendations
    <ul>
      <li> (Read/GET) Query all recommendations (including details) which match the user’s selected genres </li>
      <li> (Create/POST) Create a new “Recommendation/Favourite” on an item </li>
      <li> (Delete) Delete existing recommendation </li>
    </ul>
  </li>
  
  <li> Details
    <ul>
      <li> (Read/GET) Query to get all comments of the item </li>
      <li> (Create/POST) Create a new comment on a post </li>
      <li> (Delete) Delete existing comment </li>
    </ul>
  </li>
  
  <li> Users Nearby
    <ul>
      <li> (Read/GET) Query users based on their location (same city?) </li>
      <li> (Create/POST) Send a Friend Request </li>
    </ul>
  </li>
  
  <li> Genre Choose Screen:
    <ul>
      <li> (Read/GET) Query all genres added by the User </li>
      <li> (Create/POST) Create list of genres selected by the User </li>
      <li> (Update/PUT) Update list of genres selected by the User </li>
    </ul>
  </li>
</ul>

- Example query: Query all recommendations (including details) which match the user’s selected genres
```kotlin
        val query: ParseQuery<Item> = ParseQuery.getQuery(Post::class.java)
        // Find all recommendation items from the log in user
        query.include(Item.KEY_USER)
        query.findInBackground { recommendationList, e ->
            if (e == null) {
                Log.i("Recommendations", "Retrieved " + recommendationList.size + " recommendations")
            } else {
                e.printStackTrace()
            }
        }
```
- [OPTIONAL: List endpoints if using existing API such as Yelp]
