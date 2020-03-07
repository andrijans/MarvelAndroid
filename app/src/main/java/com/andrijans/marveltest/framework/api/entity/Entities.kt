package com.andrijans.marveltest.framework.api.entity

import android.os.Parcelable
import com.andrijans.marveltest.framework.api.entity.common.Result
import kotlinx.android.parcel.Parcelize

/**
 * Created by andrijanstankovic on 03/04/2018.
 */
@Parcelize
data class Response(val code: Int = -1, val status: String = "", val copyright: String = "", val attributionText: String = "", var data: DataContainer = DataContainer(), val etag: String = "") : Result<DataContainer>(), Parcelable {
    override fun getResultData(): DataContainer {
        return data
    }

    override fun setResultData(resultData: DataContainer) {
        data = resultData
    }

}

@Parcelize
data class DataContainer(val offset: Int = -1, val limit: Int = -1, val total: Int = -1, val count: Int = -1, val results: MutableList<Character> = mutableListOf()) : Parcelable

@Parcelize
data class Character(val id: Int = -1,
                     val name: String = "",
                     val description: String = "",
                     val resourceURI: String = "",
                     val urls: MutableList<Url> = mutableListOf(),
                     val thumbnail: Image = Image(),
                     val comics: Comics = Comics(),
                     val series: Series = Series(),
                     val stories: Stories = Stories()
) : Parcelable

@Parcelize
data class Image(val path: String = "", val extension: String = "") : Parcelable

@Parcelize
data class Url(val type: String = "", val url: String = "") : Parcelable

@Parcelize
data class Comics(val available: Int = 0, val collectionURI: String = "", val items: MutableList<Comic> = mutableListOf()) : Parcelable

@Parcelize
data class Comic(val resourceURI: String = "", val name: String = "") : Parcelable

@Parcelize
data class Stories(val available: Int = 0, val collectionURI: String = "", val items: MutableList<Story> = mutableListOf()) : Parcelable

@Parcelize
data class Story(val resourceURI: String = "", val name: String = "", val type: String = "") : Parcelable

@Parcelize
data class Series(val available: Int = 0, val collectionURI: String = "", val items: MutableList<Serie> = mutableListOf()) : Parcelable

@Parcelize
data class Serie(val resourceURI: String = "", val name: String = "") : Parcelable