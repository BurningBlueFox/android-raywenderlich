package com.thiagogamedev.todolist.model

import android.os.Parcel
import android.os.Parcelable

class TaskList(val name: String, val tasks: ArrayList<String> = ArrayList()) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    )

    companion object CREATOR: Parcelable.Creator<TaskList>{
        override fun createFromParcel(source: Parcel): TaskList =
            TaskList(source)

        override fun newArray(size: Int): Array<TaskList?> = arrayOfNulls(size)

    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeString(name)
        out.writeStringList(tasks)
    }

    override fun describeContents() = 0
}