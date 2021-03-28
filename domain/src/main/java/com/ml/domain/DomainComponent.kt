package com.ml.domain

import dagger.Component

@Component(modules = [DomainModule::class])
interface DomainComponent {
    @Component.Builder
        interface Builder{
        fun build(): DomainComponent
    }
}