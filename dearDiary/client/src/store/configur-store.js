import { configureStore} from '@reduxjs/toolkit'
import { getDefaultNormalizer } from '@testing-library/react'
import {api} from "./api"
import reducer from "./reducer"
const store = configureStore({
    reducer,
    middleware:(getDefaultMiddleware) => getDefaultMiddleware().concat(api)
})
export default store