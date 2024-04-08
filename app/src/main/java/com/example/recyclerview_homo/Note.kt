package com.example.recyclerview_homo

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val title: String,
    val body: String

) : Parcelable
