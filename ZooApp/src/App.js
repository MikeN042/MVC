import Navbar from './components/Navbar';
import Home from './components/Home';
import CreateAnimal from './components/CreateAnimal';
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom'
import AnimalDetails from './components/AnimalDetails';
import NotFound from './components/NotFound';
import { useDispatch } from "react-redux";
import { fetchAnimals } from "./redux/animalSlice";
import { fetchKeepers } from "./redux/keeperSlice";
import React, { useEffect } from 'react';



function App() {

  const dispatch = useDispatch();

  useEffect(() => {
      dispatch(fetchAnimals());
      dispatch(fetchKeepers());
    }, [dispatch]);


  return (
    <Router>
      <div className="App">
        <Navbar />
        <div className="content">
          <Switch>
            <Route exact path="/">
              <Home />
            </Route>
            <Route path="/create/animal">
              <CreateAnimal />
            </Route>
            <Route path="/animal/:id" >
              <AnimalDetails />
            </Route>
            <Route path="*">
              <NotFound />
            </Route>
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
