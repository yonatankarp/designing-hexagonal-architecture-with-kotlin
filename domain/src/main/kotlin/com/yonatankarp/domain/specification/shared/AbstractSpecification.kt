package com.yonatankarp.domain.specification.shared

abstract class AbstractSpecification<T> : Specification<T> {
    abstract override fun isSatisfiedBy(t: T): Boolean

    override fun and(other: Specification<T>): Specification<T> = AndSpecification(this, other)

    override fun or(other: Specification<T>): Specification<T> = OrSpecification(this, other)

    override fun not(): Specification<T> = NotSpecification(this)
}
