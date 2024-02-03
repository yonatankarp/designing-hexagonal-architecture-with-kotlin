package com.yonatankarp.domain.valueobject

object SwitchIdFixture {
    val id: SwitchId get() = SwitchId.withId("cdcaf6b1-56d1-419a-94bd-e099db38b6a7")
    val randomId: SwitchId get() = SwitchId.withoutId()
}
