package com.productstesttask.domain.use_cases.base

import com.productstesttask.data.remote.service.base.Answer

interface UseCase<Input, Output> {
    suspend operator fun invoke(params: Input): Answer<Output>
}
