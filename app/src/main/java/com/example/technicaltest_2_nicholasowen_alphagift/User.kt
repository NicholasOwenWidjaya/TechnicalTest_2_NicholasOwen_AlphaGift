package com.example.technicaltest_2_nicholasowen_alphagift

import com.google.firebase.storage.StorageReference

data class User(var Name: String ?= null, var Address: String ?= null, var imgUri: StorageReference ?= null)
