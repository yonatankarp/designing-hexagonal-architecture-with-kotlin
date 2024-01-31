package com.yonatankarp.domain.specification.shared

class OrSpecification<T>(
    private val spec1: Specification<T>,
    private val spec2: Specification<T>,
) : AbstractSpecification<T>() {
    override fun isSatisfiedBy(t: T) = spec1.isSatisfiedBy(t) || spec2.isSatisfiedBy(t)
}
