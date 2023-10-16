import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';


export const animalsSlice = createSlice({
  name: 'animals',
  initialState:{ 
    animals: [],
    status: 'idle', 
    error: null 
  },
  reducers: {

  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchAnimals.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchAnimals.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.animals = action.payload;
      })
      .addCase(fetchAnimals.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
  },
})
export default animalsSlice.reducer


export const fetchAnimals  =  createAsyncThunk('animals/fetchAnimals', async () => {
  const response = await fetch('http://localhost:8080/animal');
  const data = await response.json();
  return data;
});

export const selectAllAnimals = state => state.animals
export const selectAnimalByID = ( state, animalID ) => state.animals.animals.find(a => a.id === animalID)
