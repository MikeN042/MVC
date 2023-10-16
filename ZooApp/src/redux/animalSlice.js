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
      .addCase(deleteAnimal.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(deleteAnimal.fulfilled, (state, action) => {
        state.status = 'succeeded';
      })
      .addCase(deleteAnimal.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
        console.log('here');
      })
  },
})
export default animalsSlice.reducer


export const fetchAnimals  =  createAsyncThunk('animals/fetchAnimals', async () => {
  const response = await fetch('http://localhost:8080/animal');
  const data = await response.json();
  return data;
});

export const deleteAnimal  =  createAsyncThunk('animals/deleteAnimal', async (animalID) => {
  const response = await fetch(`http://localhost:8080/animal/delete/${animalID}`, {
    method: 'DELETE',
  });
  const data = await response;
  return animalID;
});

export const selectAllAnimals = state => state.animals
export const selectAnimalByID = ( state, animalID ) => state.animals.animals.find(a => a.id === animalID)

