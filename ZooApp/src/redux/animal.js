import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';





export const animalSlice = createSlice({
  name: 'animal',
  initialState:{ 
    animals: [],
    status: 'idle', 
    error: null 
  },
  reducers: {
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchData.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchData.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.animals = action.payload;
      })
      .addCase(fetchData.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      });
  },
})

export const fetchData  =  createAsyncThunk('data/fetchData', async () => {
  const response = await fetch('http://localhost:8080/animal');
  const data = await response.json();
  return data;
});

export default animalSlice.reducer