import { configureStore } from '@reduxjs/toolkit'
import animalReducer from './animal'

export default configureStore({
    reducer: {
        animal: animalReducer,
    }
});