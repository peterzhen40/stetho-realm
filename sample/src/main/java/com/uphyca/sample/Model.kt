package com.uphyca.sample

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Model (
        @PrimaryKey
        var id: Long,
        var text: String?
): RealmObject() {
        constructor() : this(0,null)
}