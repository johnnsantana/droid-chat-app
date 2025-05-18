package com.johnnsantana.droidchat.model.fake

import com.johnnsantana.droidchat.model.User

val user1 =  User(
    id = 1,
    self = true,
    firstName = "John",
    lastName = "Doe",
    profilePictureUrl = null,
    username = "john_doe"
)

val user2 = User(
    id = 2,
    self = false,
    firstName = "Jane",
    lastName = "Doe",
    profilePictureUrl = null,
    username = "jane_doe"
)

val user3 = User(
    id = 4,
    self = false,
    firstName = "Robert",
    lastName = "Doe",
    profilePictureUrl = null,
    username = "robert_doe"
)

val user4 = User(
    id = 4,
    self = false,
    firstName = "Elisabeth",
    lastName = "Doe",
    profilePictureUrl = null,
    username = "elisabeth_doe"
)