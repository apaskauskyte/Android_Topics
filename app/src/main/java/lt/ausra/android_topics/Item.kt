package lt.ausra.android_topics

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Item(
    private var _id: Int,
    private var _text01: String,
    private var _text02: String,
    private var _creationDate: LocalDateTime = LocalDateTime.now(),
    private var _updateDate: LocalDateTime = LocalDateTime.now(),
) : Parcelable {

    @IgnoredOnParcel
    var id = this._id
        private set

    @IgnoredOnParcel
    var text01: String = ""
        get(): String {
            return _text01
        }
        set(value) {
            field = value
            this._text01 = value
            this._updateDate = LocalDateTime.now()
        }

    @IgnoredOnParcel
    var text02: String //geriau taip rasyt be field
        get(): String {
            return _text02
        }
        set(value) {
            this._text02 = value
            this._updateDate = LocalDateTime.now()
        }

    @IgnoredOnParcel
    val creationDate: LocalDateTime
        get() = this._creationDate

    @IgnoredOnParcel
    val updateDate: LocalDateTime
        get() = this._updateDate
}