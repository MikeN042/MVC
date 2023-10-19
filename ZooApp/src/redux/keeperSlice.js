import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';


export const keepersSlice = createSlice({
    name: 'keepers',
    initialState:{ 
      keepers: [],
      status: 'idle', 
      error: null 
    },
    reducers: {
  
    },
    extraReducers: (builder) => {
      builder
        .addCase(fetchKeepers.pending, (state) => {
          state.status = 'loading';
        })
        .addCase(fetchKeepers.fulfilled, (state, action) => {
          state.status = 'succeeded';
          state.keepers = action.payload;
        })
        .addCase(fetchKeepers.rejected, (state, action) => {
          state.status = 'failed';
          state.error = action.error.message;
        })
    },
  })
export default keepersSlice.reducer
  
  
export const fetchKeepers  =  createAsyncThunk('keeper/fetchKeepers', async () => {
const response = await fetch('http://localhost:8080/keeper');
const data = await response.json();
return data;
});

export const selectAllKeepers = state => state.keepers
export const selectKeeperByID = ( state, keeperID ) => state.keepers.keepers.find(a => a.id === keeperID)