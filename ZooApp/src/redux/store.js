import { configureStore } from '@reduxjs/toolkit'
import animalsReducer from './animalSlice'
import keepersReducer from './keeperSlice'
import feedingsReducer from './feedingSlice';

export default configureStore({
    reducer: {
        animals: animalsReducer,
        keepers: keepersReducer,
        feedings: feedingsReducer,
    }
});