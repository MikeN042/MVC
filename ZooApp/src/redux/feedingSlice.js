import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';


export const feedingsSlice = createSlice({
  name: 'feedings',
  initialState:{ 
    feedings: [],
    status: 'idle', 
    error: null 
  },
  reducers: {

  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchfeedings.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchfeedings.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.feedings = action.payload;
      })
      .addCase(fetchfeedings.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(deleteFeeding.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(deleteFeeding.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.feedings = state.feedings.filter(f => f.id !== action.payload)
      })
      .addCase(deleteFeeding.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(addFeeding.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(addFeeding.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.feedings.push(action.payload)
      })
      .addCase(addFeeding.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
  },
})
export default feedingsSlice.reducer


export const fetchfeedings  =  createAsyncThunk('feedings/fetchfeedings', async (animalID) => {
  const response = await fetch(`http://localhost:8080/animal/${animalID}/feedings`);
  const data = await response.json();
  return data;
});

export const deleteFeeding  =  createAsyncThunk('feedings/deleteFeeding', async (feedingID) => {
  const response = await fetch(`http://localhost:8080/animal/feedings/delete/${feedingID}`, {
    method: 'DELETE',
  });
  return feedingID
});

export const addFeeding  =  createAsyncThunk('feedings/addFeeding', async ({animalID,keeperID}) => {
    const response = await fetch(`http://localhost:8080/animal/${animalID}/feed?keeperID=${keeperID}`, {
      method: 'POST'
    });
    const data = await response.json();;
    return data
});
export const selectAllFeedings = state => state.feedings
