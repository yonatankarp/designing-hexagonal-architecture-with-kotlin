package com.yonatankarp.domain.specification.shared

class NotSpecification<T>(private val spec: Specification<T>) : AbstractSpecification<T>() {
    override fun isSatisfiedBy(t: T) = !spec.isSatisfiedBy(t)
}
