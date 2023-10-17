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
        state.animals = state.animals.filter(a => a.id !== action.payload)
      })
      .addCase(deleteAnimal.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(addAnimal.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(addAnimal.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.animals.push(action.payload);
      })
      .addCase(addAnimal.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
  },
})
export default animalsSlice.reducer


export const fetchAnimals  =  createAsyncThunk('animals/fetchAnimals', async () => {
  const response = await fetch('http://localhost:8080/animal');
  return await response.json();
});

export const deleteAnimal  =  createAsyncThunk('animals/deleteAnimal', async (animalID) => {
  const response = await fetch(`http://localhost:8080/animal/delete/${animalID}`, {
    method: 'DELETE',
  });
  const data = await response;
  return animalID
});

export const addAnimal  =  createAsyncThunk('animals/addAnimal', async (animal) => {
  const response = await fetch('http://localhost:8080/animal/new', {
    method: 'POST',
    headers:{"Content-Type":"application/json"},
    body: JSON.stringify(animal)
  });
  return await response.json();
});

export const selectAllAnimals = state => state.animals
export const selectAnimalByID = ( state, animalID ) => state.animals.animals.find(a => a.id === animalID)

