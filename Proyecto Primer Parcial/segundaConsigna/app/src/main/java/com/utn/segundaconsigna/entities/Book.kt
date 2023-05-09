package com.utn.segundaconsigna.entities
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "books")
class Book(id: Int, name: String?, year: Int, author: String?, editorial: String?,imageUrl: String? ) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int

    @ColumnInfo(name = "name")
    var name: String?

    @ColumnInfo(name = "year")
    var year: Int

    @ColumnInfo(name = "author")
    var author: String?

    @ColumnInfo(name = "editorial")
    var editorial: String?

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String?

    constructor(parcel: Parcel) : this(
        TODO("id"),
        TODO("name"),
        TODO("year"),
        TODO("author"),
        TODO("editorial"),
        TODO("imageUrl")
    ) {
        id = parcel.readInt()
        name = parcel.readString()
        year = parcel.readInt()
        author = parcel.readString()
        editorial = parcel.readString()
        imageUrl = parcel.readString()
    }


    init {
        this.id         = id
        this.name       = name
        this.year       = year
        this.author     = author
        this.editorial  = editorial
        this.imageUrl   = imageUrl
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}
/*

class Book(
    var id: Int,
    var name: String?,
    var year: Int,
    var author: String?,
    var editorial: String?,
    var genre: String?,
    var imageUrl: String?
): Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}*/