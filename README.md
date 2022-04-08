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
</table>
Name
Location
Friends
Recommendations
Genres
Username
Emails
Phone Number
Password


Items:

Item Type:
Name
Genre
Sypnosis
Picture
Recommend by
No. of Recommendations
Links

### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
