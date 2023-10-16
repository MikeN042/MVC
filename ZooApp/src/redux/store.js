import { configureStore } from '@reduxjs/toolkit'
import animalsReducer from './animalSlice'

export default configureStore({
    reducer: {
        animals: animalsReducer,
    }
});